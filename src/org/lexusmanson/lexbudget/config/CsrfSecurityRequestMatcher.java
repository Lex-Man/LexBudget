package org.lexusmanson.lexbudget.config;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

@Component
public class CsrfSecurityRequestMatcher implements RequestMatcher {
	private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
    private RegexRequestMatcher unprotectedMatcher = new RegexRequestMatcher("/api/*", null);
	
	
	@Override
	public boolean matches(HttpServletRequest request) {
		System.out.println("The method is: " + request.getMethod());
		System.out.println("The request is: " + request.getRequestURI());
//		if(allowedMethods.matcher(request.getMethod()).matches()) {
//			return false;
//		}
//		return !unprotectedMatcher.matches(request);
		
		boolean matched = Pattern.matches(".*api.*", request.getRequestURI());
		
		if(matched) {
			System.out.println("Returning false");
			return false;
		} else {
			System.out.println("Returning true");
			return true;
		}
		
	}

}
