package com.spa.viespa.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table
public class Staff {
    @Id
    @SequenceGenerator(
            name = "staff_sequence",
            sequenceName = "staff_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "staff_generator"
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private Long idNo;

    @Column(nullable = false)
    private LocalDate joinDate;

    private LocalDate endDate;

    @CreationTimestamp
    private ZonedDateTime createAt;

    @UpdateTimestamp
    private ZonedDateTime updateAt;

    @ManyToMany
    @JoinTable(
            name = "staff_skills",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skillSet;

    public Staff(String name,
                 LocalDate dob,
                 String address,
                 String phone,
                 String email,
                 Long idNo,
                 LocalDate joinDate,
                 LocalDate endDate) {
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.idNo = idNo;
        this.joinDate = joinDate;
        this.endDate = endDate;
    }
}
