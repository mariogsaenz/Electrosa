package es.unirioja.paw.web;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/i18n")
public class I18nController {

    private final List<String> languageCollection = Arrays.asList("es", "en", "fr", "de");

    @GetMapping("/lang")
    public String index(Model model) {

        model.addAttribute("languageCollection", languageCollection);
        return "i18n/lang";
    }

}
