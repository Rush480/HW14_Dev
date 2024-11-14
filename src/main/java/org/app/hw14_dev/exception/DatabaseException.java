package org.app.hw14_dev.exception;

public class DatabaseException extends RuntimeException{
    public static final String NOTE_NOT_FOUND = "Note not found with id: ";
    public DatabaseException(String message) {
        super(message);
    }
}
