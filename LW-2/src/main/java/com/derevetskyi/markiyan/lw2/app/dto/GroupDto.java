package com.derevetskyi.markiyan.lw2.app.dto;
import com.derevetskyi.markiyan.lw2.app.model.Group;

import java.time.LocalDate;

public class GroupDto {

    private Long id;

    private String name;

    private LocalDate dateOfCreation;

    public GroupDto(Group group) {
        this.id = group.getId();
        this.name = group.getName();
        this.dateOfCreation = group.getDateOfCreation();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
