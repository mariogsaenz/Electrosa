package es.unirioja.paw;

import es.unirioja.paw.config.SpringWebConfig;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

    private static final String TMP_FOLDER = "";
    private static final int MAX_UPLOAD_SIZE = 5 * 1024 * 1024;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.setServletContext(servletContext);
        ctx.register(SpringWebConfig.class);
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");

        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
                TMP_FOLDER,
                MAX_UPLOAD_SIZE, 
                MAX_UPLOAD_SIZE * 2L, 
                0);

        servlet.setMultipartConfig(multipartConfigElement);
    }

}
