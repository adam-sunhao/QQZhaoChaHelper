package com.adam.exception;

/**
 * Created by Adam on 2019/8/3 18:57.
 */
public class ImageTypeWrongException extends RuntimeException{

    public ImageTypeWrongException() {
        super();
    }

    public ImageTypeWrongException(String msg) {
        super(msg);
    }

}
