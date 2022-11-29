package com.aol.alkuznetsov.panda.server.exception;

public class DataNotFoundException extends RuntimeException {

  private static final long serialVersionUID = -4665737540601751181L;

  public DataNotFoundException() {
    super();
  }

  public DataNotFoundException(String message) {
    super(message);
  }

  public DataNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
