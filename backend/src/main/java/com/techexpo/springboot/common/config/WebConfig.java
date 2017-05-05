package com.techexpo.springboot.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.techexpo.springboot.config.RedisApplicationConfig;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.techexpo.springboot.config")
@Import({RedisApplicationConfig.class})
public class WebConfig extends WebMvcConfigurerAdapter  {

}
