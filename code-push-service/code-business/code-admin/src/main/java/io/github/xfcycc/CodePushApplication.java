package io.github.xfcycc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 启动程序
 * @author caiguoyu
 * @date 2024-01-XX
 */
@SpringBootApplication(scanBasePackages = {"io.github.xfcycc"})
@EnableAspectJAutoProxy
@EnableAsync(proxyTargetClass = true)
public class CodePushApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(CodePushApplication.class);
        application.run(args);
        System.out.println("Code Push ING........");
    }

}
