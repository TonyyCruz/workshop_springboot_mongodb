package com.workshopmongo.services.exception;

public class ObjectNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ObjectNotFoundException(String id) {
    super("Object not found. ID: " + id);
  }
}
