package org.lasencinas.sys.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.lasencinas.sys.configuration.Paginas;
import org.lasencinas.sys.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class ControllerBasic {
	
	public List<Post> getPosts(){
		ArrayList<Post> post = new ArrayList<>();
		
		post.add(new Post(1,"Este apartado tratará de Desarrollo Web","../img/desarrolloweb.jpg",new Date(),"Desarrollo Web"));
		post.add(new Post(2,"Este apartado tratará de Base de Datos","../img/mysql.jpg",new Date(),"Base de Datos"));
		post.add(new Post(3,"Este apartado tratará de Programacion","../img/java1.jpg",new Date(),"Programacion"));
		
		return post;
	}
	
	@GetMapping(path = {"/posts","/"})
	public String saludar(Model model) {
		model.addAttribute("posts",this.getPosts());
		return "index";
	}
//	@GetMapping(path="/public")
//	public ModelAndView post() {
//		ModelAndView modelAndView = new ModelAndView(Paginas.HOME);
//		modelAndView.addObject("posts",this.getPosts());
//		return modelAndView;
//	}
	
	@GetMapping(path = {"/post"})
	
	public ModelAndView getPostIndividual(
			@RequestParam(defaultValue = "1", name ="id", required = false) int id) {
				ModelAndView modelAndView = new ModelAndView(Paginas.POST);
				
				List<Post> postFiltrado = this.getPosts().stream()
						.filter( (p) -> {
							return p.getId() == id;
						}).collect(Collectors.toList());
				modelAndView.addObject("post",postFiltrado.get(0));		
				return modelAndView;

				
	}
	

}
