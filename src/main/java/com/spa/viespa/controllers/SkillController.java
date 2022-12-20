/**
 * Author: Aptech - Team 1 - Project Semester 2
 * Date: 18/12/2022
 * Description: VieSpa Management Project
 */

package com.spa.viespa.controllers;

import com.spa.viespa.entities.Skill;
import com.spa.viespa.entities.ResponseObject;
import com.spa.viespa.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/skills")
public class SkillController {
    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public ResponseEntity<ResponseObject> getSkills(){
        return skillService.getSkills();
    }

    @GetMapping(path = "{{skillId}}")
    public ResponseEntity<ResponseObject> getSkillDetail(@PathVariable("skillId") Long id) {
        return skillService.getSkillDetail(id);
    }

    @PostMapping
    public ResponseEntity<ResponseObject> addNewSkill(@RequestBody Skill skill) {
        return skillService.addNewSkill(skill);
    }

    @DeleteMapping(path = "{skillId}")
    public ResponseEntity<ResponseObject> deleteSkill(@PathVariable("skillId") Long id) {
        return skillService.deleteSkill(id);
    }

    @PutMapping(path = "{skillId}")
    public ResponseEntity<ResponseObject> updateSkill(
            @PathVariable("skillId") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description) {
        return skillService.updateSkill(id, name, description);
    }

}
