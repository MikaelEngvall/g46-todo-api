package se.lexicon.g46todoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "se.lexicon.g46todoapi") // Redundant
public class G46TodoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(G46TodoApiApplication.class, args);
    }

}
