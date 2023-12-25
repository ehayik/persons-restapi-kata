package org.github.ehayik.kata.persons.application.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
    M("male"),
    F("female");

    private final String label;
}
