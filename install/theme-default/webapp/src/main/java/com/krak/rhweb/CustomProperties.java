package com.krak.rhweb;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;//ça ne marche pas, peut être pas bien chargé

@Configuration
@ConfigurationProperties(prefix = "com.krak.rhweb")
@Data //ça ne marche pas
public class CustomProperties {

    private String apiUrl;

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	
	

}