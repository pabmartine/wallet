package com.poc.wallet.adapters.in.rest.errors;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Class that will map errors to return in REST response
 * 
 * @author pabmartine
 *
 */
@Getter
@AllArgsConstructor
public class ErrorMessage {
	private int statusCode;
	private Date timestamp;
	private String message;
	private String description;

}