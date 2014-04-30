package gr.dimitriskiriakakis.jba.controller;

import java.security.Principal;

import javax.validation.Valid;

import gr.dimitriskiriakakis.jba.entity.Blog;
import gr.dimitriskiriakakis.jba.service.BlogService;
import gr.dimitriskiriakakis.jba.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
	
	
	
	//Blog Creation
	
	@ModelAttribute("blog")
	public Blog constructBlog(){
		return new Blog();
	}
	
	
	@RequestMapping(value="/account", method=RequestMethod.POST)
	public String doAddBlog(Model model, @Valid @ModelAttribute("blog") Blog blog, Principal principal, BindingResult result){
		if(result.hasErrors()){
			return account(model, principal);
		}
		String name = principal.getName();
		blogService.save(blog, name);
		return "redirect:/account.html";
	}
	
	//Blog Delete
	@RequestMapping("/blog/remove/{id}")
	public String removeBlog(@PathVariable int id){
		Blog blog = blogService.findOne(id);
		blogService.delete(blog);
		return "redirect:/account.html";
	}
	
	
	
	
	//User Profile
	
	@RequestMapping("/account")
	public String account(Model model, Principal principal){
		String name = principal.getName();
		model.addAttribute("user", userService.findOneWithBlogs(name));
		return "account";
	}
	

}
