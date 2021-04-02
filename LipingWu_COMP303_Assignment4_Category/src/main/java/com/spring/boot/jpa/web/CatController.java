/*COMP303_Assignment4_Category
 *Liping Wu 300958061
 4-6-2020
 * */

package com.spring.boot.jpa.web;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CatController {
	@Autowired
	private CatRepository catRepository;
	
	@Autowired
	private HttpSession session;

	@RequestMapping("/cats_list.html")  //frontend url
	public String show(Model model) {
		List<Category> cats = catRepository.findAll();
		System.out.println("got current categories list: "+ cats);
		model.addAttribute("catList", cats);
		return "cats/catList";
	}	
	
	//Read
	@GetMapping(value = "/cats_details.html", params = "jobCatId")   //frontend url
	public String getCat(@RequestParam("jobCatId") int jobCatId, Model model) throws Exception {	
		model.addAttribute("category", catRepository.findById(jobCatId).get());
		return "cats/catDetails";
	}
	
	@GetMapping(value = "/cats_add.html")  //frontend url
	public String createCat(Model model) throws Exception {	
		model.addAttribute("category", new Category());
		return "cats/catAdd";
	}
	
	//Create
	@PostMapping("/cats_add.html")
	public String createCatPost(@Valid Category category, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "cats/catAdd";
		}			 
		catRepository.save(category);
		session.setAttribute("tempMsg", "Create the category  "+category.getCatName()+" successfully");
		return "redirect:/cats_list.html";	
	}
	
	//Update
	@GetMapping(value = "/cats_update.html", params = "jobCatId")  //frontend url
	public String updateCat(@RequestParam("jobCatId") int jobCatId, Model model) throws Exception {	
		model.addAttribute("category", catRepository.findById(jobCatId));
		return "cats/catUpdate";   
	}
	
	//Update
		@PostMapping("/cats_update.html")
		public String updateCatPost(@Valid Category category, BindingResult result, Model model) {
			if (result.hasErrors()) {
				return "cats/catUpdate";
			}			 
			catRepository.save(category);
			session.setAttribute("tempMsg", "Update the category  "+category.getCatName()+" successfully");
			return "redirect:/cats_list.html";	
		}
	
	//Delete	
	@RequestMapping(value = "/cats_delete.html", method = RequestMethod.GET)
	public String deleteCat(@RequestParam("jobCatId") int jobCatId, Model model) throws Exception {	
		Category category = catRepository.findById(jobCatId).get();
		model.addAttribute("category", category);
		String message="Are you sure you want to delete this category "+category.getCatName()+" ?";
		model.addAttribute("confirmMsg", message);
		return "cats/catDelete";
	}
	
	//DELETE post
	@RequestMapping(value = "/cats_delete.html", method = RequestMethod.POST)
	public String deleteCatPost(@RequestParam("jobCatId") int jobCatId, Model model) {
		Category category = catRepository.findById(jobCatId).get();
		String catName = category.getCatName();
		catRepository.deleteById(jobCatId);

		session.setAttribute("tempMsg", "Deleted the category  "+catName+" successfully");
		return "redirect:/cats_list.html";	
	}

}
