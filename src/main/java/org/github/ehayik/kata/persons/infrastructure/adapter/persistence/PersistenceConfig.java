package org.github.ehayik.kata.persons.infrastructure.adapter.persistence;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.envers.repository.config.EnableEnversRepositories;

@Configuration
@EnableEnversRepositories
class PersistenceConfig {}
