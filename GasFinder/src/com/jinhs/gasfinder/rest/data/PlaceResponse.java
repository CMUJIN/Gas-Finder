package com.jinhs.gasfinder.rest.data;

import java.util.List;

public class PlaceResponse {
	private List<Object> debug_info;
	private List<Object> html_attributions;
	private String next_page_token;
	public List<Object> getDebug_info() {
		return debug_info;
	}
	public void setDebug_info(List<Object> debug_info) {
		this.debug_info = debug_info;
	}
	public List<Object> getHtml_attributions() {
		return html_attributions;
	}
	public void setHtml_attributions(List<Object> html_attributions) {
		this.html_attributions = html_attributions;
	}
	public String getNext_page_token() {
		return next_page_token;
	}
	public void setNext_page_token(String next_page_token) {
		this.next_page_token = next_page_token;
	}
	public List<ResultData> getResults() {
		return results;
	}
	public void setResults(List<ResultData> results) {
		this.results = results;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private List<ResultData> results;
	private String status;
}
