package pl.lodz.p.it.ssbd2021.ssbd02.entities.mop;

import lombok.*;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.AbstractEntity;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mok.Account;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "Cruise")
@NamedQueries({
        @NamedQuery(name = "Cruise.findAll", query = "SELECT cr FROM Cruise cr"),
        @NamedQuery(name = "Cruise.findById", query = "SELECT cr FROM Cruise cr WHERE cr.id = :id"),
        @NamedQuery(name = "Cruise.findByStartDate", query = "SELECT cr FROM Cruise cr WHERE cr.startDate = :startDate"),
        @NamedQuery(name = "Cruise.findByEndDate", query = "SELECT cr FROM Cruise cr WHERE cr.endDate = :endDate"),
        @NamedQuery(name = "Cruise.findByRoute", query = "SELECT cr FROM Cruise cr WHERE cr.route = :route"),
        @NamedQuery(name = "Cruise.findByFerry", query = "SELECT cr FROM Cruise cr WHERE cr.ferry = :ferry"),
        @NamedQuery(name = "Cruise.findByNumber", query = "SELECT cr FROM Cruise cr WHERE cr.number = :number")
})
@Data
@NoArgsConstructor
public class Cruise extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    @Future
    @Column(name = "start_date", nullable = false, updatable = true)
    private Timestamp startDate;

    @NotNull
    @Future
    @Column(name = "end_date", nullable = false, updatable = true)
    private Timestamp endDate;

    @NotNull
    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "route", nullable = false, updatable = true, referencedColumnName = "id")
    private Route route;

    @NotNull
    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "ferry", nullable = false, updatable = true, referencedColumnName = "id")
    private Ferry ferry;

    @NotBlank
    @Pattern(regexp = "[A-Z]{6}[0-9]{6}", message = "Cruise number must have 6 capital letters and 6 digits")
    @Column(name = "number", nullable = false, unique = true, updatable = false, length = 12)
    private String number;

    @PastOrPresent
    @Column(name = "modification_date", nullable = true, updatable = true)
    private Timestamp modificationDate;

    @ManyToOne(optional = true, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "modified_by", nullable = true, updatable = true, referencedColumnName = "id")
    private Account modifiedBy;

    @PastOrPresent
    @Column(name = "creation_date", nullable = false, updatable = false)
    private Timestamp creationDate = Timestamp.from(Instant.now());

    @ManyToOne(optional = true, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "created_by", nullable = true, updatable = false, referencedColumnName = "id")
    private Account createdBy;


}
