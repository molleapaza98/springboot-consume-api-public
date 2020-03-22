package com.example.project;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NewsController {

	@Autowired
	private  NewsService service;

	@RequestMapping(value = "/news/topstories",method = RequestMethod.GET)
	public News getNews() throws Exception{
		return service.getTopStories();
	}

}