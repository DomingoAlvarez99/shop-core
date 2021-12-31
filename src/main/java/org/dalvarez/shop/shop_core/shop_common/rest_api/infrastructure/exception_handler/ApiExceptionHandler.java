package org.dalvarez.shop.shop_core.shop_common.rest_api.infrastructure.exception_handler;

import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.NonUniqueResultException;
import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.filter.WrongFilterException;
import org.dalvarez.shop.shop_core.shop_common.persistence.infrastructure.shared.exception.ConflictException;
import org.dalvarez.shop.shop_core.shop_common.persistence.infrastructure.shared.exception.NotFoundException;
import org.dalvarez.shop.shop_core.shop_common.shared.domain.log.Logger;
import org.dalvarez.shop.shop_core.shop_common.shared.infrastructure.validation.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.PersistenceException;

@RestControllerAdvice
public class ApiExceptionHandler {

    private static final String UNKNOWN = "Unknown";

    private final Logger log;

    public ApiExceptionHandler(final Logger log) {
        this.log = log;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFoundException(final Exception ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            BadRequestException.class,
            IllegalArgumentException.class,
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class,
            WrongFilterException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleBadRequestException(final Exception ex) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            ConflictException.class,
            PersistenceException.class,
            NonUniqueResultException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleConflictException(final Exception ex) {
        return buildErrorResponse(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleInternalServerErrorException(final Exception ex) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(final Exception ex,
                                                             final HttpStatus httpStatus) {
        return new ResponseEntity<>(getErrorResponse(ex, httpStatus), httpStatus);
    }

    private ErrorResponse getErrorResponse(final Exception ex,
                                           final HttpStatus httpStatus) {
        final String causeMessage = ex.getMessage();
        final int httpCode = httpStatus.value();
        final String httpName = httpStatus.name();
        final String clazz = getClassName(ex);
        final String method = getMethodName(ex);
        final int line = getLine(ex);

        ex.printStackTrace();

        log.error(
                "Exception captured: [cause {}, status {}, class {}, method {}, line {}]",
                causeMessage,
                httpCode,
                clazz,
                method,
                line
        );

        return new ErrorResponse(
                httpCode,
                httpName,
                causeMessage
        );
    }

    private String getMethodName(final Exception ex) {
        if (hasAnyTrace(ex))
            return ex.getStackTrace()[0].getMethodName();

        return UNKNOWN;
    }

    private int getLine(final Exception ex) {
        if (hasAnyTrace(ex))
            return ex.getStackTrace()[0].getLineNumber();

        return 0;
    }

    private boolean hasAnyTrace(final Exception ex) {
        return ex.getStackTrace().length > 0;
    }

    private String getClassName(final Exception ex) {
        try {
            return Class.forName(ex.getStackTrace()[0].getClassName())
                        .getName();

        } catch (final Exception ignored) {
            return UNKNOWN;
        }
    }

}
