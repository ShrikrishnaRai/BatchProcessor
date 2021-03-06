package com.shree.batch.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "people")
public class PersonEntity {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String lastName;
    private String firstName;
    private String email;


    public PersonEntity(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }
}
