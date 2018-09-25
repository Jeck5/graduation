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

}