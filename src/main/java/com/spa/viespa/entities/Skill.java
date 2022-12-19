/**
 * Author: Aptech - Team 1 - Project Semester 2
 * Date: 18/12/2022
 * Description: VieSpa Management Project
 */

package com.spa.viespa.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table
public class Skill {
    @Id
    @SequenceGenerator(
            name = "skill_sequence",
            sequenceName = "skill_sequence",
            allocationSize = 1
    )
    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "skill_generator"
    )
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    private boolean active = true;

    @CreationTimestamp
    private ZonedDateTime createAt;

    @UpdateTimestamp
    private ZonedDateTime updateAt;

    public Skill(String name,
                 String description) {
        this.name = name;
        this.description = description;
    }
}
