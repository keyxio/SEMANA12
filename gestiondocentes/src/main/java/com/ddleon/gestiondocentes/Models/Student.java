package com.ddleon.gestiondocentes.Models;

import jakarta.persistence.*;

@Entity
@Table(name="student")
@SuppressWarnings("unused")

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idStudent;
    private String nameStudent;
    private String addressStudent;
    private String cityStudent;

    // Getters y setters

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getAddressStudent() {
        return addressStudent;
    }

    public void setAddressStudent(String addressStudent) {
        this.addressStudent = addressStudent;
    }

    public String getcityStudent() {
        return cityStudent;
    }

    public void setcityStudent(String cityStudent) {
        this.cityStudent = cityStudent;
    }
}
