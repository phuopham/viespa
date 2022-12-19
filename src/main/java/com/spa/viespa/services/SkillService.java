/**
 * Author: Aptech - Team 1 - Project Semester 2
 * Date: 18/12/2022
 * Description: VieSpa Management Project
 */

package com.spa.viespa.services;

import com.spa.viespa.entities.ResponseMessage;
import com.spa.viespa.entities.ResponseObject;
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

    //Error Message
    public ResponseEntity<ResponseObject> responseSuccess(String msg, Object data) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(msg, data)
        );
    }

    //Success Message
    public ResponseEntity<ResponseObject> responseError(String msg) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(ResponseMessage.ERROR, msg)
        );
    }

    //Get All Data in Skill Table
    public ResponseEntity<ResponseObject> getSkills() {
        List<Skill> all = skillReponsitory.findAll();
        return all.isEmpty() ?
                responseError("Data not found") :
                responseSuccess("Data of skill table", all);
    }

    //Add New Skill
    public ResponseEntity<ResponseObject> addNewSkill(Skill skill) {
        Optional<Skill> duplicatedName = skillReponsitory.findSkillByName(skill.getName());
        if(duplicatedName.isPresent()) {
            responseError("This skill name is already existed");
        }

        //Save
        skillReponsitory.save(skill);
        return responseSuccess("Insert data successfully", skill);
    }

    //Delete Skill By ID
    public ResponseEntity<ResponseObject> deleteSkill(Long id) {
        boolean exists = skillReponsitory.existsById(id);

        if(exists) {
            Skill skill = skillReponsitory
                    .findSkillById(id)
                    .orElseThrow(() -> new IllegalStateException("Skill with ID: ["+ id +"] does not exist"));
            skill.setActive(!skill.isActive());
            skillReponsitory.save(skill);
            return responseSuccess("Delete data successfully", "");
        }

        return responseError("Skill with ID: [" + id + "] does not exist");
    }


    //Update Skill By ID
    @Transactional
    public ResponseEntity<ResponseObject> updateSkill(Long id,
                            String name,
                            String description) {
        Skill skill = skillReponsitory
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("Skill with ID: ["+ id +"] does not exist"));

        Optional<Skill> duplicatedName = skillReponsitory.findSkillByName(name);

        if(duplicatedName.isPresent()) {
            return responseError("This skill name is already existed");
        }

        if(name != null && name.length() > 0 && !Objects.equals(skill.getName(), name)) {
            skill.setName(name);
        }

        if(description != null && description.length() > 0 && !Objects.equals(skill.getDescription(), name)) {
            skill.setDescription(description);
        }

        return responseSuccess("Update data successfully", "");
    }
}
