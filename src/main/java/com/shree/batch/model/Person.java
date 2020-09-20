package com.shree.batch.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String lastName;
    private String firstName;
}
