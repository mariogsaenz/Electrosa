package es.unirioja.paw.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName = "FiltroEjemplo", urlPatterns = {"/cliente/*", "/clientes/*"})
public class ClienteAuthnFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        // Comprobar si el objeto cliente está en la sesión
        Object cliente = session.getAttribute("cliente");
        if (cliente != null) {
            // Dejar pasar la petición si el objeto cliente está en la sesión
            logger.info("Cliente autenticado. URL: {}", request.getRequestURL());
            chain.doFilter(req, resp);
        } 
        else {
            // Guardar la URL de destino en la sesión
            String requestURI = request.getRequestURI();
            session.setAttribute("urlDestino", requestURI);

            // Redirigir al controlador de login
            logger.info("Cliente no autenticado. Redirigiendo al controlador de login.");
            response.sendRedirect(request.getContextPath() + "/auth/login");
        }
    }

    @Override
    public void destroy() {

    }
}
