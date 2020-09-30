package com.shree.batch.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
public class Person extends RepresentationModel<Person> implements Serializable {


    private static final long serialVersionUID = -231564723891446585L;
    private String lastName;
    private String firstName;

    @JsonCreator
    public Person(@JsonProperty("lastName") String lastName, @JsonProperty("firstName") String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

}
