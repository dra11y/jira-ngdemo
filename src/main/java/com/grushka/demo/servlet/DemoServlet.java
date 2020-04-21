package com.grushka.demo.servlet;

import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.atlassian.templaterenderer.TemplateRenderer;

@Scanned
public class DemoServlet extends HttpServlet{
    private static final Logger log = LoggerFactory.getLogger(DemoServlet.class);

    @ComponentImport
    private final TemplateRenderer templateRenderer;

    public DemoServlet(TemplateRenderer templateRenderer) {
        this.templateRenderer = templateRenderer;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html");
        templateRenderer.render("index.vm", resp.getWriter());
    }

}