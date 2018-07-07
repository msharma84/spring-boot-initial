package com.kinley.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/rest/sample")

@Api(value = "Sample Rest Controller")
public class SampleRestController {
	
	 private static final Logger logger = LoggerFactory.getLogger(SampleRestController.class);
	
	 @ApiOperation(value = "Returns Hello World")
	    @ApiResponses(
	            value = {
	                    @ApiResponse(code = 200, message = "Successful Hello World")
	            }
	    )
	 
	@GetMapping
    public String hello() {
        return "Hello World";
    }
	
	@ApiOperation(value="Returns Hello World")
	@PostMapping
	public String helloPost(@RequestBody final String hello){
		return hello;
	}
	
	@ApiOperation(value = "Returns Hello World")
    @PutMapping("/put")
    public String helloPut(@RequestBody final String hello) {
        return hello;
    }
}
