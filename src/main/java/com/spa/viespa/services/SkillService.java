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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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

    public void deleteSkill(Long id) {
        boolean exists = skillReponsitory.existsById(id);

        if(!exists) {
            throw new IllegalStateException(
                    "Skill with ID: ["+ id +"] does not exist");
        }
        skillReponsitory.deleteById(id);
    }

    @Transactional
    public void updateSkill(Long id,
                            String name,
                            String description,
                            Integer status) {
        Skill skill = skillReponsitory
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("Skill with ID: ["+ id +"] does not exist"));

        if(name != null && name.length() > 0 && !Objects.equals(skill.getName(), name)) {
            skill.setName(name);
        }

        if(description != null && description.length() > 0 && !Objects.equals(skill.getDesciption(), name)) {
            skill.setDesciption(description);
        }

        if(status != null && status >= 0 && !Objects.equals(skill.getStatus(), status)) {
            skill.setStatus(status);
        }
    }
}
