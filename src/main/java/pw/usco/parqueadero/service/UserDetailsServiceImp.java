package pw.usco.parqueadero.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pw.usco.parqueadero.entity.UserEntity;
import pw.usco.parqueadero.repository.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found!"));

        Set<SimpleGrantedAuthority> authorityList = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return new User(user.getUsername(), user.getPassword(), authorityList);
    }
}
