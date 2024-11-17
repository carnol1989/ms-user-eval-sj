package com.smartjob.cl.exception;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class test for ResourceNotFoundException. <br/>
 * <b>Class</b>: {@link ResourceNotFoundExceptionTest}<br/>
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
class ResourceNotFoundExceptionTest {

    @ParameterizedTest
    @CsvSource({
            "'Resource not found', null",
            "'Resource not found with ID 123', 'java.lang.Throwable: Cause of the exception'"
    })
    void testConstructors(String message, String cause) {
        Throwable throwable = cause != null ? new Throwable(cause) : null;

        ResourceNotFoundException exceptionWithMessage = new ResourceNotFoundException(message);
        assertEquals(message, exceptionWithMessage.getMessage());
        assertNull(exceptionWithMessage.getCause());

        ResourceNotFoundException exceptionWithCause = new ResourceNotFoundException(message, throwable);
        assertEquals(message, exceptionWithCause.getMessage());
        assertEquals(throwable, exceptionWithCause.getCause());
    }

}