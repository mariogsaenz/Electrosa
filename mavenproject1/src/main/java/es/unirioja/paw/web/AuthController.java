package es.unirioja.paw.web;

import com.github.javafaker.Faker;
import es.unirioja.paw.jpa.ArticuloEntity;
import es.unirioja.paw.jpa.CestaCompraEntity;
import es.unirioja.paw.jpa.ClienteEntity;
import es.unirioja.paw.service.AuthService;
import es.unirioja.paw.service.PedidoService;
import es.unirioja.paw.service.RegistroClienteUseCase;
import es.unirioja.paw.service.data.RegistroClienteRequest;
import es.unirioja.paw.service.data.RegistroClienteResponse;
import es.unirioja.paw.web.data.RegistroClienteValidationResponse;
import es.unirioja.paw.web.data.RegistroPostPayload;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    AuthService authService;
    
    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private RegistroClienteUseCase registroClienteUseCase;

    private final String USER_REGISTERED_KEY = "userRegistered";
    
    @GetMapping("/login")
    public String login(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

        String usuario = request.getParameter("username");
        String contraseña = request.getParameter("password");
        
        if (usuario == null || contraseña == null || usuario.isEmpty() || contraseña.isEmpty()) {
            model.addAttribute("errorMessage", "Por favor, introduzca un nombre de usuario y contraseña.");
            if (usuario != null) {
                model.addAttribute("errorUser", usuario);
            }
            return "auth/login-cliente";
        }
        
        ClienteEntity cliente = authService.authenticate(usuario, contraseña);
        if (cliente != null) {
            request.getSession().setAttribute("cliente", cliente);
            HttpSession session = request.getSession();           
            
            String redirectUrl = session != null ? (String) session.getAttribute("returnUrl") : null;
            return (redirectUrl != null) ? "redirect:" + redirectUrl : "redirect:/cliente/cuenta";
        } 
        else {
            model.addAttribute("errorMessage", "Usuario o contraseña incorrecta.");
            model.addAttribute("errorUser", usuario);
            return "auth/login-cliente";
        }  
    }
    
    @PostMapping("/login")
    public String handleLogin(HttpServletRequest request, HttpSession session, Model model){
        String usuario = request.getParameter("username");
        String contraseña = request.getParameter("password");
        ClienteEntity cliente = authService.authenticate(usuario, contraseña);
        if(cliente != null){
            logger.info("Usuario logueado correctamente");
            session.setAttribute("cliente",cliente);
            CestaCompraEntity cestaEntity = pedidoService.findCestaCliente(cliente.getCodigo());
            logger.info("Cliente {}: cesta={}", cliente.getCodigo(), cestaEntity == null ? "null" : cestaEntity.getLineas().size());
            session.setAttribute(SessionConstants.CESTA_KEY, cestaEntity);
            return "redirect:/cliente/cuenta";
        }
        else{
            logger.info("Credenciales incorrectas");
            model.addAttribute("error", "Credenciales incorrectas");
            return "auth/login";
        }
    }
    
    @GetMapping("/register")
    public String registro(Model model){
        return "auth/register";
    }
    
    @PostMapping("/register")
    public String registroSubmit(@ModelAttribute RegistroPostPayload modelAttribute, HttpSession session, Model model) {

        RegistroClienteValidationResponse validationResponse = validateRegistroPayload(modelAttribute);

        if (!validationResponse.isSuccess()) {
            model.addAttribute("messages", validationResponse.getMessageCollection());
            model.addAttribute("backing", modelAttribute);
            return "auth/register";
        }
        RegistroClienteResponse registroClienteResponse = registroClienteUseCase.registrar(new RegistroClienteRequest(modelAttribute));

        logger.info("Registro correcto? {}", registroClienteResponse.isSuccess());
        if (registroClienteResponse.isSuccess()) {
            session.setAttribute(USER_REGISTERED_KEY, registroClienteResponse.cliente);
            return "redirect:/auth/welcome";
        }

        model.addAttribute("backing", modelAttribute);
        return "auth/register";
    }
    
    @GetMapping("/welcome")
    public String welcome(Model model, HttpSession session) {
        ClienteEntity cliente = (ClienteEntity) session.getAttribute(USER_REGISTERED_KEY);
        if (cliente != null) {
            model.addAttribute("clienteRegistrado", cliente);
            session.removeAttribute(USER_REGISTERED_KEY);
        }
        return "auth/welcome";
    }
    
    @GetMapping("/logout")
    public String logout(ModelMap model, HttpServletRequest request) { 
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.invalidate();
        }
        return "redirect:/auth/login";
    }
    
    private RegistroClienteValidationResponse validateRegistroPayload(RegistroPostPayload modelAttribute) {
        RegistroClienteValidationResponse response = new RegistroClienteValidationResponse();
        response.setSuccess(true);
        String nombreUsuario = modelAttribute.getUsername();
        String mail = modelAttribute.getEmail();
        String password1 = modelAttribute.getPassword1();
        String password2 = modelAttribute.getPassword2();
        if (nombreUsuario == null || nombreUsuario.isBlank()) {
            response.getMessageCollection().add("El nombre de usuario debe proporcionar un valor");
            response.setSuccess(false);
        }
        if (mail == null || mail.isBlank()) {
            response.getMessageCollection().add("El correo electrónico debe proporcionar un valor");
            response.setSuccess(false);
        }
        if (password1 == null || password1.isBlank()) {
            response.getMessageCollection().add("Campo 'Contraseña' debe proporcionar un valor");
            response.setSuccess(false);
        }
        if (password2 == null || password2.isBlank()) {
            response.getMessageCollection().add("Campo 'Repita contraseña' debe proporcionar un valor");
            response.setSuccess(false);
        }
        return response;
    }
    
}
