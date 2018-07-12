package com.cubic.api;

import static com.cubic.api.core.ProjectConstant.MAPPER_PACKAGE;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author fei.yu
 * @date 2018/06/09
 */
@EnableEncryptableProperties
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(basePackages = MAPPER_PACKAGE)
public class Application {
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}