package com.geccocrawler.gecco.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.geccocrawler.gecco.GeccoEngine;

@SpringBootApplication
@Configuration
public class App {

	@Bean
	public SpringGeccoEngine initGecco() {
		return new SpringGeccoEngine() {
			@Override
			public void init() {
				GeccoEngine.create()
				.pipelineFactory(springPipelineFactory)
				.classpath("com.geccocrawler.gecco.spring")
					//入口，从这个url开始爬取
				.start("https://doutushe.com/portal/index/index/p/1")
				.loop(false)
				.start();
			}
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
}
