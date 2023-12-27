package org.github.ehayik.kata.persons.infrastructure.adapter.persistence;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.SEQUENCE;
import static org.hibernate.annotations.OnDeleteAction.RESTRICT;
import static org.hibernate.annotations.SourceType.DB;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Include;
import lombok.experimental.Accessors;
import org.github.ehayik.kata.persons.application.domain.Gender;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.RowId;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

@Getter
@Setter
@Entity
@Audited
@RowId("ROWID")
@Table(name = "PERSONS")
@Accessors(chain = true)
@ToString(onlyExplicitlyIncluded = true)
class PersonEntity {

    @Id
    @Include
    @GeneratedValue(strategy = SEQUENCE, generator = "PERSONS_id_gen")
    @SequenceGenerator(name = "PERSONS_id_gen", sequenceName = "PERSON_SEQ", allocationSize = 1)
    @Column(name = "PERSON_ID", nullable = false)
    private Long id;

    @Include
    @NotBlank
    @Size(max = 100)
    @Column(name = "FIRST_NAME", nullable = false, length = 100)
    private String firstName;

    @Include
    @NotBlank
    @Size(max = 100)
    @Column(name = "LAST_NAME", nullable = false, length = 100)
    private String lastName;

    @Include
    @NotNull
    @Column(name = "BIRTH_DATE", nullable = false)
    private LocalDate birthDate;

    @Include
    @NotNull
    @Enumerated
    @Column(name = "GENDER", nullable = false)
    private Gender gender;

    @Include
    @Size(max = 20)
    @Column(name = "PHONE", nullable = false, length = 20)
    private String phone;

    @Include
    @Size(max = 50)
    @Column(name = "EMAIL", nullable = false, length = 50)
    private String email;

    @Include
    @CreationTimestamp(source = DB)
    @Column(name = "CREATED_ON", nullable = false, updatable = false)
    private LocalDateTime createdOn;

    @Include
    @UpdateTimestamp(source = DB)
    @Column(name = "LAST_UPDATED_ON", nullable = false)
    private LocalDateTime lastUpdatedOn;

    /**
     * It's a good practice to set all <code>@ManyToOne</code> associations to use <code>FetchType.LAZY</code> strategy
     * @see <a href="https://vladmihalcea.com/jpa-entity-graph">Jpa Entity Graph</a>
     * */
    @NotNull
    @ManyToOne(fetch = LAZY, cascade = PERSIST, optional = false)
    @OnDelete(action = RESTRICT)
    @JoinColumn(name = "ADDRESS_ID", nullable = false)
    private AddressEntity address;

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof PersonEntity other) && Objects.equals(getId(), other.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
