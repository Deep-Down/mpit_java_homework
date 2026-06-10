package edu.phystech.hw5;

import edu.phystech.hw5.annotation.validation.NotBlank;
import edu.phystech.hw5.annotation.validation.Size;
import edu.phystech.hw5.exception.ValidationException;
import edu.phystech.hw5.service.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

/**
 * @author kzlv4natoly
 */
public class ValidatorTest {
    private final Validator validator = object -> {
        if (object == null) return;

        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            // Валидируем только строковые поля
            if (field.getType().equals(String.class)) {
                try {
                    field.setAccessible(true);
                    String value = (String) field.get(object);
                    if (field.isAnnotationPresent(NotBlank.class)) {
                        if (value == null || value.trim().isEmpty()) {
                            String msg = field.getAnnotation(NotBlank.class).message();
                            throw new ValidationException(msg);
                        }
                    }

                    if (field.isAnnotationPresent(Size.class)) {
                        Size sizeAnn = field.getAnnotation(Size.class);
                        int len = (value == null) ? 0 : value.length();
                        
                        if (len < sizeAnn.min() || len > sizeAnn.max()) {
                            throw new ValidationException(sizeAnn.message());
                        }
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Доступ к полю ограничен: " + field.getName(), e);
                }
            }
        }
    };

    @Test
    void notBlankWorks() {
        class Example {
            @NotBlank
            private final String x;

            @NotBlank(message = "This is a very important field and it can't be empty!")
            private final String y;

            Example(String x, String y) {
                this.x = x;
                this.y = y;
            }
        }

        Assertions.assertDoesNotThrow(() -> validator.validate(new Example("123", "567")));
        ValidationException exception =
                Assertions.assertThrows(ValidationException.class, () -> validator.validate(new Example("11", "")));
        Assertions.assertEquals("This is a very important field and it can't be empty!", exception.getMessage());
    }

    @Test
    void sizeWorks() {
        class Example {
            @Size(max = 52, message = "Long live Saint Petersburg!")
            private final String x;

            @Size(min = 5, max = 11)
            private final String y;

            Example(String x, String y) {
                this.x = x;
                this.y = y;
            }
        }

        Assertions.assertDoesNotThrow(() -> validator.validate(new Example("123", "567765")));
        ValidationException exception =
                Assertions.assertThrows(ValidationException.class, () -> validator.validate(new Example("", "")));
        Assertions.assertEquals("Long live Saint Petersburg!", exception.getMessage());
        Assertions.assertThrows(ValidationException.class, () -> validator.validate(new Example("valid", "0000000000000")));
    }
}
