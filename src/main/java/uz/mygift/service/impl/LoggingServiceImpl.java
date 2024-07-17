package uz.mygift.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.mygift.entity.LogEntry;
import uz.mygift.repository.LogEntryRepository;
import uz.mygift.service.LoggingService;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LoggingServiceImpl implements LoggingService {
    private final LogEntryRepository logEntryRepository;
    private static int beginNumber=0;
    private static int sizeNumber=0;
    @Override
    public ResponseEntity<Page<LogEntry>> getAll() {
        sizeNumber +=10;
        return ResponseEntity.ok(logEntryRepository.findAll(PageRequest.of(beginNumber,sizeNumber)));
    }

    @Override
    public ResponseEntity<Boolean> deleteLog(Long logId) {
        Optional<LogEntry> byId = logEntryRepository.findById(logId);
        if (byId.isPresent()){
            byId.get().setDeleted(true);
            logEntryRepository.save(byId.get());
            return ResponseEntity.ok(true);
        }
        throw new EntityNotFoundException("log entity not found");
    }

    @Override
    public void logErrorSave(String loggerName, String logLevel, String message, String exception) {
        LogEntry logEntry = new LogEntry();
        logEntry.setLoggerName(loggerName);
        logEntry.setLogLevel(logLevel);
        logEntry.setMessage(message);
        logEntry.setException(exception);
        logEntryRepository.save(logEntry);
    }
}
