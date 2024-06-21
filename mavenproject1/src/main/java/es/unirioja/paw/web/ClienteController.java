package es.unirioja.paw.web;

import com.github.javafaker.Faker;
import es.unirioja.paw.filter.ClienteAuthnFilter;
import es.unirioja.paw.jpa.ArticuloEntity;
import es.unirioja.paw.jpa.CestaCompraEntity;
import es.unirioja.paw.jpa.ClienteEntity;
import es.unirioja.paw.jpa.LineaCestaCompraEntity;
import es.unirioja.paw.jpa.PedidoEntity;
import es.unirioja.paw.model.Articulo;
import es.unirioja.paw.model.Usuario;
import es.unirioja.paw.repository.ArticuloRepository;
import es.unirioja.paw.repository.ClienteRepository;
import es.unirioja.paw.repository.PedidoRepository;
import es.unirioja.paw.service.AvatarClienteChangeUseCase;
import es.unirioja.paw.service.PedidoService;
import es.unirioja.paw.service.data.AvatarClienteChangeRequest;
import es.unirioja.paw.service.data.AvatarClienteChangeResponse;
import es.unirioja.paw.web.data.ClienteAvatarInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Faker faker = new Faker(Locale.of("es"));
    
    @Autowired
    private ArticuloRepository articuloRepository;
    
    @Autowired
    private AvatarClienteChangeUseCase avatarClienteChangeUseCase;

    @GetMapping("/cuenta")
    public String cuenta(ModelMap model, HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        Object clienteActivo = session.getAttribute("cliente");
        
        model.addAttribute("clienteActivo",(ClienteEntity) clienteActivo);
        
        return "auth/area-cliente";
        
    }
   
    @GetMapping("/datos")
    public String datosPersonales(Model model) {
        logger.warn("Mostramos vista datos cliente");
        return "auth/datos-personales";
    }
    
    @PostMapping("/avatar")
    public String uploadAvatarImage(HttpSession session,HttpServletRequest request,@RequestParam("file") MultipartFile file) {
        ClienteEntity cliente = (ClienteEntity) session.getAttribute("cliente");
        String realTargetPath = request
                .getServletContext()
                .getRealPath("/resources/static/assets/avatar");
        Optional<AvatarClienteChangeResponse> response = avatarClienteChangeUseCase
                .execute(new AvatarClienteChangeRequest(cliente.getCodigo(),file,realTargetPath));

        if (response.isPresent()) {
            session.setAttribute("clienteAvatar", new ClienteAvatarInfo(response.get().cliente));
        }
        return "redirect:/cliente/cuenta";
    }

    @GetMapping("/favoritos")
    public String mostrarFavoritos(Model model) {
        logger.warn("Mostramos vista favoritos");
        return "compras/favoritos";
    }

    
}
