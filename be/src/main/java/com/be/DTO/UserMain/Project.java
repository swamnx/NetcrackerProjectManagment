package com.be.DTO.UserMain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idProject",scope = Project.class)
public class Project implements Comparable<Project>{

    private int idProject;
    private String code;
    private String description;
    @Override
    public int compareTo(Project o) {
        return this.code.compareTo(o.code);
    }

}
