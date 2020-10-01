package com.shree.batch.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum ErrorMessages {
    MISSING_REQUIRED_FIELD("Missing required field"),
    COULD_NOT_UPDATE("Could not update"),
    COULD_NOT_DELETE("Could not delete"),
    COULD_NOT_FIND("Could not find requested detail's");
    private String message;
    }
