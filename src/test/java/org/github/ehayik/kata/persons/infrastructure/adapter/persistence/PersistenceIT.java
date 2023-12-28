package org.github.ehayik.kata.persons.infrastructure.adapter.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.github.ehayik.kata.persons.fixtures.verifies.Verifications.verifyThat;
import static org.github.ehayik.kata.persons.infrastructure.adapter.persistence.PersonEntityFactory.createDefaultPerson;

import java.time.Duration;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.oracle.OracleContainer;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@SpringBootTest
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
    private PersonEntityRepository repository;

    @Test
    void shouldPersistPersonEntity() {
        // Given
        var person = createDefaultPerson();

        // When
        person = repository.saveAndFlush(person);

        // Then
        assertThat(person.getId()).isNotZero();
        assertThat(person.getCreatedOn().toLocalDate()).isEqualTo(LocalDate.now());
        assertThat(person.getLastUpdatedOn().toLocalDate()).isEqualTo(LocalDate.now());
    }

    @Test
    void shouldCreatePersonEntityRevision() {
        // Given
        var person = repository.saveAndFlush(createDefaultPerson());

        // When
        var revisions = repository.findRevisions(person.getId());

        // Then
        assertThat(revisions).hasSize(1);
    }

    @Test
    void shouldThrowExceptionWhenEmailIsDuplicated() {
        // Given
        var person = repository.saveAndFlush(createDefaultPerson());

        // Then
        verifyThat(() -> repository.saveAndFlush(person)).shouldThrowRuntimeException("ss");
    }
}
