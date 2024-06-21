package es.unirioja.paw.web;

import com.github.javafaker.Faker;
import es.unirioja.paw.jpa.ArticuloEntity;
import es.unirioja.paw.repository.ArticuloRepository;
import es.unirioja.paw.service.ArticuloFinder;
import es.unirioja.paw.web.ejemplo.EjemploPostPayload;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ejemplo")
public class EjemploController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Faker faker = new Faker(Locale.of("es"));

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private ArticuloFinder articuloFinder;

    @GetMapping("/")
    public String index(Model model) {
        return "redirect:/ejemplo/catalogo";
    }

    @GetMapping("/catalogo")
    public String catalogo(Model model) {
        List<ArticuloEntity> itemCollection = articuloFinder.findAll();
        model.addAttribute("articulos", itemCollection);
        return "ejemplo/catalogo";
    }

    @GetMapping("/catalogo/{articleId:.+}")
    public String detail(Model model, @PathVariable("articleId") String articleId) {

        Optional<ArticuloEntity> entity = articuloRepository.findById(articleId);
        if (entity.isPresent()) {
            logger.info("Articulo {}: encontrado", articleId);
            model.addAttribute("articulo", entity.get());
            return "ejemplo/ficha-producto";
        }
        logger.info("Articulo {}: no encontrado", articleId);
        return "redirect:/ejemplo/catalogo";
    }

    @PostMapping("/cesta")
    public String addToCart(@ModelAttribute EjemploPostPayload ejemploPostPayload, HttpSession session, Model model) {
        logger.info("Articulo: {}", ejemploPostPayload.getCodigoArticulo());
        logger.info("Añadiendo articulo a la cesta");
        return String.format("redirect:/ejemplo/catalogo/%s", ejemploPostPayload.getCodigoArticulo());
    }

}
