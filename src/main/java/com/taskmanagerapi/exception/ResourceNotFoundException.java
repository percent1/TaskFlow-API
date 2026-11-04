
package com.taskmanagerapi.exception;

// Custom exception for resources that cannot be found.
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
