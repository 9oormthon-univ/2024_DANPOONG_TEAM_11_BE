package shop.nongdam.nongdambackend.global.config;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import shop.nongdam.nongdambackend.global.annotationresolver.CurrentMemberEmailArgumentResolver;

@Configuration
@RequiredArgsConstructor
public class AnnotationWebConfig implements WebMvcConfigurer {

    private final CurrentMemberEmailArgumentResolver currentMemberEmailArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentMemberEmailArgumentResolver);
    }
}
