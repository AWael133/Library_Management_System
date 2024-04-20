package com.example.Library_Management_System.Exceptions;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String message){
        super(message + " not found");
    }
}
