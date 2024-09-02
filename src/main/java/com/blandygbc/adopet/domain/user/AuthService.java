package com.blandygbc.adopet.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blandygbc.adopet.domain.exception.ApiException;
import com.blandygbc.adopet.domain.exception.EmailAlreadyInUseException;
import com.blandygbc.adopet.domain.role.Role;
import com.blandygbc.adopet.domain.role.RoleRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
        var newUser = User.builder()
                .email(email)
                .password(encodedPass)
                .role(role)
                .build();
        try {
            return repository.save(newUser);
        } catch (DataIntegrityViolationException e) {
            log.atError().log("Violação de dados com a causa: {}", e.getCause().toString());
            if (e.getRootCause() != null) {
                log.atError().log("Violação de dados com a causa raíz: {}", e.getRootCause().toString());
                if (e.getRootCause().toString().endsWith("'users.email'")) {
                    throw new EmailAlreadyInUseException();
                }
            }
            log.atError().log("Violação de dados com a mensagem: {}", e.getLocalizedMessage());
            throw new ApiException("Violação de dados: " + e.getLocalizedMessage());
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
    }

    public UserDetails getUserDetails(String email) {
        return repository.findByEmail(email);
    }

    public void updateUser(User user, String email, String password) {
        user.updateInfo(email, password);
        try {
            repository.save(user);
        } catch (DataIntegrityViolationException e) {
            log.atError().log("Violação de dados com a causa: {}", e.getCause().toString());
            if (e.getRootCause() != null) {
                log.atError().log("Violação de dados com a causa raíz: {}", e.getRootCause().toString());
                if (e.getRootCause().toString().endsWith("'users.email'")) {
                    throw new EmailAlreadyInUseException();
                }
            }
            log.atError().log("Violação de dados com a mensagem: {}", e.getLocalizedMessage());
            throw new ApiException("Violação de dados: " + e.getLocalizedMessage());
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
    }

}
