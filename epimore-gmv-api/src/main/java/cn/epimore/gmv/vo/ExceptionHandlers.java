package cn.epimore.gmv.vo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.MessageFormat;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlers {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandlers.class);

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseEntity<String> onCustomException(CustomException e) {
        logger.error("CustomException: {}", e.getMessage());
        return Result.custom(e.getCode(), e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<String> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errMsg = e.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + ":" + f.getDefaultMessage())
                .collect(Collectors.joining(" | "));
        String msg = MessageFormat.format("Invalid param: [{0}]", errMsg);
        logger.warn("onMethodArgumentNotValidException: {}", msg);
        return Result.common(400, msg, null);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<String> onHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.warn("onHttpMessageNotReadableException: {}", e.getMessage());
        return Result.common(400, "Invalid param", null);
    }
}
