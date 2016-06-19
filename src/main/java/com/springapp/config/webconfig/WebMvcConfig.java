package com.springapp.config.webconfig;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.UrlTemplateResolver;


/**
 * Created by bangae1 on 2016-06-13.
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.springapp"})
public class WebMvcConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    private ApplicationContext applicationContext;


    public WebMvcConfig() {
        super();
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("Messages");
        return resourceBundleMessageSource;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /* tiles view resolver*/
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.tiles().viewClass(TilesView.class);
//    }
//
//    @Bean
//    public TilesConfigurer tilesConfigurer() {
//        TilesConfigurer configurer = new TilesConfigurer();
//        configurer.setDefinitions("/WEB-INF/tiles-definitions.xml");
//        return configurer;
//    }

    @Bean
    public UrlTemplateResolver urlTemplateResolver() {
        return new UrlTemplateResolver();
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("/WEB-INF/html/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        // Template cache is true by default. Set to false if you want
        // templates to be automatically updated when modified.
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        /* thymeleaf-springsecurity 4 support*/
//        templateEngine.setEnableSpringELCompiler(true); // Compiled SpringEL should speed up executions
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.addDialect(new SpringSecurityDialect());
        templateEngine.addDialect(new LayoutDialect());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setCharacterEncoding("utf-8");
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setOrder(1);
        return viewResolver;
    }

}
