 package uz.pdp.handlers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.exceptions.ItemNotFoundException;

import java.time.LocalDateTime;
import java.util.Map;

    @RestControllerAdvice
    public class GlobalExceptionHandlers {
        @ExceptionHandler(ItemNotFoundException.class)
        public ResponseEntity<Object> notFoundHandler(ItemNotFoundException ex, HttpServletRequest request){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "massege",ex.getMessage(),
                            "path",request.getRequestURI(),
                            "timestamp", LocalDateTime.now().toString()
                    )
            );
        }
    }

