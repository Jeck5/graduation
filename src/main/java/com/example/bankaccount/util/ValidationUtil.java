package com.example.bankaccount.util;

import com.example.bankaccount.model.AbstractBaseEntity;
import com.example.bankaccount.util.exception.NotFoundException;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static void checkNotFoundWithKey(boolean found, int key) {
        checkNotFound(found, "key=" + key);
    }

    public static <T> T checkNotFoundWithKey(T object, int key) {
        return checkNotFound(object, "key=" + key);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void checkNew(AbstractBaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (key=null)");
        }
    }

    public static void assureKeyConsistent(AbstractBaseEntity entity, int key) {
//      http://stackoverflow.com/a/32728226/548473
        if (entity.isNew()) {
            entity.setKey(key);
        } else if (entity.getKey() != key) {
            throw new IllegalArgumentException(entity + " must be with key=" + key);
        }
    }

    //  http://stackoverflow.com/a/28565320/548473
    public static Throwable getRootCause(Throwable t) {
        Throwable result = t;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }

}