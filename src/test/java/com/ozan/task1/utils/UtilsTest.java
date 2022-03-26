package com.ozan.task1.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.ozan.task1.model.Contributor;
import com.ozan.task1.model.GithubForm;

class UtilsTest {

	private Utils utils;

	@Test
	void it_should_write_all_to_file() throws IOException {

		// Given
		GithubForm form = new GithubForm("testOrg", "testRepo", "");
		String fileName = form.getOrganisation() + "-" + form.getRepository() + ".txt";

		Contributor c1 = new Contributor("user1", 1, "http://xxc/", "testCompy", "USA", 930);
		Contributor c2 = new Contributor("user2", 2, "http://xxc/", "testCompy2", "TR", 62);
		Contributor c3 = new Contributor("user3", 3, "http://xxc/", "testCompy3", "CHA", 3);

		List<Contributor> contributorList = List.of(c1, c2, c3);

		// When
		boolean isWritten = utils.saveToTextFile(form, contributorList);

		// Then
		Assertions.assertThat(isWritten).isEqualTo(true);

		File file = new File(fileName); 
		FileReader fr = new FileReader(file); 
		BufferedReader br = new BufferedReader(fr); 
		StringBuffer sb = new StringBuffer(); 
		String line;
		int i = 0;
		
		while((line = br.readLine())!=null)  {  
			Assertions.assertThat(line).isEqualTo(String.format("repo: %s, user: %s, location: %s, company: %s, contributions: %d", 
					form.getRepository(), 
					contributorList.get(i).getLogin(), 
					contributorList.get(i).getLocation(), 
					contributorList.get(i).getCompany(),
					contributorList.get(i).getContributions()));
			i++;
		}  
		fr.close(); 

	}

}
