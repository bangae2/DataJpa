package com.springapp.config.webconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * Created by bangae1 on 2016-06-13.
 */
public class WebInitializer implements WebApplicationInitializer {
    private ServletContext servletContext;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        this.servletContext = servletContext;
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(WebMvcConfig.class);
        applicationContext.setConfigLocation("com.springapp.config");
        servletContext.addListener(new ContextLoaderListener(applicationContext));

        /* Dispatcher Servlet*/
        ServletRegistration.Dynamic servlet = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(applicationContext));
        servlet.setLoadOnStartup(1);
        servlet.setAsyncSupported(true);
        servlet.addMapping("/");

        /* Encoding Filter */
        FilterRegistration.Dynamic filter =  servletContext.addFilter("EncodingFilter", new CharacterEncodingFilter());
        filter.setAsyncSupported(true);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/");
        filter.setInitParameter("encoding", "UTF-8");
        filter.setInitParameter("forceEncoding", "true");

        /* Spring Security Filter */
        FilterRegistration.Dynamic filterChain =  servletContext.addFilter("springSecurityFilterChain",new DelegatingFilterProxy());
        filterChain.setAsyncSupported(true);
        filterChain.addMappingForUrlPatterns(EnumSet.of(DispatcherType.ERROR, DispatcherType.FORWARD, DispatcherType.REQUEST), true, "/*");

    }

    public ServletContext getServletContext() {
        return servletContext;
    }
}
