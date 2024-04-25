package com.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 开启服务
 *
 * @author start
 */
@SpringBootApplication
@ComponentScan(basePackages = {
		"com.base.common.dev",
		"com.common.core",
		"com.topnetwork"})
public class RunApplicationWallet {

    public static void main(String[] args) {
		SpringApplication.run(RunApplicationWallet.class, args);
    }

}
