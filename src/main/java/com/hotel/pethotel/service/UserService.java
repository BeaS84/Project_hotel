package com.hotel.pethotel.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.hotel.pethotel.model.UserModel;
import com.hotel.pethotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    //  @Autowired
    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String userEmail)
            throws UsernameNotFoundException {
        UserModel user = findByEmail(userEmail);

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        return new User(userEmail, user.getPassword(), authorities);
    }

    private UserModel findByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(() ->
                new RuntimeException("Couldn't find user by email"));
    }


/// na razie nie umiem zrobic dla sytuacji, kiedy user jest zarowno adminem jak i userem
//    public String determineUserRole() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"))) {
//            return "admin";
//        } else if (authentication.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_USER"))) {
//            return "user";
//        }
//        // W przypadku braku dopasowania zwracamy wartość domyślną lub null
//        return null;
//    }

    public List<UserModel> getAllClients() {
        return userRepo.findAll();
    }

    public UserModel getUserByEmail(String userEmail) {
        Optional<UserModel> userOptional = userRepo.findByEmail(userEmail);
        return userOptional.orElse(null);
    }
}

