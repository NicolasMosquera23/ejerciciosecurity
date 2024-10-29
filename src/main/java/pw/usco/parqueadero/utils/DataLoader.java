package pw.usco.parqueadero.utils;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pw.usco.parqueadero.entity.RoleEntity;
import pw.usco.parqueadero.entity.UserEntity;
import pw.usco.parqueadero.repository.RoleRepository;
import pw.usco.parqueadero.repository.UserRepository;

import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        /* Roles */
        RoleEntity user = roleRepository.findById(1L)
                .orElseThrow(() -> new EntityNotFoundException("role not found!"));


        RoleEntity admin = roleRepository.findById(2L)
                .orElseThrow(() -> new EntityNotFoundException("role not found!"));

        /* Users */
        UserEntity julianUser = UserEntity.builder()
                .username("julian.enrique")
                .password(passwordEncoder.encode("julian"))
                .isEnabled(true)
                .roles(Set.of(admin))
                .build();

        UserEntity carlosUser = UserEntity.builder()
                .username("carlos.esteban")
                .password(passwordEncoder.encode("carlos"))
                .isEnabled(true)
                .roles(Set.of(user))
                .build();

        UserEntity nathaliaUser = UserEntity.builder()
                .username("nathalia.avila")
                .password(passwordEncoder.encode("nathalia"))
                .isEnabled(true)
                .roles(Set.of(admin))
                .build();

        UserEntity yohanUser = UserEntity.builder()
                .username("yohan.bustos")
                .password(passwordEncoder.encode("yohan"))
                .isEnabled(true)
                .roles(Set.of(admin))
                .build();

        UserEntity liangUser = UserEntity.builder()
                .username("liang.camilo")
                .password(passwordEncoder.encode("liang"))
                .isEnabled(true)
                .roles(Set.of(user))
                .build();

        UserEntity felipeUser = UserEntity.builder()
                .username("felipe.artunduaga")
                .password(passwordEncoder.encode("felipe"))
                .isEnabled(true)
                .roles(Set.of(user))
                .build();

        userRepository.saveAll(List.of(julianUser,
                carlosUser,
                nathaliaUser,
                yohanUser,
                liangUser,
                felipeUser));
    }
}
