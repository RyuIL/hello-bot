package com.example.hellobot.config;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.TypeNameProviderPlugin;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

/**
 * @Author Ryu
 * @create 2020/10/18 6:14 오후
 */

@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
public class CustomTypeNameProvider implements TypeNameProviderPlugin {
    @Override
    public String nameFor(Class<?> type) {
        String fullName = type.getName();
        return fullName.substring(fullName.lastIndexOf(".") + 1);
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }
}
