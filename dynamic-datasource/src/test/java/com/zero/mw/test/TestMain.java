package com.zero.mw.test;

import java.util.concurrent.CountDownLatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

/**
 * Test main class entrance.
 * @date 2019年3月30日 下午10:05:46
 * @author zero
 */
@PropertySource(value = {"classpath:application.properties"})
@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class})
@SpringBootApplication(scanBasePackages= {"com.zero.mw.dynamic.mysql"})
public class TestMain {
	private static CountDownLatch latch = new CountDownLatch(1);
	
	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(TestMain.class, args);
		System.out.println("context inited success. bean size:" + context.getBeanDefinitionCount());
		latch.await();
	}
}
