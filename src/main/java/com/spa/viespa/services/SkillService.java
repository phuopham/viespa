/**
 * Author: Aptech - Team 1 - Project Semester 2
 * Date: 18/12/2022
 * Description: VieSpa Management Project
 */

package com.spa.viespa.services;

import com.spa.viespa.entities.Skill;
import com.spa.viespa.repositories.SkillReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //Get All Data in Skill Table
    public ResponseEntity<ResponseObject> getSkills() {
        List<Skill> all = skillReponsitory.findAll();
        return all.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("error", "Data not found", "")
                ) :
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("success", "Data of skill table", all)
                );
    }

    //Add New Skill
    public ResponseEntity<ResponseObject> addNewSkill(Skill skill) {
        Optional<Skill> duplicatedName = skillReponsitory.findSkillByName(skill.getName());
        if(duplicatedName.isPresent()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("error", "This skill name is already existed", "")
            );
        }

        //Save
        skillReponsitory.save(skill);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Insert data successfully", skill)
        );
    }

    //Delete Skill By ID
    public ResponseEntity<ResponseObject> deleteSkill(Long id) {
        boolean exists = skillReponsitory.existsById(id);

        if(exists) {
            skillReponsitory.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Delete data successfully", "")
            );
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("error", "Skill with ID: [" + id + "] does not exist", "")
        );
    }


    //Update Skill By ID
    @Transactional
    public ResponseEntity<ResponseObject> updateSkill(Long id,
                            String name,
                            String description,
                            Integer status) {
        Skill skill = skillReponsitory
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("Skill with ID: ["+ id +"] does not exist"));

        Optional<Skill> duplicatedName = skillReponsitory.findSkillByName(name);

        if(duplicatedName.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("error", "This skill name is already existed", "")
            );
        }

        if(name != null && name.length() > 0 && !Objects.equals(skill.getName(), name)) {
            skill.setName(name);
        }

        if(description != null && description.length() > 0 && !Objects.equals(skill.getDescription(), name)) {
            skill.setDescription(description);
        }

        if(status != null && status >= 0 && !Objects.equals(skill.getStatus(), status)) {
            skill.setStatus(status);
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Update data successfully", "")
        );
    }
}
