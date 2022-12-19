/**
 * Author: Aptech - Team 1 - Project Semester 2
 * Date: 18/12/2022
 * Description: VieSpa Management Project
 */

package com.spa.viespa.repositories;

import com.spa.viespa.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    @Query("SELECT it FROM Skill it WHERE it.name = ?1 AND it.active = true")
    Optional<Skill> findSkillByName(String name);

    @Query("SELECT it FROM Skill it WHERE it.id = ?1")
    Optional<Skill> findSkillById(Long id);
}
