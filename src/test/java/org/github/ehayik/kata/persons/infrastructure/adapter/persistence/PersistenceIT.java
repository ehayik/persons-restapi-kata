package org.github.ehayik.kata.persons.infrastructure.adapter.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.github.ehayik.kata.persons.infrastructure.adapter.persistence.PersonEntityFactory.DEFAULT_PERSON_ID;
import static org.github.ehayik.kata.persons.infrastructure.adapter.persistence.PersonEntityFactory.createDefaultPerson;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import java.time.Duration;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.oracle.OracleContainer;
import org.testcontainers.utility.DockerImageName;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = NONE)
class PersistenceIT {

    @Container
    @ServiceConnection
    static OracleContainer oracle = new OracleContainer(DockerImageName.parse("gvenzl/oracle-free:slim-faststart")
                    .asCompatibleSubstituteFor("gvenzl/oracle-free"))
            // increase startup timeout when using docker & colima on Apple M1
            .withStartupTimeout(Duration.ofMinutes(5))
            .withUsername("PERSONS_DB")
            .withPassword("Lider0ne");

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void shouldPersistPersonEntity() {
        // Given
        var person = createDefaultPerson();

        // When
        person = testEntityManager.persist(person);
        testEntityManager.flush();

        // Then
        assertThat(person.getId()).isEqualTo(DEFAULT_PERSON_ID);
        assertThat(person.getCreatedOn().toLocalDate()).isEqualTo(LocalDate.now());
        assertThat(person.getLastUpdatedOn().toLocalDate()).isEqualTo(LocalDate.now());
    }
}
