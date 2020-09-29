package com.shree.batch.model;

import lombok.*;

import java.io.Serializable;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {


    private String lastName;
    private String firstName;

}
