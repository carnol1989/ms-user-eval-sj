package com.smartjob.cl.exception;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
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