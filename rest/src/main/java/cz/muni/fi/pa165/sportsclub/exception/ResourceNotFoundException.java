package cz.muni.fi.pa165.sportsclub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author 445403 Kristian Katanik
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="The requested resource was not found")
public class ResourceNotFoundException extends RuntimeException {

}