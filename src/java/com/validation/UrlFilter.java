package com.validation;

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class UrlFilter implements Filter, Serializable {
    // @ManagedProperty("#{param.tokendURl}")

    private String url;
    private static final long serialVersionUID = 1094801825228386463L;

    public UrlFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        System.out.println("FILTERING__");
        try {

            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);
            String reqURI = reqt.getRequestURI();
            System.out.println("==========" + reqURI);
            boolean isRegister = reqt.getRequestURI().endsWith("/register.xhtml");
            System.out.println(")))))))reg))))))))" + isRegister);
            if (isRegister) {
                url = reqt.getRequestURI();
                url = reqt.getRequestURI().replace("/OnthatileWebApplication/faces/", "");
                String finalurl = url.replace("/register.xhtml", "");
                ses.setAttribute("url", finalurl);
                String replaceAll = reqt.getRequestURI().replaceAll(reqURI, "/OnthatileWebApplication/faces/register.xhtml");
                RequestDispatcher rd = request.getRequestDispatcher("/faces/register.xhtml");
                rd.forward(request, response);
            }
            if (!isRegister) {
                if (reqURI.contains("/login.xhtml") || (ses != null && ses.getAttribute("username") != null) || reqURI.contains("/public/") || reqURI.contains("javax.faces.resource")) {
                    System.err.println("When garnting___" + reqURI);
                    System.out.println("ganting login__");
                    System.out.println(" Req " + request.toString() + " respose " + response.toString());
                    chain.doFilter(request, response);
                } else {
                    System.out.println("login redirect__");
                    resp.sendRedirect(reqt.getContextPath() + "/faces/login.xhtml");
                }
            }
        } catch (IOException | ServletException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }
}