package epam.com.practice.trainerservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    public static final String TRAINER_WORKLOAD_TAG = "gym-app-service";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("epam.com.cractice.controller"))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag(TRAINER_WORKLOAD_TAG, "the Trainer Workload Service API with description api tag"));
    }
}
