package com.test.jetty.common.server;

import org.eclipse.jetty.servlet.ServletHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DumpServlet extends HttpServlet {


    private static final long serialVersionUID = -2630631577998543292L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);

        System.out.println("===dump haha===");

        response.getWriter().println("<h1>Hello from DumpServlet</h1>");
        System.out.println("===dump 准备睡觉===");
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

    }
}
