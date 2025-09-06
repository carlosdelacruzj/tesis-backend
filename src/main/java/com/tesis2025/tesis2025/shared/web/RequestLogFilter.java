package com.tesis2025.tesis2025.shared.web;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class RequestLogFilter implements Filter {
  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest r = (HttpServletRequest) req;
    System.out.printf("[BACK] %s %s%n", r.getMethod(), r.getRequestURI());
    chain.doFilter(req, res);
  }
}
