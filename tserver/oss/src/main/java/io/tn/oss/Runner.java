package io.tn.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Runner {
    public static void main(String[] args) {
        SpringApplication.run(Runner.class);
        System.getProperties()
                .forEach((k, v) -> {
                    System.out.println(k + " = " + v);
                });

        System.out.println();
        var classes = System.getProperty("java.class.path");
        var b = classes.split(";");
        Arrays.stream(b).filter(s -> !s.contains(".jar")).forEach(System.out::println);

        var path = Runner.class.getClassLoader().getResource("a.txt");
        System.out.println(path);
    }
}