package com.ssm.core.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class QuartzTask {

	private final static Logger logger = LoggerFactory.getLogger(QuartzTask.class);
	
	public void taskOne(){
		System.out.println("========================taskOne");
	}
	
	public void taskTwo(){
		System.out.println("========================taskTwo");
	}
}
