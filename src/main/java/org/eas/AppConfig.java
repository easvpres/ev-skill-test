package org.eas;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author eas
 */
@Configuration
@EnableWebMvc
@ComponentScan("org.eas")
public class AppConfig {
}
