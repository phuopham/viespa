/**
 * Author: Aptech - Team 1 - Project Semester 2
 * Date: 18/12/2022
 * Description: VieSpa Management Project
 */

package com.spa.viespa.configs;

import com.spa.viespa.entities.*;
import com.spa.viespa.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;

@Configuration
public class ConfigDataTest {
    private final RoleRepository roleRepository;

    public ConfigDataTest(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Bean
    CommandLineRunner mappingDemo(
            SkillRepository skillRepository,
            StaffRepository staffRepository,
            ServiceBundleRepository serviceBundleRepository,
            CourseRepository courseRepository){
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
            staff1.setJoinSkills(Set.of(
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
            staff2.setJoinSkills(Set.of(
                    skill2,skill3
            ));
            staffRepository.saveAll(List.of(staff1, staff2));
            //----------------------------------------------

            //Add Service Data Demo
            ServiceBundle service1 = new ServiceBundle(
                    "Service 1",
                    "Description for Service 1"
            );
            service1.setJoinSkills(Set.of(skill1,skill2));
            ServiceBundle service2 = new ServiceBundle(
                    "Service 2",
                    "Description for Service 2"
            );
            service2.setJoinSkills(Set.of(skill2, skill3));
            ServiceBundle service3 = new ServiceBundle(
                    "Service 3",
                    "Description for Service 3"
            );
            service3.setJoinSkills(Set.of(skill3));
            
            serviceBundleRepository.saveAll(List.of(service1,service2,service3));
            //----------------------------------------------

            //Add Course Data Demo
            Course course1 = new Course(
                    "Course 1",
                    "Description for Course 1",
                    499.00
            );
            Course course2 = new Course(
                    "Course 2",
                    "Description for Course 2",
                    699.00
            );
            Course course3 = new Course(
                    "Course 3",
                    "Description for Course 3",
                    899.00
            );

            courseRepository.saveAll((List.of(course1, course2, course3)));
            //----------------------------------------------

            //Add Role Data Demo
            Role role1 = new Role(ERole.ROLE_USER);
            Role role2 = new Role(ERole.ROLE_MODERATOR);
            Role role3 = new Role(ERole.ROLE_ADMIN);
            roleRepository.saveAll((List.of(role1,role2,role3)));
            //----------------------------------------------
            
        };

    }
}
