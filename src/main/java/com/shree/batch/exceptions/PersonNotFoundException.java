package com.shree.batch.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends EntityNotFoundException {

    public PersonNotFoundException(String message) {
        super(message);
    }
}
