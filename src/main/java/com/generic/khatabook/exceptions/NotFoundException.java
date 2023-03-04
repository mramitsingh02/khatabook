package com.generic.khatabook.exceptions;

import com.generic.khatabook.entity.AppEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(final String msg) {
        super(msg);
    }

    public NotFoundException(final AppEntity appEntity, final String id) {
        super(appEntity.getName() + " " + id + " not fount!.");
    }
}
