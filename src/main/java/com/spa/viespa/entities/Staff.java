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
    @GeneratedValue (
            strategy = GenerationType.AUTO
    )
    private Long id;
    private String name;
    private LocalDate dob;
    private String address;
    private String phone;
    private String email;
    private Long idNo;
    private LocalDate joinDate;
    private LocalDate endDate;

    @CreationTimestamp
    private ZonedDateTime createAt;

    @UpdateTimestamp
    private ZonedDateTime updateAt;

    //@OneToMany(mappedBy="staff")
    //private Set<Skill> skills;

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
