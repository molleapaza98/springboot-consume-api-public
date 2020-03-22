package com.example.project;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NewsService {

	private String apiKey= "Kp2Z2LsPI1JBpTW4GAFDR3G4ORojqFMA";

	public News getTopStories()  throws  Exception{

		List<News> topStories = new ArrayList<>();
		RestTemplate restTemplate = new RestTemplate();
		News news = new News();
		String getUrl="https://api.nytimes.com/svc/topstories/v2/home.json?api-key="+apiKey;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<Map> newList = restTemplate.exchange(getUrl, HttpMethod.GET,entity,Map.class);
		JSONObject jsonObject;

		if(newList.getStatusCode()==HttpStatus.OK){
			jsonObject = new JSONObject(newList.getBody());
			JSONArray jsonArray = jsonObject.getJSONArray("results");
			Results[] results = new Results[jsonArray.length()];
			for(int i=0; i<jsonArray.length(); i++){
				news.setTitle(jsonArray.getJSONObject(i).get("title").toString());
				news.setSection(jsonArray.getJSONObject(i).get("section").toString());
				String title = jsonArray.getJSONObject(i).get("title").toString();
				results[i]=new Results();
				results[i].setTitle(title);
				news.setResults(results);
				topStories.add(news);
			}
		}
		return topStories.get(0);
	}
}