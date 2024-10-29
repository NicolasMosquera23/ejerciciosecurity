package pw.usco.parqueadero.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pw.usco.parqueadero.entity.EventEntity;

public interface EventRepository extends JpaRepository<EventEntity, Long> {}
