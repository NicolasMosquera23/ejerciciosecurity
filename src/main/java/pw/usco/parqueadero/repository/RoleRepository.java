package pw.usco.parqueadero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pw.usco.parqueadero.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {}
