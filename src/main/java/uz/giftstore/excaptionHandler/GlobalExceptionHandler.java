package uz.giftstore.excaptionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.giftstore.service.impl.LoggingServiceImpl;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final LoggingServiceImpl loggingService;

    @Autowired
    public GlobalExceptionHandler(LoggingServiceImpl loggingService) {
        this.loggingService = loggingService;
    }

//    @ExceptionHandler(Exception.class)
//    public void handleException(Exception e) {
//        // Log the exception
////        loggingService.logErrorSave(getClass().getName(), "ERROR", e.getMessage(), e.toString());
//
//        // You can also handle the exception or format the response as needed
//    }
}
