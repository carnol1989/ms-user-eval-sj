package com.smartjob.cl.controller;

import com.smartjob.cl.entity.User;
import com.smartjob.cl.exception.ExceptionResponse;
import com.smartjob.cl.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class test for UserController. <br/>
 * <b>Class</b>: {@link UserControllerTest}<br/>
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
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        String uuidString = "dc9b4f4f-ea7b-4ee6-9b1c-973233b56c3b";
        UUID specificUUID = UUID.fromString(uuidString);

        user1 = new User();
        user1.setId(specificUUID);
        user1.setName("John Doe");
        user1.setEmail("john.doe@example.com");

        String uuidString2 = "dc9b4f4f-ea7b-4ee6-9b1c-973233b56c3c";
        UUID specificUUID2 = UUID.fromString(uuidString);

        user2 = new User();
        user2.setId(specificUUID2);
        user2.setName("Jane Doe");
        user2.setEmail("jane.doe@example.com");
    }

    @Test
    void testGetAllUsersController() {
        List<User> userList = Arrays.asList(user1, user2);
        when(userService.findAllService()).thenReturn(userList);

        ResponseEntity<?> response = userController.getAllUsersController();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
        verify(userService, times(1)).findAllService();
    }

    @Test
    void testCreatedUserController() {
        when(userService.saveService(user1)).thenReturn(user1);

        ResponseEntity<?> response = userController.createdUserController(user1);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user1, response.getBody());
        verify(userService, times(1)).saveService(user1);
    }

    @Test
    void testCreatedUserControllerBadRequest() {
        when(userService.saveService(user1)).thenThrow(new IllegalArgumentException("Invalid user data"));

        ResponseEntity<?> response = userController.createdUserController(user1);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof ExceptionResponse);
        ExceptionResponse errorResponse = (ExceptionResponse) response.getBody();
        assertEquals("Invalid user data", errorResponse.getMessage());
        assertNotNull(errorResponse.getTimestamp());
        verify(userService, times(1)).saveService(user1);
    }

}