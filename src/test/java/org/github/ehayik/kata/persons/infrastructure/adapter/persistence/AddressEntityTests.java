package org.github.ehayik.kata.persons.infrastructure.adapter.persistence;

import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.STRICT_HASHCODE;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class AddressEntityTests {

    @Test
    void shouldMeetEqualsAndHashCodeContract() {
        EqualsVerifier.forClass(AddressEntity.class)
                .suppress(ALL_FIELDS_SHOULD_BE_USED, STRICT_HASHCODE)
                .verify();
    }
}
