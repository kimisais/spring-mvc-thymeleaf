package com.sais.springmvc.omsdev.util.controllers;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sais.springmvc.omsdev.util.domain.MQForm;
import com.sais.springmvc.omsdev.util.service.MQPostService;
@Controller
@RequestMapping("omsdevutils")
public class MQController {
	private static final Logger logger = LoggerFactory.getLogger(MQController.class);	

	@Autowired
	MQPostService mqService;
	
	@GetMapping("post-to-mq")
	public ModelAndView createUserView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("post-to-mq");
		mav.addObject("mqform", new MQForm());
		mav.addObject("allMQNames", getMQNames());
		mav.addObject("allEnvironments", getEnvironments());
		return mav;
	}
	
	@PostMapping("post-to-mq")
	public ModelAndView postToMQ(@Valid MQForm mqform, BindingResult result) {
		
		ModelAndView mav = new ModelAndView();
				
		if(result.hasErrors()) {
			logger.info("Validation errors while submitting form.");
			mav.setViewName("post-to-mq");
			mav.addObject("allMQNames", getMQNames());
			mav.addObject("allEnvironments", getEnvironments());
			return mav;
		}		

		mav.addObject("mqform", mqform);
		mav.addObject("message", mqform.getMessage());
		mav.addObject("queueName", mqform.getQueueName());
		mav.addObject("environment", mqform.getEnvironment());

		
		mqService.postMessageToMQ(mqform);
		
		mav.setViewName("posted-to-mq");
		logger.info("Form submitted successfully.");	    
		return mav;
	}	
	
	private List<String> getMQNames() {
	    List<String> list = new ArrayList<>();
	    list.add("Create Order Q");
	    list.add("Ship Update Q");
	    list.add("Carrier Update Q");
	    return list;
	}
	
	private List<String> getEnvironments() {
	    List<String> list = new ArrayList<>();
	    list.add("Minor QA");
	    list.add("Major QA");
	    list.add("Production");
	    return list;
	}
} 