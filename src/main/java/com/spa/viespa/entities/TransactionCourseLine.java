/**
 * Author: Aptech - Team 1 - Project Semester 2
 * Date: 18/12/2022
 * Description: VieSpa Management Project
 */

package com.spa.viespa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Setter
@Getter
@NoArgsConstructor

@Entity
@Table
public class TransactionCourseLine {
    @Id
    @SequenceGenerator(
            name = "tcl_sequence",
            sequenceName = "tcl_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tcl_generator"
    )
    private Long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "service_id")
    private ServiceBundle service;


    @OneToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    private EPaymentStatus status;

    private ZonedDateTime scheduleAt;

    private ZonedDateTime doneAt;

    @CreationTimestamp
    private ZonedDateTime createAt;

    @UpdateTimestamp
    private ZonedDateTime updateAt;

    public TransactionCourseLine(Customer customer, Course course, ServiceBundle service, Skill skill) {
        this.customer = customer;
        this.course = course;
        this.service = service;
        this.skill = skill;
    }
}
