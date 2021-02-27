package com.derevetskyi.markiyan.lw2.app.dto;

import java.time.LocalDate;

public class GroupUpdateDto {
    private String name;

    private LocalDate dateOfCreation;

    public GroupUpdateDto() {

    }

    public GroupUpdateDto(String name, LocalDate dateOfCreation) {
        this.name = name;
        this.dateOfCreation = dateOfCreation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
