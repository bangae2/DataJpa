package com.springapp.config.webconfig;

import com.springapp.config.interceptor.SecurityInterceptor;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

import java.util.Properties;


/**
 * Created by bangae1 on 2016-06-13.
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.springapp"})
@PropertySource("classpath:application.properties")
public class WebMvcConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    @Autowired
    private Environment env;

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
        registry.addResourceHandler(new String[] {"/resources/**"}).addResourceLocations(new String[]{"/resources/"});
        registry.addResourceHandler(new String[] {"/attach/**"}).addResourceLocations(new String[]{"/attach/"});
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

//    @Bean
//    public UrlTemplateResolver urlTemplateResolver() {
//        return new UrlTemplateResolver();
//    }

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

    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
        mapping.setInterceptors(new Object[]{new SecurityInterceptor()});
        return mapping;
    }

    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("mail.host"));
        mailSender.setPort(Integer.parseInt(env.getProperty("mail.port")));
        mailSender.setUsername(env.getProperty("mail.username"));
        mailSender.setPassword(env.getProperty("mail.password"));
        mailSender.setProtocol(env.getProperty("mail.protocol"));
        Properties javaMailProperties = new Properties();
        javaMailProperties.setProperty("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
        javaMailProperties.setProperty("mail.smtp.ssl.trust", env.getProperty("mail.smtp.ssl.trust"));
        javaMailProperties.setProperty("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;

    }
/*
ffmpeg -i "C:\attach\HeroesSeason1\Heroes.S01E01.720p.HDTV.x264.mp4" -an -filter scale=768:-1 -ss 00:10:01 -r 1 -vframes 1 -y "%d.jpg"
*
	echo ffmpeg start

	ffmpeg -i "%dir%\%%i" -an -filter scale=768:-1 -ss 00:10:01 -r 1 -vframes 1 -y "%d.jpg"

	echo ffmpeg end
* */
}
