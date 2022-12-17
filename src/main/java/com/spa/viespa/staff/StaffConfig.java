package com.spa.viespa.staff;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.util.Calendar.JULY;

@Configuration
public class StaffConfig {

    @Bean
    CommandLineRunner commandLineRunner(StaffRepository repository){
        return args -> {
            Staff phuong = new Staff(
                    "phuong",
                    LocalDate.of(2000, JULY, 2),
                    23432543524L
            );
            repository.saveAll(List.of(phuong));
        };
    }
}
