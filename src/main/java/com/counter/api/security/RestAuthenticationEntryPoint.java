package com.counter.api.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * The Class RestAuthenticationEntryPoint.
 * @author vinod.sharma
 */
@Component( "restAuthenticationEntryPoint" )
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	/* (non-Javadoc)
	 * @see org.springframework.security.web.AuthenticationEntryPoint#commence(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public final void commence
	    ( HttpServletRequest request, HttpServletResponse response, AuthenticationException authException )
	    throws IOException{
	      response.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized" );
	   }
}
