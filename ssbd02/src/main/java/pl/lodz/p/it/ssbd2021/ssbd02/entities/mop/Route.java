package pl.lodz.p.it.ssbd2021.ssbd02.entities.mop;

import lombok.AccessLevel;
import lombok.*;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.AbstractEntity;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mok.Account;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "Route", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"start", "destination"})
})
@NamedQueries({
        @NamedQuery(name = "Route.findAll", query = "SELECT r FROM Route r"),
        @NamedQuery(name = "Route.findById", query = "SELECT r FROM Route r WHERE r.id = :id"),
        @NamedQuery(name = "Route.findByStart", query = "SELECT r FROM Route r WHERE r.start = :start"),
        @NamedQuery(name = "Route.findByDestination", query = "SELECT r FROM Route r WHERE r.destination = :destination"),
        @NamedQuery(name = "Route.findByCode", query = "SELECT r FROM Route r WHERE r.code = :code")
})
@Data
@NoArgsConstructor
public class Route extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "start", nullable = false, updatable = false, referencedColumnName = "id")
    private Seaport start;

    @NotNull
    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "destination", nullable = false, updatable = false, referencedColumnName = "id")
    private Seaport destination;

    @NotBlank
    @Pattern(regexp = "[A-Z]{6}", message = "Route code must have 6 capital letters")
    @Column(name = "code", nullable = false, unique = true, updatable = false, length = 6)
    private String code;

    @PastOrPresent
    @Column(name = "creation_date", nullable = false, updatable = false)
    private Timestamp creationDate = Timestamp.from(Instant.now());

    @ManyToOne(optional = true, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "created_by", updatable = false, referencedColumnName = "id")
    private Account createdBy;

    @Override
    public String getSummary() {
        return super.getSummary() + " code: " + getCode() + " ";
    }
}
