package pl.devthoughts.jsr380;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.devthoughts.jsr380.domain.BankAccountFacade;
import pl.devthoughts.jsr380.domain.dto.BankAccountData;

import java.util.List;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bank-accounts")
class BankAccountController {

    private final BankAccountFacade bankAccountFacade;

    @PutMapping("{bankAccountId}")
    private ResponseEntity createBankAccount(@Valid @RequestBody CreateBankAccountRequest request) {
        BankAccountData account = newBankAccount(request);
        bankAccountFacade.save(account);
        return ResponseEntity.ok().build();
    }

    private BankAccountData newBankAccount(CreateBankAccountRequest request) {
        return new BankAccountData(request.getIban(), request.getBic(), request.getBankAccountNumber());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private ValidationFailureResponse validationError(MethodArgumentNotValidException ex) {
        final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        return new ValidationFailureResponse(fieldErrors.toArray(new FieldError[0]));
    }

}
