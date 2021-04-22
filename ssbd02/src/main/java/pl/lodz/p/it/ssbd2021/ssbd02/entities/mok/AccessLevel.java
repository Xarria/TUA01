package pl.lodz.p.it.ssbd2021.ssbd02.entities.mok;

import lombok.*;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;


@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "level", discriminatorType = DiscriminatorType.STRING)
@Table(name = "Access_level")
@NamedQueries({
        @NamedQuery(name = "AccessLevel.findAll", query = "SELECT l FROM AccessLevel l"),
        @NamedQuery(name = "AccessLevel.findById", query = "SELECT l FROM AccessLevel l WHERE l.id = :id"),
        @NamedQuery(name = "AccessLevel.findByVersion", query = "SELECT l FROM AccessLevel l WHERE l.version = :version"),
        @NamedQuery(name = "AccessLevel.findByLevel", query = "SELECT l FROM AccessLevel l WHERE l.level = :level"),
        @NamedQuery(name = "AccessLevel.findByAccountId", query = "SELECT l FROM AccessLevel l WHERE l.accountId = :accountId"),
        @NamedQuery(name = "AccessLevel.findByActive", query = "SELECT l FROM AccessLevel l WHERE l.active = :active"),
        @NamedQuery(name = "AccessLevel.findByModificationDate", query = "SELECT l FROM AccessLevel l WHERE l.modificationDate = :modificationDate"),
        @NamedQuery(name = "AccessLevel.findByModifiedBy", query = "SELECT l FROM AccessLevel l WHERE l.modifiedBy = :modifiedBy"),
        @NamedQuery(name = "AccessLevel.findByCreationDate", query = "SELECT l FROM AccessLevel l WHERE l.creationDate = :creationDate"),
        @NamedQuery(name = "AccessLevel.findByLogin", query = "SELECT l FROM AccessLevel l WHERE l.accountId.login = :login")
})
@Data
@NoArgsConstructor
public class AccessLevel extends AbstractEntity implements Serializable {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(lombok.AccessLevel.NONE)
    private Long id;

    @NotNull
    @Column(name = "level", nullable = false, updatable = false, length = 16)
    private String level;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "account", nullable = false, updatable = false, referencedColumnName = "id")
    private Account account;

    @Column(name = "active", nullable = false, updatable = true)
    private Boolean active = true;

    @Column(name = "modification_date", nullable = true, updatable = true)
    private Timestamp modificationDate;

    @ManyToOne(optional = true, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "modified_by", nullable = true, updatable = true, referencedColumnName = "id")
    private Account modifiedBy;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Timestamp creationDate = Timestamp.from(Instant.now());

}
