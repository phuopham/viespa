package com.spa.viespa.configs;

import com.spa.viespa.entities.Staff;
import com.spa.viespa.repositories.StaffRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StaffConfig {

    @Bean
    CommandLineRunner preDataStaff(StaffRepository repository){
        return args -> {
            Staff test1 = new Staff(
                    "Nguyen Hoang Nam",
                    LocalDate.of(1999, Month.JULY, 16),
                    "146 Truong Dinh, Hai Ba Trung, Ha Noi",
                    "0852470666",
                    "ng-hoangnam@github.com",
                    23432543524L,
                    LocalDate.of(2022, Month.DECEMBER, 2),
                    null
            );
            Staff test2 = new Staff(
                    "Phuo Pham",
                    LocalDate.of(1994, Month.JULY, 2),
                    "285 Doi Can, Hai Ba Trung, Ha Noi",
                    "0988999666",
                    "phuopham@github.com",
                    13432543832L,
                    LocalDate.of(2022, Month.OCTOBER, 30),
                    null
            );
            repository.saveAll(List.of(test1, test2));
        };
    }
}
