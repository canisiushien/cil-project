package cil.bf.activiteApp;

import cil.bf.activiteApp.service.impl.InitalDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ActiviteAppApplication implements CommandLineRunner {

    @Autowired
    InitalDataServiceImpl service;

    public static void main(String[] args) {
        SpringApplication.run(ActiviteAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        service.saveInitDirection();
        service.saveInitAuthorities();
        service.saveInitProfil();
        service.saveInitUser();
    }

}
