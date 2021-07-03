package main.java.com.enterprise.sunchip;

import main.java.com.enterprise.sunchip.filters.AdminFilter;
import main.java.com.enterprise.sunchip.filters.CustomerFilter;
import main.java.com.enterprise.sunchip.filters.DriverFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminFilter()).addPathPatterns("/Admin/**").excludePathPatterns("/Customer/**").excludePathPatterns("/Driver/**");
        registry.addInterceptor(new DriverFilter()).addPathPatterns("/Customer/**").excludePathPatterns("/Admin/**").excludePathPatterns("/Driver/**");
        registry.addInterceptor(new CustomerFilter()).addPathPatterns("/Driver/**").excludePathPatterns("/Admin/**").excludePathPatterns("/Customer/**");
    }
}
