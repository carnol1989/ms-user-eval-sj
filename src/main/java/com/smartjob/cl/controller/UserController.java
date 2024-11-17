package com.smartjob.cl.controller;

import com.smartjob.cl.entity.User;
import com.smartjob.cl.exception.ExceptionResponse;
import com.smartjob.cl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * This class controller exposes endpoint for resource User. <br/>
 * <b>Class</b>: {@link UserController}<br/>
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
 *     <li>Nov 16, 2024 (JAR) Creation class.</li>
 *     </ul>
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllUsersController() {
        return ResponseEntity.ok(service.findAllService());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createdUserController(@Valid  @RequestBody User objReq) {
        try {
            User user = service.saveService(objReq);
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            ExceptionResponse er = new ExceptionResponse(e.getMessage(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
        }

    }

}
