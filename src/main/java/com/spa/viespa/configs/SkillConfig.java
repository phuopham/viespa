/**
 * Author: Aptech - Team 1 - Project Semester 2
 * Date: 18/12/2022
 * Description: VieSpa Management Project
 */

package com.spa.viespa.configs;

import com.spa.viespa.entities.Skill;
import com.spa.viespa.repositories.SkillReponsitory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SkillConfig {
    @Bean
    CommandLineRunner preDataSkill(SkillReponsitory repository){
        return args -> {
            Skill test1 = new Skill(
                    "Level 1",
                    "Require for beginner employee with working time period lower than 100 hours",
                    0
            );
            Skill test2 = new Skill(
                    "Level 2",
                    "Require for intermadiate employee with working time priod from 100 to 500 hours",
                    0
            );
            Skill test3 = new Skill(
                    "Level 3",
                    "Require for professional employee with working time priod from 500 to 2000 hours",
                    0
            );
            repository.saveAll(List.of(test1, test2, test3));
        };
    }
}
