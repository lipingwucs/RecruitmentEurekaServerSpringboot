/*COMP303_Assignment4_Organization
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
public class OrgController {
	@Autowired
	private OrgRepository orgRepository;
	
	@Autowired
	private HttpSession session;

	@RequestMapping("/orgs_list.html")  //frontend url
	public String show(Model model) {
		List<Organization> organizations = orgRepository.findAll();
		System.out.println("got current organization list: "+ organizations);
		model.addAttribute("orgList", organizations);
		return "orgs/orgList";
	}	
	
	//Read
	@GetMapping(value = "/orgs_details.html", params = "orgId")   //frontend url
	public String getOrg(@RequestParam("orgId") int orgId, Model model) throws Exception {	
		model.addAttribute("organization", orgRepository.findById(orgId).get());
		return "orgs/orgDetails";
	}
	
	@GetMapping(value = "/orgs_add.html")  //frontend url
	public String createOrg(Model model) throws Exception {	
		model.addAttribute("organization", new Organization());
		return "orgs/orgAdd";
	}
	
	//Create
	@PostMapping("/orgs_add.html")
	public String createOrgPost(@Valid Organization organization, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "orgs/orgAdd";
		}
		orgRepository.save(organization);
		session.setAttribute("tempMsg", "Create the organization  "+organization.getOrgName() +" successfully");
		return "redirect:/orgs_list.html";	
	}
	
	//Update
	@GetMapping(value = "/orgs_update.html", params = "orgId")  //frontend url
	public String updateOrg(@RequestParam("orgId") int orgId, Model model) throws Exception {	
		model.addAttribute("organization", orgRepository.findById(orgId));
		return "orgs/orgUpdate";   
	}
	
	//Update
		@PostMapping("/orgs_update.html")
		public String updateOrgPost(@Valid Organization organization, BindingResult result, Model model) {
			if (result.hasErrors()) {
				return "orgs/orgUpdate";
			}
			orgRepository.save(organization);
			session.setAttribute("tempMsg", "Update the organization  "+organization.getOrgName()+" successfully");
			return "redirect:/orgs_list.html";	
		}
	
	//Delete	
	@RequestMapping(value = "/orgs_delete.html", method = RequestMethod.GET)
	public String deleteOrg(@RequestParam("orgId") int orgId, Model model) throws Exception {	
		Organization organization = orgRepository.findById(orgId).get();
		model.addAttribute("organization", organization);
		String message="Are you sure you want to delete this organization "+organization.getOrgName()+" ?";
		model.addAttribute("confirmMsg", message);
		return "orgs/orgDelete";
	}
	
		//DELETE post
		@RequestMapping(value = "/orgs_delete.html", method = RequestMethod.POST)
		public String deleteOrgPost(@RequestParam("orgId") int orgId, Model model) {
			Organization organization = orgRepository.findById(orgId).get();
			String orgName = organization.getOrgName();
			orgRepository.deleteById(orgId);

			session.setAttribute("tempMsg", "Deleted the organization  "+orgName+" successfully");
			return "redirect:/orgs_list.html";	
		}

}
