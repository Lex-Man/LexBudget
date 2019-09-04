package org.lexusmanson.lexbudget.aspect.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerLogger {

	private final Logger LOG = LogManager.getLogger(ControllerLogger.class);
	
	@Before("execution( * org.lexusmanson.lexbudget.controller.*.*(..))")
	public void logTest() {
		System.out.println("Aspect Called");
		LOG.error("There has been a massive error.");
	}
	
}
