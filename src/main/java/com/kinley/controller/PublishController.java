package com.kinley.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kinley.model.ServiceEntry;
import com.kinley.service.StatisticsService;

@RestController
public class PublishController {
	
	@Autowired
	StatisticsService statisticsService;

	@GetMapping(value="/publish")
	public Map<ServiceEntry,Integer> publishStatisticalData(){
		return statisticsService.publishStatisticsData();
	}

}
