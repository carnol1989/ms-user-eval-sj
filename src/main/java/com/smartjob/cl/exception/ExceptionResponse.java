package com.smartjob.cl.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * This class is custom exception response. <br/>
 * <b>Class</b>: {@link ExceptionResponse}<br/>
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
@Schema(description = "Error response information.")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionResponse {

    @Schema(description = "Error message.", example = "The mail already registered")
    private String message;

    @Schema(description = "Date and time of error.", example = "2024-11-17T08:39:23.5516508")
    private LocalDateTime timestamp;

}
