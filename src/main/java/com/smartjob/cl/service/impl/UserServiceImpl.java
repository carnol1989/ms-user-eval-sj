package com.smartjob.cl.service.impl;

import com.smartjob.cl.config.PasswordConfig;
import com.smartjob.cl.entity.User;
import com.smartjob.cl.repository.UserRepository;
import com.smartjob.cl.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordConfig passwordConfig;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    public User saveService(User obj) {
        repository.findByEmail(obj.getEmail()).ifPresent(existingUser -> {
            throw new IllegalArgumentException("The mail already registered");
        });

        if (obj.getPhoneList() != null) {
            obj.getPhoneList().forEach(phone -> phone.setUser(obj));
        }

        String passwordRegex = passwordConfig.getPasswordRegex();
        if (obj.getPassword() != null && !Pattern.matches(passwordRegex, obj.getPassword())) {
            throw new IllegalArgumentException("The password must have at least one uppercase letter, one number and 8 characters.");
        }

        if (obj.getId() == null) {
            obj.setLastLogin(obj.getCreated());
        } else {
            obj.setLastLogin(LocalDateTime.now());
        }

        obj.setToken(generateToken(obj.getEmail()));

        User savedUser = repository.save(obj);

        if (savedUser.getLastLogin() == null) {
            savedUser.setLastLogin(savedUser.getCreated());
            repository.save(savedUser);
        }

        return savedUser;
    }

    private String generateToken(String email) {
        byte[] secretKeyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .signWith(SignatureAlgorithm.HS256, secretKeyBytes)
                .compact();
    }

    @Override
    public void saveAllService(List<User> listObj) {
        repository.saveAll(listObj);
    }

    @Override
    public void updateService(User obj) {
        repository.save(obj);
    }

    @Override
    public void deleteService(User obj) {
        repository.delete(obj);
    }

    @Override
    public User findByIdService(Long id) {
        return null;
    }

    @Override
    public List<User> findAllService() {
        return repository.findAll();
    }

    @Override
    public User findByEmailService(String email) {
        Optional<User> userOptional = repository.findByEmail(email);
        return userOptional.orElseGet(User::new);
    }
}
