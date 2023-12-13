package com.example.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestDockerApplication {

	public static void main(String[] args) {
		SpringApplication.from(DockerApplication::main).with(TestDockerApplication.class).run(args);
	}

}
