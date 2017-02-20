package com.team1.webservice.jsonbean;

import org.codehaus.jackson.annotate.JsonProperty;

public class RequestBean<T> {
	
	@JsonProperty("url")
	private String uri;
	
	@JsonProperty("method")
	private String method;
	
	@JsonProperty("headers")
	private HeaderBean heads;
	
	@JsonProperty("json")
	private T t;

	public String getUri() {
		return uri;
	}

	public String getMethod() {
		return method;
	}

	public HeaderBean getHeads() {
		return heads;
	}

	public T getT() {
		return t;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setHeads(HeaderBean heads) {
		this.heads = heads;
	}

	public void setT(T t) {
		this.t = t;
	}
}
