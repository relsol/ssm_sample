package com.ssm.core.frame.web;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class BaseController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor());
	    binder.registerCustomEditor(Timestamp.class, new CustomTimestampEditor());
	}
	
	public final static String SUCCESS = "success";
	public final static String ERROR = "error";

}


