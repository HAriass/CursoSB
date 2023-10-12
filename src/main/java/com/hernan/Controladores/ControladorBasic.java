
package com.hernan.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hola") //creo un endpoint
public class ControladorBasic {
    
    @GetMapping(path = {"/saludar", "/holaMundo"}) //hay varias propiedades para el mapping la comun es path y puede ser un arreglo de rutas
    public String saludar(){
        return "index";
    }
}
