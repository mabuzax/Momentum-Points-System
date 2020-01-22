package com.momentum.points.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class ContentNotFoundException extends Exception{
	
 private static final long serialVersionUID = 1L;
 
 public ContentNotFoundException(String message) throws Exception{
     super(message);
    }
}