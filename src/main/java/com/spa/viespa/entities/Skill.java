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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @GeneratedValue (
            strategy = GenerationType.AUTO
    )
    private Long id;
    private String name;
    private String desciption;

    @Column(columnDefinition="tinyint(1) default 0")
    private Integer status;

    @CreationTimestamp
    private ZonedDateTime createAt;

    @UpdateTimestamp
    private ZonedDateTime updateAt;

/*    @ManyToOne
    @JoinColumn(name="id", nullable=false)
    private Staff staff;*/

    public Skill(String name,
                 String desciption,
                 Integer status) {
        this.name = name;
        this.desciption = desciption;
        this.status = status;
    }
}
