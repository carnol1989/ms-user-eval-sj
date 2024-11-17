package com.smartjob.cl.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * This class entity for store the users. <br/>
 * <b>Class</b>: {@link User}<br/>
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
@Schema(description = "User information.")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {

    @Schema(description = "Entity identifier.", example = "dc9b4f4f-ea7b-4ee6-9b1c-973233b56c3b")
    @Id
    @GeneratedValue
    private UUID id;

    @Schema(description = "Username.", example = "Juan Rodriguez")
    @Column
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Schema(description = "User email.", example = "juan@rodriguez.org")
    @Email(message = "The email must be in a valid format")
    @Column(unique = true)
    private String email;

    @Schema(description = "User password.", example = "Valid1Password")
    @Column
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @Schema(description = "User phone list.", example = "{\n" +
            "\"number\": \"1234567\",\n" +
            "\"citycode\": \"1\",\n" +
            "\"contrycode\": \"57\"\n" +
            "}")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("phones")
    @JsonManagedReference
    private List<Phone> phoneList;

    @Schema(description = "User creation date.", example = "2024-11-17T08:14:30.29569")
    @CreationTimestamp
    private LocalDateTime created;

    @Schema(description = "User modification date.", example = "2024-11-17T08:14:30.29569")
    @UpdateTimestamp
    private LocalDateTime modified;

    @Schema(description = "Last user login.", example = "2024-11-17T08:14:30.29569")
    @Column
    private LocalDateTime lastLogin;

    @Schema(description = "API access token (can be UUID or JWT).", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWFuLnJvZHJpQGRvbWluaW8uY2wiLCJpYXQiOjE3MzE4NDkyNzAsImV4cCI6MTczMTg1Mjg3MH0.FVr-phozbvcNEcSY_0hdCpDVhKjNbAiu9Cprcc78xFI")
    @Column
    private String token;

    @Schema(description = "Indicates whether the user is still enabled within the system.", example = "true")
    @Column
    private boolean isActive = true;

}
