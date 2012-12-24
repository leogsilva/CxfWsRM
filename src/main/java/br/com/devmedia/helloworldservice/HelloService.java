package br.com.devmedia.helloworldservice;

import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
//@org.apache.cxf.interceptor.InInterceptors(interceptors = { "org.apache.cxf.interceptor.LoggingInInterceptor" })
//@org.apache.cxf.interceptor.OutInterceptors(interceptors = { "org.apache.cxf.interceptor.LoggingOutInterceptor" })
public interface HelloService {
	String NS = "http://helloworldservice.devmedia.com.br";

	@WebResult(name = "response")
	public abstract PersonInfo digaOla(PersonInfo info) ;
	
	@WebResult(name= "response")
	public abstract PersonInfo digaAdeus(PersonInfo info) throws InvalidPersonException ;
}