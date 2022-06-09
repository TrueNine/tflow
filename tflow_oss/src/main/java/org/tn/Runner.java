package org.tn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Runner {
    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
        String tsx = """
                <a>
                  ${formControlName="control"} 
                </a>
                """;

    }
}
