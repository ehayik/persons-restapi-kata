package org.github.ehayik.kata.persons.infrastructure.adapter.persistence;

import static lombok.AccessLevel.PACKAGE;

import java.time.LocalDate;
import lombok.NoArgsConstructor;
import org.github.ehayik.kata.persons.application.domain.Gender;

@NoArgsConstructor(access = PACKAGE)
class PersonEntityFactory {

    static final int DEFAULT_PERSON_ID = 1;

    static PersonEntity createDefaultPerson() {
        var address = new AddressEntity()
                .setStreet("517 Stark Rapid")
                .setBuildingNumber("74653")
                .setCountry("Zimbabwe")
                .setCity("Stephaniaberg")
                .setZipCode("Zimbabwe");
        return new PersonEntity()
                .setFirstName("Lindsay")
                .setLastName("Farrell")
                .setBirthDate(LocalDate.parse("1943-04-09"))
                .setGender(Gender.M)
                .setPhone("+2151259822482")
                .setEmail("beer.eliseo@yahoo.com")
                .setAddress(address);
    }
}
