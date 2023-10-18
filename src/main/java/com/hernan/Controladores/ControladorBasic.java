
package com.hernan.Controladores;

import Modelos.Post;
import com.hernan.configuration.Paginas;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home") //creo un endpoint
public class ControladorBasic {
   
    
    public List<Post> getPosts(){
        ArrayList<Post> post = new ArrayList<>();
        post.add(new Post(1,"El desarrollo web abarca el proceso de construir y mantener sitios en Internet.","http://localhost/img/computadora-oficina.jpg",new Date(), "Desarrollo Web Front End"));
        post.add(new Post(2,"El desarrollo web abarca el proceso de construir y mantener sitios en Internet.","http://localhost/img/computadora-oficina.jpg",new Date(), "Desarrollo Web Back End"));
        post.add(new Post(3,"El desarrollo web abarca el proceso de construir y mantener sitios en Internet.","http://localhost/img/computadora-oficina.jpg",new Date(), "Desarrollo Web Frameworks"));
        post.add(new Post(4,"El desarrollo web abarca el proceso de construir y mantener sitios en Internet.","http://localhost/img/computadora-oficina.jpg",new Date(), "Desarrollo Web UX/UI"));
        return post;
    }
    @GetMapping(path = {"/posts", "/"}) //hay varias propiedades para el mapping la comun es path y puede ser un arreglo de rutas
    public String saludar(Model model){
        model.addAttribute("posts", this.getPosts());
        return "index";
    }
    @GetMapping(path = "/public")
    public ModelAndView post(){
        ModelAndView modelAndView = new ModelAndView(Paginas.HOME);
        modelAndView.addObject("posts", this.getPosts());
        return modelAndView;
    }
    /**
    @GetMapping(path = {"/post"})
    public ModelAndView getPostIndividual(
            @RequestParam(defaultValue = "1",name = "id",required = false) int id
        ){
        ModelAndView modelAndView = new ModelAndView(Paginas.POST);
        
        List<Post> postfiltrado = this.getPosts().stream().filter((p)->{return p.getId() == id;}).collect(Collectors.toList());
        modelAndView.addObject("post", postfiltrado.get(0));
        return modelAndView;
    }
    * **/
    
    //MI FORMA DE HACERLO
    @GetMapping(path = {"/post","/post/{post}"})
    public String getPostIndividual(
            Model model, @RequestParam(defaultValue = "1",name = "id",required = false) 
                    @PathVariable(required = true, name = "post")
                    int id){
        
        List<Post> posts = this.getPosts();
        Post postFiltrado = null;
        for (Post post : posts) {
            if(post.getId() == id){
                postFiltrado = post;
            }
        }
        
        model.addAttribute("post", postFiltrado);
        return "post";
    }
}
