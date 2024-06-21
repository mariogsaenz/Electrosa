/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.unirioja.paw.web;


import com.github.javafaker.Faker;
import es.unirioja.paw.exception.ArticuloNotFoundException;
import es.unirioja.paw.jpa.ArticuloEntity;
import es.unirioja.paw.repository.ArticuloRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/catalogo")
public class CatalogoController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Faker faker = new Faker(Locale.of("es"));
    
    private int pageSize = 12;
    
    @Autowired
    private ArticuloRepository articuloRepository;
    
    int parsePageNumber(HttpServletRequest request) {
        String pageNumberAsString = request.getParameter("p");
        if (pageNumberAsString == null || pageNumberAsString.isBlank()) {
            // no llega el "p" en querystring
            return 1;
        }
        int result;
        try {
            result = Integer.parseInt(pageNumberAsString);
        } catch (NumberFormatException e) {
            // no se puede convertir a Int
            return 1;
        }
        return result;
    }

    @GetMapping(value = {"", "/"})
    public String index(ModelMap model, HttpServletRequest request) {
        int pageNumber = parsePageNumber(request);
        Pageable pageable = PageRequest.of(pageNumber - 1, this.pageSize);
        logger.info("Pageable: {} , {}", pageable.getPageNumber(), pageable.getPageSize());
        
        Page<ArticuloEntity> page = articuloRepository.findAll(pageable);
        List<ArticuloEntity> articulos = page.getContent();
        
        model.addAttribute("articulos", articulos);
        model.addAttribute("pageInfo", page);
        
        return "catalogo/lista-articulos";
    }
    
    @GetMapping("/{articleId:.+}")
    public String detail(Model model, @PathVariable("articleId") String articleId) {

        Optional<ArticuloEntity> entity = articuloRepository.findById(articleId);
        if (entity.isPresent()) {
            logger.info("Articulo {}: encontrado", articleId);
            model.addAttribute("articulo", entity.get());
            return "catalogo/ficha-producto";
        }
        else{
            logger.info("Articulo {}: no encontrado", articleId);
            throw new ArticuloNotFoundException("Artículo con ID " + articleId + " no encontrado.");
        }
    }

}
