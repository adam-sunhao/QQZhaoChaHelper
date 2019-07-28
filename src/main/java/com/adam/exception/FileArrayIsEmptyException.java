package com.adam.exception;

/**
 * Created by Adam on 2019/7/28 17:25.
 */
public class FileArrayIsEmptyException extends RuntimeException {

    public FileArrayIsEmptyException() {
        super();
    }

    public FileArrayIsEmptyException(String msg) {
        super(msg);
    }

}
