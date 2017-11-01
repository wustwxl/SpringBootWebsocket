package com.wust;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@MapperScan("com.wust.dao")
public class DemoApplication {

	public static void main(String[] args) {

		//常规开启Banner
		//SpringApplication.run(DemoApplication.class, args);


		//修改Banner的模式为OFF
		SpringApplicationBuilder builder = new SpringApplicationBuilder(DemoApplication.class);
		builder.bannerMode(Banner.Mode.OFF).run(args);

	}
}
