package com.nix.events.exceptions;

public class EventNotFoundException extends RuntimeException{

    public EventNotFoundException(Long id) {
        super("Could not found event with id " + id);
    }
}
