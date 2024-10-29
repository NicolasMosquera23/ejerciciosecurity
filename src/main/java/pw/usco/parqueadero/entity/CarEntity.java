package pw.usco.parqueadero.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cars")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_carro_id")
    private Long id;

    @Column(unique = true)
    private String car;

    @OneToMany(mappedBy = "car")
    private Set<EventEntity> cars;

}
