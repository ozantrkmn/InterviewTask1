package com.ozan.task1.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.ozan.task1.model.Contributor;
import com.ozan.task1.model.GithubForm;

public class Utils {
	
	/**
	 * saves all contributors to the text file
	 * 
	 * @param githubForm form from UI
	 * @param contributors list
	 * @return
	 */
	public static final boolean saveToTextFile(GithubForm githubForm, List<Contributor>  contributors) {
		
		String fileName = githubForm.getOrganisation() + "-" + githubForm.getRepository() + ".txt";
		
        try (FileWriter fstream = new FileWriter(fileName);
               BufferedWriter wr = new BufferedWriter(fstream)) {
               for (Contributor contributor : contributors) {
                   wr.write(String.format("repo: %s, user: %s, location: %s, company: %s, contributions: %d%n", 
                		   githubForm.getRepository(), 
                		   contributor.getLogin(), 
                		   contributor.getLocation(), 
                		   contributor.getCompany(),
                		   contributor.getContributions()));
               }
               
               wr.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
		
		
		return true;
	}
	

}
