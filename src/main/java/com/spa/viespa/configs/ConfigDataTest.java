/**
 * Author: Aptech - Team 1 - Project Semester 2
 * Date: 18/12/2022
 * Description: VieSpa Management Project
 */

package com.spa.viespa.configs;

import com.spa.viespa.entities.Skill;
import com.spa.viespa.entities.Staff;
import com.spa.viespa.repositories.SkillReponsitory;
import com.spa.viespa.repositories.StaffRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;

@Configuration
public class ConfigDataTest {
    @Bean
    CommandLineRunner mappingDemo(
            SkillReponsitory skillReponsitory,
            StaffRepository staffRepository){
        return args -> {
            //Add Skill Data Demo
            Skill skill1 = new Skill(
                    "Level 1",
                    "Require for beginner employee with working time period lower than 100 hours",
                    0
            );
            Skill skill2 = new Skill(
                    "Level 2",
                    "Require for intermadiate employee with working time priod from 100 to 500 hours",
                    0
            );
            Skill skill3 = new Skill(
                    "Level 3",
                    "Require for professional employee with working time priod from 500 to 2000 hours",
                    0
            );
            skillReponsitory.saveAll(List.of(skill1, skill2, skill3));
            //-------------------------------------------------

            //Add Staff Data Demo
            Staff staff1 = new Staff(
                    "Nguyen Hoang Nam",
                    LocalDate.of(1999, Month.JULY, 16),
                    "146 Truong Dinh, Hai Ba Trung, Ha Noi",
                    "0852470666",
                    "ng-hoangnam@github.com",
                    23432543524L,
                    LocalDate.of(2022, Month.DECEMBER, 2),
                    null,
                    Set.of(skill1, skill2)
            );
            Staff staff2 = new Staff(
                    "Phuo Pham",
                    LocalDate.of(1994, Month.JULY, 2),
                    "285 Doi Can, Hai Ba Trung, Ha Noi",
                    "0988999666",
                    "phuopham@github.com",
                    13432543832L,
                    LocalDate.of(2022, Month.OCTOBER, 30),
                    null,
                    Set.of(skill2, skill3)
            );

            staffRepository.saveAll(List.of(staff1, staff2));
            //----------------------------------------------
        };
    }
}
