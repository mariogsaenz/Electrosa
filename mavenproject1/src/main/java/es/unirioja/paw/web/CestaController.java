/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.unirioja.paw.web;


import es.unirioja.paw.exception.AccessNotAuthorizedException;
import es.unirioja.paw.exception.PedidoNotFoundException;
import es.unirioja.paw.jpa.ArticuloEntity;
import es.unirioja.paw.jpa.CestaCompraEntity;
import es.unirioja.paw.jpa.ClienteEntity;
import es.unirioja.paw.jpa.LineaCestaCompraEntity;
import es.unirioja.paw.jpa.LineaPedidoEntity;
import es.unirioja.paw.jpa.LineaanuladaEntity;
import es.unirioja.paw.jpa.PedidoEntity;
import es.unirioja.paw.jpa.PedidoanuladoEntity;
import es.unirioja.paw.service.CestaCompraUseCase;
import es.unirioja.paw.service.PedidoAnuladoUseCase;
import es.unirioja.paw.service.PedidoService;
import es.unirioja.paw.service.data.AddToCartRequest;
import es.unirioja.paw.service.data.AddToCartResponse;
import static es.unirioja.paw.web.SessionConstants.CESTA_KEY;
import es.unirioja.paw.web.data.CarritoAddRequest;
import es.unirioja.paw.web.data.RegistroPostPayload;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cliente")
public class CestaController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private PedidoAnuladoUseCase pedidoAnuladoUseCase;
    
    @Autowired
    private CestaCompraUseCase cestaCompraUseCase;
    
    @GetMapping({"/pedido"})
    public String pedido(ModelMap model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        ClienteEntity cliente = (ClienteEntity) session.getAttribute("cliente");
        
        List<PedidoEntity> pedidos = pedidoService.findByCliente(cliente.getCodigo());
        
        model.addAttribute("cliente",(ClienteEntity) cliente);
        model.addAttribute("pedidos", pedidos);
        
        return "compras/compras-realizadas";
    }
    
    @GetMapping({"/pedido/{codigo:.+}"})
    public String pedido_detail(Model model, @PathVariable("codigo") String codigo, HttpServletRequest request) {

        PedidoEntity entity = pedidoService.findOne(codigo);

        HttpSession session = request.getSession();
        ClienteEntity cliente = (ClienteEntity) session.getAttribute("cliente");
        
        if(entity==null){
            logger.info("Pedido {}: no encontrado", codigo);
            throw new PedidoNotFoundException("Pedido [" + codigo + "] no encontrado");
        }
        else{
            String codCliente = entity.getCodigoCliente();
        
            if(!codCliente.equals(cliente.getCodigo())){
                logger.info("El usuario no tiene permiso para ver ese pedido");
                throw new AccessNotAuthorizedException("No está autorizado para consultar este pedido");
            }
            List<LineaPedidoEntity> articulos2 = entity.getLineas();
            model.addAttribute("pedido", entity);
            model.addAttribute("articuloss", articulos2);
            return "compras/compras-realizadas-pedido";
        }

    }
    
    @GetMapping({"/pedido/anulados"})
    public String pedidosAnulados(ModelMap model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        ClienteEntity cliente = (ClienteEntity) session.getAttribute("cliente");
        
        List<PedidoanuladoEntity> pedidos = pedidoAnuladoUseCase.findAnuladosByCliente(cliente.getCodigo());
        
        model.addAttribute("cliente",(ClienteEntity) cliente);
        model.addAttribute("anulados", pedidos);
        
        return "compras/pedidos-anulados";
    }
    
    @GetMapping({"/pedido/anulados/{codigo:.+}"})
    public String pedidosAnulados_detail(Model model, @PathVariable("codigo") String codigo, HttpServletRequest request) {
 
        PedidoanuladoEntity entity = pedidoAnuladoUseCase.findOne(codigo);
        
        HttpSession session = request.getSession();
        ClienteEntity cliente = (ClienteEntity) session.getAttribute("cliente");
        
        if(entity==null){
            logger.info("Pedido {}: no encontrado", codigo);
            throw new PedidoNotFoundException("Pedido anulado [" + codigo + "] no encontrado");
        }
        else{
            String codCliente = entity.getCodigoCliente();
        
            if(!codCliente.equals(cliente.getCodigo())){
                logger.info("El usuario no tiene permiso para ver ese pedido anulado");
                throw new AccessNotAuthorizedException("No está autorizado para consultar este pedido anulado");
            }
            List<LineaanuladaEntity> lineas = entity.getLineas();
            model.addAttribute("pedidoAnulado", entity);
            model.addAttribute("lineas", lineas);;
            return "compras/pedidos-anulados-pedido";
        }
                
    }
    
    @GetMapping({"/cesta"})
    public String cesta(ModelMap model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        ClienteEntity cliente = (ClienteEntity) session.getAttribute("cliente");
        
        CestaCompraEntity cesta = pedidoService.findCestaCliente(cliente.getCodigo());
        if(cesta!=null){
            session.setAttribute(CESTA_KEY, cesta);
            List<LineaCestaCompraEntity> lineas = cesta.getLineas();
            model.addAttribute("lineas",lineas);
        }
        return "compras/cesta-compra";  
    }
    
    @PostMapping("/cesta")
    public String addToCart(@RequestParam("codigoArticulo") String codigoArticulo, HttpSession session, Model model) {
        CestaCompraEntity cesta = cestaFromSession(session);
        Optional<AddToCartResponse> response = cestaCompraUseCase.add(new AddToCartRequest(codigoArticulo,cesta));
        if(response.isPresent()){
            session.setAttribute(CESTA_KEY, response.get().cesta);
        }
        else{
            logger.warn("Respuesta es empty");
        }
        return "redirect:/cliente/cesta";
    }
    
    private CestaCompraEntity cestaFromSession(HttpSession session) {
        CestaCompraEntity cesta = (CestaCompraEntity) session.getAttribute(SessionConstants.CESTA_KEY);
        ClienteEntity cliente = (ClienteEntity) session.getAttribute(SessionConstants.CLIENTE_KEY);
        if (cliente == null) {
            logger.warn("No hay cliente en sesion!");
            return null;
        }
        if (cesta == null) {
            logger.warn("Cliente {}: se crea cesta nueva", cliente.getCodigo());
            cesta = new CestaCompraEntity();
            cesta.setCodigoCliente(cliente.getCodigo());
            cesta.setFechaInicio(Timestamp.valueOf(LocalDateTime.now()));
            cesta.setLineas(new ArrayList<>());
        }
        return cesta;
    }
    
}
