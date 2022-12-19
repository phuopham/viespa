/**
 * Author: Aptech - Team 1 - Project Semester 2
 * Date: 18/12/2022
 * Description: VieSpa Management Project
 */

package com.spa.viespa.configs;

import com.spa.viespa.entities.ServiceBundle;
import com.spa.viespa.entities.Skill;
import com.spa.viespa.entities.Staff;
import com.spa.viespa.repositories.ServiceBundleRepository;
import com.spa.viespa.repositories.SkillRepository;
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
            SkillRepository skillRepository,
            StaffRepository staffRepository,
            ServiceBundleRepository serviceBundleRepository){
        return args -> {
            //Add Skill Data Demo
            Skill skill1 = new Skill(
                    "Level 1",
                    "Require for beginner employee with working time period lower than 100 hours"
            );
            Skill skill2 = new Skill(
                    "Level 2",
                    "Require for intermadiate employee with working time priod from 100 to 500 hours"
            );
            Skill skill3 = new Skill(
                    "Level 3",
                    "Require for professional employee with working time priod from 500 to 2000 hours"
            );
            skillRepository.saveAll(List.of(skill1, skill2, skill3));
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
                    null
            );
            staff1.setSkills(Set.of(
                    skill1,skill2
            ));
            Staff staff2 = new Staff(
                    "Phuo Pham",
                    LocalDate.of(1994, Month.JULY, 2),
                    "285 Doi Can, Hai Ba Trung, Ha Noi",
                    "0988999666",
                    "phuopham@github.com",
                    13432543832L,
                    LocalDate.of(2022, Month.OCTOBER, 30),
                    null
            );
            staff2.setSkills(Set.of(
                    skill2,skill3
            ));
            staffRepository.saveAll(List.of(staff1, staff2));
            //----------------------------------------------

            //Add Service Data Demo

            ServiceBundle service1 = new ServiceBundle(
                    "service1",
                    "Description for service1"
            );
            service1.setSkills(Set.of(skill1,skill2));
            ServiceBundle service2 = new ServiceBundle(
                    "service2",
                    "Description for service2"
            );
            service2.setSkills(Set.of(skill2, skill3));
            ServiceBundle service3 = new ServiceBundle(
                    "service3",
                    "Description for service3"
            );
            service3.setSkills(Set.of(skill3));
            
            serviceBundleRepository.saveAll(List.of(service1,service2,service3));

        };
    }
}
