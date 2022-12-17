package com.spa.viespa.staff;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Staff {
    @Id
//    @SequenceGenerator(
//            name="staff_sequence",
//            sequenceName="staff_sequence",
//            allocationSize = 1
//    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
//            generator = "student_sequence"
    )
    private Long id;
    private String name;
    private LocalDate dob;
    private Long idNo;

    public Staff() {
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", idNo=" + idNo +
                '}';
    }

    public Long getId() {
        return id;
    }

    public Staff( String name, LocalDate dob, Long idNo) {
        this.name = name;
        this.dob = dob;
        this.idNo = idNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Long getIdNo() {
        return idNo;
    }

    public void setIdNo(Long idNo) {
        this.idNo = idNo;
    }
}
