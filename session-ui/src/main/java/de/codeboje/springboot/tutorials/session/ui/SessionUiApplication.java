package de.codeboje.springboot.tutorials.session.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@SpringBootApplication
@EnableWebSecurity
@RestController
public class SessionUiApplication {

	//https://github.com/spring-projects/spring-session/pull/204/commits/6326e8122209cc151fb2ae745dd396b549ec6606

	
	// disable base64 encoding of the sessionId in the cookie for demostration
//	@Bean
//	public HttpSessionIdResolver httpSessionIdResolver() {
//		CookieHttpSessionIdResolver resolver = new CookieHttpSessionIdResolver();
//		DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
//		cookieSerializer.setUseBase64Encoding(false);
//		resolver.setCookieSerializer(cookieSerializer);
//	    return resolver; 
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(SessionUiApplication.class, args);
	}

	@GetMapping("/name")
	public String getName(Principal principal) {
		return principal.getName();
	}

	@Autowired
	HttpSession session;

	@GetMapping("/id")
	public String getId() {
		return session.getId();
	}

	@GetMapping("/signout")
	public String logout() {
		session.invalidate();
		return "logged out";
	}

	@Bean
	public HttpSessionIdResolver httpSessionIdResolver() {
		return ShellHeaderHttpSessionIdResolver.jSessionInfo();
	}

}
