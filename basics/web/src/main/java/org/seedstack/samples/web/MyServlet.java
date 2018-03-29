/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.web;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.seedstack.seed.Application;

@WebServlet("/my-servlet")
public class MyServlet extends HttpServlet {
    @Inject
    private Application application;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String queryString = req.getQueryString();
        resp.setContentType("text/plain");
        String name = queryString.substring(queryString.indexOf("name=") + 5);
        resp.getWriter().print(String.format("Hello %s from %s!", name, application.getId()));
    }
}
