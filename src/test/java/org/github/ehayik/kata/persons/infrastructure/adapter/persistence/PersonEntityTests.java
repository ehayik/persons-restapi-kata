package org.github.ehayik.kata.persons.infrastructure.adapter.persistence;

import static nl.jqno.equalsverifier.Warning.*;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class PersonEntityTests {

    @Test
    void shouldMeetEqualsAndHashCodeContract() {
        EqualsVerifier.forClass(PersonEntity.class)
                .suppress(ALL_FIELDS_SHOULD_BE_USED)
                .verify();
    }
}
