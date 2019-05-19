package com.fapi.DTO.UserMain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project implements Comparable<Project> {

    private int idProject;
    private String code;
    private String description;

    @Override
    public int compareTo(Project o) {
        return this.code.compareTo(o.code);
    }
}
