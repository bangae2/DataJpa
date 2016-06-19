//package com.springapp.config.webconfig;
//
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.filter.CharacterEncodingFilter;
//import org.springframework.web.filter.DelegatingFilterProxy;
//import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;
//
//import javax.servlet.Filter;
//
///**
// * Created by bangae11 on 2016-06-19.
// */
//public class SpringServletInitializer extends AbstractDispatcherServletInitializer {
//    public SpringServletInitializer() {
//        super();
//    }
//
//    @Override
//    protected WebApplicationContext createServletApplicationContext() {
//        final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(WebMvcConfig.class);
//        return context;
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[]{"/"};
//    }
//
//    @Override
//    protected WebApplicationContext createRootApplicationContext() {
//        final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(SecurityConfig.class);
//        return context;
//    }
//
//    @Override
//    protected Filter[] getServletFilters() {
//        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter("utf-8");
//        encodingFilter.setEncoding("utf-8");
//        encodingFilter.setForceEncoding(true);
//
//        DelegatingFilterProxy filterProxy = new DelegatingFilterProxy("springSecurityFilterChain");
//
//        return new Filter[] {encodingFilter, filterProxy};
//    }
//
//}
