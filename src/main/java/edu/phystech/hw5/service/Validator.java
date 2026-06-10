package edu.phystech.hw5.service;

import edu.phystech.hw5.annotation.validation.NotBlank;
import edu.phystech.hw5.annotation.validation.Size;
import edu.phystech.hw5.exception.ValidationException;

import java.lang.reflect.Field;

public interface Validator {
    void validate(Object object);

    static Validator getDefault() {
        return object -> {
            if (object == null) return;

            Class<?> clazz = object.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getType().equals(String.class)) {
                    try {
                        field.setAccessible(true); 
                        String value = (String) field.get(object);

                        if (field.isAnnotationPresent(NotBlank.class)) {
                            if (value == null || value.isEmpty()) {
                                throw new ValidationException(field.getAnnotation(NotBlank.class).message());
                            }
                        }

                        if (field.isAnnotationPresent(Size.class)) {
                            Size size = field.getAnnotation(Size.class);
                            int length = (value == null) ? 0 : value.length();
                            
                            if (length < size.min() || length > size.max()) {
                                throw new ValidationException(size.message());
                            }
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Failed to access field: " + field.getName(), e);
                    }
                }
            }
        };
    }
}
