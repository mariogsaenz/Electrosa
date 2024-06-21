package es.unirioja.paw.config;

import es.unirioja.paw.exception.AccessNotAuthorizedException;
import es.unirioja.paw.exception.ArticuloNotFoundException;
import es.unirioja.paw.exception.PedidoNotFoundException;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Locale;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import javax.sql.DataSource;
import java.util.Properties;
import javax.naming.NamingException;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.jndi.JndiTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

@EnableWebMvc
@Configuration
@ComponentScan({"es.unirioja.paw"})
@PropertySource("classpath:app.properties")
@EnableJpaRepositories("es.unirioja.paw.repository") // Spring Data JPA
@EnableTransactionManagement
public class SpringWebConfig implements WebMvcConfigurer {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Environment env;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public SpringWebConfig() {
        logger.info("Configurando Spring!");
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/core/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/core/js/");
        registry.addResourceHandler("/static/**").addResourceLocations("/resources/static/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);

        templateResolver.setPrefix("/WEB-INF/views/thymeleaf/"); // Ubicacion de las vistas
        templateResolver.setSuffix(".html"); // asi no hace falta incluir la extension en los controladores
        templateResolver.setTemplateMode(TemplateMode.HTML); // se puede omitir (valor por defecto)
//        templateResolver.setCacheable(true); // TODO
        templateResolver.setCacheable(false);
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }
    
    

    @Bean
    public SpringTemplateEngine templateEngine() {
//        https://www.thymeleaf.org/doc/tutorials/3.0/thymeleafspring.html
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
//        https://www.thymeleaf.org/doc/articles/layouts.html
        templateEngine.addDialect(new LayoutDialect());
        return templateEngine;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        WebMvcConfigurer.super.addFormatters(registry);
        registry.addFormatter(dateFormatter());
    }

    @Bean
    public DateFormatter dateFormatter() {
        return new DateFormatter();
    }

    // // Integracion Spring + Thymeleaf
    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
//        viewResolver.setCache(false);
        return viewResolver;
    }

    // Configuracion del datasource:
    // - Memoria
    // - Configuracion MySQL manual
    // - Configuracion MySQL como recurso JNDI
    //    @Bean
    public DataSource dataSourceHSQL() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.HSQL).build();
    }

//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/electrosa?serverTimezone=GMT%2B2");
//        dataSource.setUsername("root");
//        dataSource.setPassword("root"); // Una contraseña en claro directamente en codigo??? Mal
//        return dataSource;
//    }
//
    // Mejor como recurso JNDI
    @Bean
    public DataSource dataSource() throws NamingException {
        return (DataSource) new JndiTemplate().lookup(env.getProperty("jdbc.url"));
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() throws NamingException {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("es.unirioja.paw.jpa"); // Paquetes donde se encuentran las endidades JPA
//        factory.setDataSource(dataSourceHSQL());
        factory.setDataSource(dataSource());
        factory.setJpaProperties(additionalProperties());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
//        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        return properties;
    }


    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.of("es"));
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean(name = "simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
        // Puedes revisar la configuracion en https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();

        // Propiedades (clave, valor): Excepcion --> Vista
        Properties mappings = new Properties();
        mappings.setProperty(AccessNotAuthorizedException.class.getName(), "error/forbidden");
        mappings.setProperty(ArticuloNotFoundException.class.getName(), "error/notFound");
        mappings.setProperty(PedidoNotFoundException.class.getName(), "error/notFound");
        
        r.setExceptionMappings(mappings);

        r.setDefaultStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        r.setDefaultErrorView("error/default");

        // Propiedades (clave, valor): Vista --> Codigo de estado HTTP        
        r.addStatusCode("error/forbidden", HttpServletResponse.SC_FORBIDDEN);
        r.addStatusCode("error/notFound", HttpServletResponse.SC_NOT_FOUND);

        r.setExceptionAttribute("exception");
        return r;
    }

    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}
