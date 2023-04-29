package com.blandygbc.adopet.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blandygbc.adopet.domain.role.Role;
import com.blandygbc.adopet.domain.role.RoleRepository;

import jakarta.transaction.Transactional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }

    @Transactional
    public User createUser(String email, String password, Long roleId) {
        Role role = roleRepository.getReferenceById(roleId);
        String encodedPass = passwordEncoder.encode(password);
        var newUser = User.buildNew(email, encodedPass, role);
        return repository.save(newUser);
    }

    public UserDetails getUserDetails(String email) {
        return repository.findByEmail(email);
    }

    public void updateUser(User user, String email, String password) {
        user.updateInfo(email, password);
        repository.save(user);
    }

}
