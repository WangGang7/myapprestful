package cn.nawa.restful;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication
//@EnableSwagger2
//@MapperScan("cn.nawa.restful.mapper")
//public class RestfulApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(RestfulApplication.class, args);
//	}
//}

@SpringBootApplication
@EnableSwagger2
@MapperScan("cn.nawa.restful.mapper")
public class RestfulApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(RestfulApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(RestfulApplication.class, args);
	}
}
