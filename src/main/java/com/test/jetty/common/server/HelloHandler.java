package com.test.jetty.common.server;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloHandler extends AbstractHandler {
    final String greeting;
    final String body;

    public HelloHandler() {
        this("Hello World");
    }

    public HelloHandler(String greeting) {
        this(greeting, null);
    }

    public HelloHandler(String greeting, String body) {
        this.greeting = greeting;
        this.body = body;
    }

    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException,
            ServletException {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = response.getWriter();

        out.println("<h1>" + greeting + "</h1><p>");
        if (body != null) {
            out.println(body);
        }
        System.out.println("==request.getContextPath()==" + request.getContextPath());
        System.out.println("==baseRequest.getContextPath()==" + baseRequest.getContextPath());
        System.out.println("==request.getServletPath()==" + request.getServletPath());
        System.out.println("==baseRequest.getServletPath()==" + baseRequest.getServletPath());
        System.out.println("==target==" + target);
        baseRequest.setHandled(true);
    }
}