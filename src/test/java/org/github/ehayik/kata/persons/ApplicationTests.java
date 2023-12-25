package org.github.ehayik.kata.persons;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.oracle.OracleContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
class ApplicationTests {

    @Container
    @ServiceConnection
    static OracleContainer oracle = new OracleContainer(DockerImageName.parse("gvenzl/oracle-free:slim-faststart")
            .asCompatibleSubstituteFor("gvenzl/oracle-free"))
            // increase startup timeout when using docker & colima on Apple M1
            .withStartupTimeout(Duration.ofMinutes(5))
            .withUsername("PERSONS_DB")
            .withPassword("Lider0ne");

    @Test
    void connectionEstablished() {
        assertThat(oracle.isCreated()).isTrue();
        assertThat(oracle.isRunning()).isTrue();
    }
}
