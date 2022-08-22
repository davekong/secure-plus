package com.mos.secure.ext.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 敏感starter应用程序
 *
 * @author 小尘哥
 * @date 2022/08/09
 * @version $Id: $Id
 */
@SpringBootApplication
public class SensitiveSpringBootStarterApplication {

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        SpringApplication.run(SensitiveSpringBootStarterApplication.class, args);
    }

}
