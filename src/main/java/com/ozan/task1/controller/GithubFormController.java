package com.ozan.task1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

import com.ozan.task1.model.Contributor;
import com.ozan.task1.model.GithubForm;
import com.ozan.task1.service.IGithubRepoService;

@Controller
public class GithubFormController {
	
    @Autowired
    private IGithubRepoService githubRepoService;
	
    @GetMapping("/getRepoForm")
    public String showSignUpForm(GithubForm githubForm) {
        return "get-repo";
    }
    
    @PostMapping("/getRepo")
    public String getRepo(GithubForm githubForm, BindingResult result, Model model) {

        if(StringUtils.isEmptyOrWhitespace(githubForm.getOrganisation()) || StringUtils.isEmptyOrWhitespace(githubForm.getRepository())) {
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