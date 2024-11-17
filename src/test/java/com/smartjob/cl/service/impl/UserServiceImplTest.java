package com.smartjob.cl.service.impl;

import com.smartjob.cl.entity.User;
import com.smartjob.cl.repository.UserRepository;
import com.smartjob.cl.config.PasswordConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class test for UserServiceImpl. <br/>
 * <b>Class</b>: {@link UserServiceImplTest}<br/>
 * <b>Copyright</b>: &Copy; 2024 SmartJob. <br/>
 * <b>Company</b>: SmartJob. <br/>
 *
 * @author SmartJob <br/>
 *     <u>Service Provider</u>: Consultor TI <br/>
 *     <u>Developed by</u>: <br/>
 *     <ul>
 *     <li>Carlos Augusto Nole Machaca</li>
 *     </ul>
 *     <u>Changes</u>:<br/>
 *     <ul>
 *     <li>Nov 17, 2024 (JAR) Creation class.</li>
 *     </ul>
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordConfig passwordConfig;

    @InjectMocks
    private UserServiceImpl userService;

    private User validUser;
    private String token;

    @BeforeEach
    void setUp() {
        String uuidString = "dc9b4f4f-ea7b-4ee6-9b1c-973233b56c3b";
        UUID specificUUID = UUID.fromString(uuidString);

        validUser = new User();
        validUser.setEmail("test@example.com");
        validUser.setPassword("Valid1Password");
        validUser.setId(specificUUID);

        token = Jwts.builder()
                .setSubject(validUser.getEmail())
                .signWith(SignatureAlgorithm.HS256, Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded())
                .compact();
    }

    @Test
    void testSaveService_UserSavedSuccessfully() {
        when(repository.findByEmail(validUser.getEmail())).thenReturn(Optional.empty());
        when(repository.save(validUser)).thenReturn(validUser);
        when(passwordConfig.getPasswordRegex()).thenReturn("^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$");

        User savedUser = userService.saveService(validUser);

        assertNotNull(savedUser);
        assertEquals(validUser.getEmail(), savedUser.getEmail());
        assertNotNull(savedUser.getToken());
        verify(repository, times(1)).findByEmail(validUser.getEmail());
        verify(repository, times(1)).save(validUser);
    }

    @Test
    void testSaveService_UserWithDuplicateEmail_ThrowsException() {
        when(repository.findByEmail(validUser.getEmail())).thenReturn(Optional.of(validUser));

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> userService.saveService(validUser));
        assertEquals("The mail already registered", thrown.getMessage());
        verify(repository, times(1)).findByEmail(validUser.getEmail());
        verify(repository, never()).save(any());
    }

    @Test
    void testSaveService_InvalidPassword_ThrowsException() {
        User userWithInvalidPassword = new User();
        userWithInvalidPassword.setEmail("invalid@example.com");
        userWithInvalidPassword.setPassword("12345");

        when(passwordConfig.getPasswordRegex()).thenReturn("^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$");

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> userService.saveService(userWithInvalidPassword));
        assertEquals("The password must have at least one uppercase letter, one number and 8 characters.", thrown.getMessage());
    }

    @Test
    void testGenerateToken_ReturnsValidToken() {
        byte[] secretKeyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();
        String generatedToken = Jwts.builder()
                .setSubject(validUser.getEmail())
                .signWith(SignatureAlgorithm.HS256, secretKeyBytes)
                .compact();

        assertNotNull(generatedToken);
        assertTrue(generatedToken.startsWith("ey"));
    }

    @Test
    void testFindByEmailService_UserExists() {
        when(repository.findByEmail(validUser.getEmail())).thenReturn(Optional.of(validUser));

        User foundUser = userService.findByEmailService(validUser.getEmail());

        assertNotNull(foundUser);
        assertEquals(validUser.getEmail(), foundUser.getEmail());
        verify(repository, times(1)).findByEmail(validUser.getEmail());
    }

    @Test
    void testFindByEmailService_UserDoesNotExist() {
        when(repository.findByEmail(validUser.getEmail())).thenReturn(Optional.empty());

        User foundUser = userService.findByEmailService(validUser.getEmail());

        assertNotNull(foundUser);
        assertEquals(new User().getEmail(), foundUser.getEmail());
        verify(repository, times(1)).findByEmail(validUser.getEmail());
    }

    @Test
    void testSaveAllService() {
        User anotherUser = new User();
        anotherUser.setEmail("another@example.com");
        anotherUser.setPassword("Valid1Password");

        List<User> userList = List.of(validUser, anotherUser);

        when(repository.saveAll(userList)).thenReturn(userList);

        userService.saveAllService(userList);

        verify(repository, times(1)).saveAll(userList);
    }

    @Test
    void testUpdateService_UserUpdated() {
        validUser.setPassword("UpdatedPassword1");

        when(repository.save(validUser)).thenReturn(validUser);

        User updatedUser = userService.updateService(validUser);

        assertNotNull(updatedUser);
        assertEquals("UpdatedPassword1", updatedUser.getPassword());
        verify(repository, times(1)).save(validUser);
    }

    @Test
    void testDeleteService_UserDeleted() {
        doNothing().when(repository).delete(validUser);

        userService.deleteService(validUser);

        verify(repository, times(1)).delete(validUser);
    }

    @Test
    void testFindAllService_ReturnsUserList() {
        List<User> users = List.of(validUser);
        when(repository.findAll()).thenReturn(users);

        List<User> userList = userService.findAllService();

        assertNotNull(userList);
        assertEquals(1, userList.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindByIdService_ReturnsNull() {
        User user = userService.findByIdService(1L);
        assertNull(user);
    }

}