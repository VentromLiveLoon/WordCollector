package edu.csmz.ventrom.wordcollector.config;

import edu.csmz.ventrom.wordcollector.interceptor.WebInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    WebInterceptor webInterceptor;
//    Intercepted api path
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webInterceptor)
                .addPathPatterns("/word/**");
    }
}
