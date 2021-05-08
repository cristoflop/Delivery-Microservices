package com.urjc.code.products.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Product already exists")
public class ResourceAlreadyExistException extends RuntimeException {
}
