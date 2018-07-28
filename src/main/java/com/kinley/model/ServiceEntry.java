package com.kinley.model;

import java.io.Serializable;

public class ServiceEntry implements Serializable{
	
	/**
	 *  Default Serial Version Id
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 *  Parameterized full constructor
	 *  */
	public ServiceEntry(String service, String uri, String method) {
		this.service = service;
		this.uri = uri;
		this.method = method;
	}

	private String service;
	private String uri;
	private String method;
	
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + ((service == null) ? 0 : service.hashCode());
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceEntry other = (ServiceEntry) obj;
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		if (service == null) {
			if (other.service != null)
				return false;
		} else if (!service.equals(other.service))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "ServiceEntry [service=" + service + ", uri=" + uri + ", method=" + method + "]";
	}
}