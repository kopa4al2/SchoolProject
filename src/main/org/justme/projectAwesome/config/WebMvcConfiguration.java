package justme.projectAwesome.config;

import justme.projectAwesome.interceptors.SuccessMessageInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    private final SuccessMessageInterceptor successMessageInterceptor;

    public WebMvcConfiguration(SuccessMessageInterceptor successMessageInterceptor) {
        this.successMessageInterceptor = successMessageInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.successMessageInterceptor);
    }
}
