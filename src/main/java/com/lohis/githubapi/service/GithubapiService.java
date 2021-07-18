package com.lohis.githubapi.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lohis.githubapi.Repo;

@Service
public class GithubapiService {

	private static String GITHUB_URL = "https://api.github.com/users/abhi4java8/repos";

	private static String GITHUB_CONTENTS_URL = "https://api.github.com/repos/abhi4java8/CodingChallenge/contents/src/main/resources/application-local.yml";

	RestTemplate restTemplate = new RestTemplate();

	@PostConstruct
	public void fetchGithubData() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(GITHUB_URL)).build();
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		ResponseEntity<Repo[]> repos = restTemplate.exchange(GITHUB_URL, HttpMethod.GET, entity, Repo[].class);

		Repo[] repoArr = repos.getBody();

		System.out.println("post construct is called...");
		for (Repo repo : repoArr) {
			System.out.println("Id   : " + repo.getId());
			System.out.println("Name : " + repo.getName());
		}

		// getting and decoding contents from github
		ResponseEntity<Repo> reposContents = restTemplate.exchange(GITHUB_CONTENTS_URL, HttpMethod.GET, entity,
				Repo.class);

		Repo repo = reposContents.getBody();
		String content = repo.getContent();
		System.out.println(content);
		
		byte[] decodedContent = Base64.getMimeDecoder().decode(content.getBytes());//Base64.getMimeDecoder().decode(content);
//						 Base64.decode(repo.getContent()).toString();
		System.out.println(decodedContent);
	}

}
