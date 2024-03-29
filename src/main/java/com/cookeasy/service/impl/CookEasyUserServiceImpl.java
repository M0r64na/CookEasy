package com.cookeasy.service.impl;

import com.cookeasy.model.entity.UserEntity;
import com.cookeasy.model.CookEasyUser;
import com.cookeasy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class CookEasyUserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CookEasyUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username %s not found!", username)));

        return this.mapToUserDetails(user);
    }

    private UserDetails mapToUserDetails(UserEntity user) {
        List<GrantedAuthority> grantedAuthorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(String.format("ROLE_%s", role.getRoleNameEnum().name())))
                .collect(Collectors.toList());

        return new CookEasyUser(
                user.getUsername(),
                user.getPassword(),
                grantedAuthorities,
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getGenderEntity(),
                user.getLevelEntity()
        );
    }
}