/**
 * Author: Aptech - Team 1 - Project Semester 2
 * Date: 18/12/2022
 * Description: VieSpa Management Project
 */

package com.spa.viespa.services;

import com.spa.viespa.entities.Skill;
import com.spa.viespa.repositories.SkillReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {

    private final SkillReponsitory skillReponsitory;

    @Autowired
    public SkillService(SkillReponsitory skillReponsitory) {
        this.skillReponsitory = skillReponsitory;
    }

    public List<Skill> getSkills() {
        return skillReponsitory.findAll();
    }

    public void addNewSkill(Skill skill) {
        Optional<Skill> duplicatedName = skillReponsitory.findSkillByName(skill.getName());
        if(duplicatedName.isPresent()) {
            throw new IllegalStateException("This skill name is already existed");
        }

        System.out.println(skill);
        skillReponsitory.save(skill);
    }
}
