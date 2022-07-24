package com.movie.dto;


import com.movie.model.type.PersonType;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class PersonDto implements Serializable {
    private @Id Long id;
    private String firstName;
    private String lastName;
    private List<PersonType> jobs = new ArrayList<>();
}
