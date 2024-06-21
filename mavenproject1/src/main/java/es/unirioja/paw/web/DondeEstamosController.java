/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.unirioja.paw.web;

import com.github.javafaker.Faker;
import es.unirioja.paw.jpa.AlmacenEntity;
import es.unirioja.paw.repository.AlmacenRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/info/donde-estamos")
public class DondeEstamosController extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Faker faker = new Faker(Locale.of("es"));
    
    @Autowired
    private AlmacenRepository almacenRepository;
    
    @GetMapping(value = {"", "/"})
    public String index(ModelMap model, HttpServletRequest request) {
        
        List<AlmacenEntity> almacenes = almacenRepository.findAll();
        
        model.addAttribute("almacenes", almacenes);
            
        return "mapa/almacenes-map";
           
    }

}
