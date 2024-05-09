package pl.zajavka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.zajavka.api.controller.HomeController;

@SpringBootApplication
public class CarDealershipApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarDealershipApplication.class, args);
    }
}
