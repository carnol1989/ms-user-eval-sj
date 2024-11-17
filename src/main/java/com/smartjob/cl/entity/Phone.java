package com.smartjob.cl.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class entity for store the phones of users. <br/>
 * <b>Class</b>: {@link Phone}<br/>
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
@Schema(description = "Phone information.")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "phones")
public class Phone {

    @Schema(description = "Entity identifier.", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "phone number.", example = "1234567")
    private String number;

    @Schema(description = "City code.", example = "1")
    private String cityCode;

    @Schema(description = "Country code.", example = "57")
    private String contryCode;

    @Schema(description = "User information.", example = "Juan Rodriguez")
    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_users_phones"), nullable = false)
    @JsonBackReference
    private User user;

}
