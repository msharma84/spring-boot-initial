package com.kinley.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kinley.model.ServiceEntry;

@Service
public class StatisticsService {

	private static final Logger logger = LoggerFactory.getLogger(StatisticsService.class);
	
	private Map<ServiceEntry,Integer> statisticsMap = new HashMap<>();
	
	private static int count = 0;
	
	public void add(String serviceName, String uri, String method){

		ServiceEntry entry = new ServiceEntry(serviceName,uri,method);
		if(statisticsMap.isEmpty()){
			count = count +1;
			statisticsMap.put(entry, count);
		}else{
			Set<ServiceEntry> set= statisticsMap.keySet();
			Iterator<ServiceEntry> itr = set.iterator();
			while(itr.hasNext()){
				ServiceEntry stats = itr.next();
				if(stats.getUri().equals(uri) && stats.getMethod().equals(method)){
					count = count +1;
					statisticsMap.put(entry, count);
				}
			}
		}
	}
	
	public Map<ServiceEntry,Integer> publishStatisticsData(){
		return statisticsMap;
	}
}
