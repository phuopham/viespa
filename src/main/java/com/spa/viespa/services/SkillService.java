/**
 * Author: Aptech - Team 1 - Project Semester 2
 * Date: 18/12/2022
 * Description: VieSpa Management Project
 */

package com.spa.viespa.services;

import com.spa.viespa.entities.ResponseObject;
import com.spa.viespa.entities.Skill;
import com.spa.viespa.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    @Autowired
    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    //Get All Data in Skill Table
    public ResponseEntity<ResponseObject> getSkills() {
        List<Skill> all = skillRepository.findAll();
        return all.isEmpty() ?
                ResponseObject.response("Data not found") :
                ResponseObject.response("Data of skill table", all);
    }

    //Get Detail Skill By ID
    public ResponseEntity<ResponseObject> getSkillDetail(Long id) {

        Optional<Skill> skill = skillRepository.findById(id);
        if (skill.isEmpty()) return ResponseObject
                .response("Skill with ID: [" + id + "] does not exist");

        Skill target = skill.get();

        return ResponseObject.response("Get skill [" + id + "] successfully", target);
    }

    //Add New Skill
    public ResponseEntity<ResponseObject> addNewSkill(Skill skill) {
        Optional<Skill> duplicatedName = skillRepository.findSkillByName(skill.getName());
        if (duplicatedName.isPresent()) return ResponseObject
                .response("This skill name is already existed");

        //Save
        skillRepository.save(skill);
        return ResponseObject.response("Insert data successfully", skill);
    }

    //Delete Skill By ID
    public ResponseEntity<ResponseObject> deleteSkill(Long id) {

        Optional<Skill> skill = skillRepository.findById(id);

        if (skill.isEmpty()) return ResponseObject
                .response("Skill with ID: [" + id + "] does not exist");

        //Soft Delete
        Skill target = skill.get();
        target.setActive(!target.isActive());
        skillRepository.save(target);

        return ResponseObject.response("Delete data successfully", target);

    }


    //Update Skill By ID
    @Transactional
    public ResponseEntity<ResponseObject> updateSkill(Long id,
                                                      String name,
                                                      String description) {
        Optional<Skill> skill = skillRepository.findById(id);

        if (skill.isEmpty()) return ResponseObject
                .response("Skill with ID: [\"+ id +\"] does not exist");

        Optional<Skill> duplicatedName = skillRepository.findSkillByName(name);

        if (duplicatedName.isPresent()) return ResponseObject
                .response("This skill name is already existed");

        Skill target = skill.get();

        if (name != null && name.length() > 0 && !Objects.equals(target.getName(), name))
            target.setName(name);

        if (description != null && description.length() > 0 && !Objects.equals(target.getDescription(), description))
            target.setDescription(description);

        return ResponseObject.response("Update data successfully", target);
    }
}
