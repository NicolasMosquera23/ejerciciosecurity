package pw.usco.parqueadero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pw.usco.parqueadero.entity.CarEntity;


public interface CarRepository extends JpaRepository<CarEntity, Long> {}