package com.ozan.task1.service;

import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.ozan.task1.client.GithubRepoClient;
import com.ozan.task1.model.Contributor;
import com.ozan.task1.model.GithubForm;

@ExtendWith(MockitoExtension.class)
class GithubRepoServiceTest {
	
	@InjectMocks
	private GithubRepoService service;
	
	@Mock
	private GithubRepoClient client;
	
	@Test
	void it_should_return_null_when_contributors_empty(){
		
		//Given
		GithubForm form = new GithubForm();
		
		form.setOrganisation("apache");
		form.setRepository("commons-lang");
		
		List<Contributor> contributorsList = new ArrayList<>();

		org.mockito.BDDMockito.given(client.getAllContributors(form)).willReturn(contributorsList);
		
		//When
		List<Contributor> detailedContributorsList = service.listAllContributors(form);
		
		
		//Then
		Assertions.assertThat(detailedContributorsList).isNull();
		
	}
	
	@Test
	void it_should_return_detailed_contributors() {
		
		//Given
		GithubForm form = new GithubForm();
		
		form.setOrganisation("apache");
		form.setRepository("commons-lang");
		
		Contributor c1 = new Contributor("user1", 1, "http://xxc/", null, null, 66);
		Contributor c2 = new Contributor("user2", 2, "http://xxc/", null, null, 271);
		Contributor c3 = new Contributor("user3", 3, "http://xxc/", null, null, 3);
		
		List<Contributor> contributorList = List.of(c1, c2, c3);
		
		org.mockito.BDDMockito.given(client.getAllContributors(form)).willReturn(contributorList);
		

		//When
		List<Contributor> detailedContributorsList = service.listAllContributors(form);
		
		
		
		
		// Then
		org.mockito.BDDMockito.verify(client, times(1)).getContributorDetail(contributorList, form.getPersonelToken());
		
		Assertions.assertThat(detailedContributorsList).hasSize(3);
		
		Assertions.assertThat(detailedContributorsList.get(0)).isEqualTo(c2);
		Assertions.assertThat(detailedContributorsList.get(1)).isEqualTo(c1);
		Assertions.assertThat(detailedContributorsList.get(2)).isEqualTo(c3);
		
		
	}

}
