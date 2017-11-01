package com.wust;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by IntelliJ IDEA.
 * @author : F7687967
 * Date: 2017/10/16
 * Time: 下午 03:28
 * Description: Swagger2的配置类,通过@Configuration注解，让Spring来加载该类配置,通过@EnableSwagger2注解来启用Swagger2
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        /**
         * @Description: 通过createRestApi函数创建Docket的Bean
         * elect()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现，
         * 本例采用指定扫描的包路径来定义，Swagger会扫描该包下所有Controller定义的API，并产生文档内容（除了被@ApiIgnore指定的请求）
         */
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wust.web"))
                .paths(PathSelectors.any())
                .build();

    }

    /**
     * @Description: 通过apiInfo()函数创建该Api的基本信息（这些基本信息会展现在文档页面中）
     */
    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .contact("F7687967")
                .version("1.0")
                .build();
    }
}
