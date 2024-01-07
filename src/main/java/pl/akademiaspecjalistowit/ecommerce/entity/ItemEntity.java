package pl.akademiaspecjalistowit.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "item")
public class ItemEntity {

    public ItemEntity(UUID technicalId, String description, Set<CategoryEntity> categoryId, String name, String availability) {
        this.technicalId = technicalId;
        this.description = description;
        this.categoryId = categoryId;
        this.name = name;
        this.availability = availability;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "technical_id")
    private UUID technicalId;

    private String description;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id")
    private Set<CategoryEntity> categoryId;

    private String name;

    private String availability;
}
