/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.unirioja.paw.web;

import java.io.IOException;
import java.io.PrintWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class WelcomeController {

    @GetMapping(value = "/")
    public String index(ModelMap model) {
        return "index/landing";
    }

}


