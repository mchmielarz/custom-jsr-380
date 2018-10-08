package pl.devthoughts.jsr380;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.validation.FieldError;

@Getter
@RequiredArgsConstructor
class ValidationFailureResponse {
    private final FieldError[] fieldErrors;
}
