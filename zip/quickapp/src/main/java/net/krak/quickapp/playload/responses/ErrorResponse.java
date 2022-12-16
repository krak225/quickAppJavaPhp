package net.krak.quickapp.playload.responses;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "error")
public class ErrorResponse 
{

	public ErrorResponse(String message) {
		 super();
		 this.message = message;
	}

	public ErrorResponse(String message, List<Map<String,String>> details) {
	    super();
	    this.message = message;
	    this.details = details;
	}

	//response status
	private int status = 0;
	
	//General error message about nature of error
	private String message;
	 
	//Specific errors in API request processing
	private List<Map<String,String>> details;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Map<String, String>> getDetails() {
		return details;
	}

	public void setDetails(List<Map<String, String>> details) {
		this.details = details;
	}

}