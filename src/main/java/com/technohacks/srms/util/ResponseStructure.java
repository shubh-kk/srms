package com.technohacks.srms.util;

import lombok.Data;
@Data
public class ResponseStructure<T> {
	private String message ;
	private int status ;
	private T data ;
}
