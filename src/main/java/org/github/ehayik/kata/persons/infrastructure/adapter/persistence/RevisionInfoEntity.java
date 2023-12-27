package org.github.ehayik.kata.persons.infrastructure.adapter.persistence;

import static jakarta.persistence.GenerationType.SEQUENCE;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Getter
@Setter
@Entity
@ToString
@RevisionEntity
@Table(name = "AUDIT_REVISIONS_LOG")
class RevisionInfoEntity {

    @Id
    @RevisionNumber
    @Column(name = "REVISION_NUMBER")
    @GeneratedValue(strategy = SEQUENCE, generator = "AUDIT_REVISIONS_LOG_id_gen")
    @SequenceGenerator(name = "AUDIT_REVISIONS_LOG_id_gen", sequenceName = "AUDIT_REVISION_LOG_SEQ", allocationSize = 1)
    private Long id;

    @RevisionTimestamp
    @Column(name = "REVISION_TIMESTAMP")
    private Long timestamp;
}
