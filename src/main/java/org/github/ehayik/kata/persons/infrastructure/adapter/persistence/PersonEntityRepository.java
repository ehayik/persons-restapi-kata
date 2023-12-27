package org.github.ehayik.kata.persons.infrastructure.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

interface PersonEntityRepository
        extends JpaRepository<PersonEntity, Long>, RevisionRepository<PersonEntity, Long, Long> {}
