package edu.curtin.mouse.exception;

public class MoveOutsideException extends GridException {
    public MoveOutsideException() {
    }

    public MoveOutsideException(String message) {
        super(message);
    }
}
