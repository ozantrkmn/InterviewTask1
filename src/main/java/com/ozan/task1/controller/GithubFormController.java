package com.ozan.task1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ozan.task1.model.Contributor;
import com.ozan.task1.model.GithubForm;
import com.ozan.task1.service.IGithubRepoService;
import com.ozan.task1.utils.Utils;

@Controller
public class GithubFormController {
	
    @Autowired
    private ApplicationContext appContext;
	
    @GetMapping("/getRepoForm")
    public String showSignUpForm(GithubForm githubForm) {
        return "get-repo";
    }
    
    @PostMapping("/getRepo")
    public String getRepo(GithubForm githubForm, BindingResult result, Model model) {
    	
    	IGithubRepoService githubRepoService = (IGithubRepoService) appContext.getBean("githubRepoService");

        if(Utils.isEmpty(githubForm.getOrganisation()) || Utils.isEmpty(githubForm.getRepository())) {
        	return "get-repo";
        }else{
        	List<Contributor> responseList = githubRepoService.listAllContributors(githubForm);
		    model.addAttribute("contributors", responseList);
		    model.addAttribute("repo", githubForm.getRepository());
		    return "contributors";
        	
        }
    }
    
    @GetMapping("/index")
    public String showIndex(Model model) {
        return "index";
    }

}