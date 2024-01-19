package com.encore.basic.servletjsp;


import com.encore.basic.domain.Hello;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello-servlet-rest-get")
public class HelloServletRestGet extends HttpServlet {
    // 객체 조립 -> resp에 조립 1.content type json , 2. out.print(hello)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Hello hello = new Hello();
        hello.setName("재영");
        hello.setEmail("재영@네이버");
        hello.setPasssWd("123");
        ObjectMapper objectMapper = new ObjectMapper();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("json");
        PrintWriter out = resp.getWriter();
        out.print(objectMapper.writeValueAsString(hello));
    }
}
