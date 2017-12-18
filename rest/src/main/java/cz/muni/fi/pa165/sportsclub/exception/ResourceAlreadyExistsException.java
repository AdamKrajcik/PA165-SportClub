package cz.muni.fi.pa165.sportsclub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author 445403 Kristian Katanik
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason="The resource already exists")
public class ResourceAlreadyExistsException extends RuntimeException {
}
