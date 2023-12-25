package org.github.ehayik.kata.persons.infrastructure.adapter.persistence;

import static jakarta.persistence.GenerationType.SEQUENCE;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.RowId;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Setter
@ToString
@RowId("ROWID")
@Accessors(chain = true)
@Table(name = "ADDRESSES")
class AddressEntity {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "ADDRESSES_id_gen")
    @SequenceGenerator(name = "ADDRESSES_id_gen", sequenceName = "ADDRESS_SEQ", allocationSize = 1)
    @Column(name = "ADDRESS_ID", nullable = false)
    private Integer id;

    @NotBlank
    @Size(max = 250)
    @Column(name = "STREET", nullable = false, length = 250)
    private String street;

    @NotBlank
    @Size(max = 100)
    @Column(name = "BUILDING_NUMBER", nullable = false, length = 100)
    private String buildingNumber;

    @NotBlank
    @Size(max = 100)
    @Column(name = "CITY", nullable = false, length = 100)
    private String city;

    @NotBlank
    @Size(max = 100)
    @Column(name = "COUNTRY", nullable = false, length = 100)
    private String country;

    @NotBlank
    @Size(max = 20)
    @Column(name = "ZIP_CODE", nullable = false, length = 20)
    private String zipCode;

    @NotNull
    @CreationTimestamp
    @Column(name = "CREATED_ON", nullable = false, updatable = false)
    private LocalDateTime createdOn;

    @NotNull
    @UpdateTimestamp
    @Column(name = "LAST_UPDATED_ON", nullable = false)
    private LocalDateTime lastUpdatedOn;

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof AddressEntity other) && Objects.equals(getId(), other.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
