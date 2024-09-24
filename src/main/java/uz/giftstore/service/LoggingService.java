package uz.giftstore.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.giftstore.entity.LogEntry;

public interface LoggingService {
    ResponseEntity<Page<LogEntry>> getAll();

    ResponseEntity<Boolean> deleteLog(Long logId);
    void logErrorSave(String loggerName, String logLevel, String message, String exception);

}
