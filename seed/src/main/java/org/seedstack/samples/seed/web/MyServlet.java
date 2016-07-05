package org.seedstack.samples.seed.web;

import org.seedstack.samples.seed.guice.Greeter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/my-servlet")
public class MyServlet extends HttpServlet {
    @Inject
    private Greeter greeter;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String queryString = req.getQueryString();
        resp.setContentType("text/plain");
        resp.getWriter().print(greeter.greet(queryString.substring(queryString.indexOf("name=") + 5)));
    }
}
