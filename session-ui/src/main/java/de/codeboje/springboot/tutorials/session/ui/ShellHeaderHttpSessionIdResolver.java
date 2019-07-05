package de.codeboje.springboot.tutorials.session.ui;

import org.springframework.session.web.http.HeaderHttpSessionIdResolver;

public class ShellHeaderHttpSessionIdResolver extends HeaderHttpSessionIdResolver {

    public ShellHeaderHttpSessionIdResolver(String header) {
        super(header);
    }

    private static final String HEADER_JSESSION_ID = "jSessionID";

    public static HeaderHttpSessionIdResolver jSessionInfo() {
        return new ShellHeaderHttpSessionIdResolver(HEADER_JSESSION_ID);
    }
}
