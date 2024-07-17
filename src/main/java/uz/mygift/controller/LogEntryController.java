package uz.mygift.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.mygift.base.BaseUrl;
import uz.mygift.entity.LogEntry;
import uz.mygift.service.LoggingService;

@RestController
@RequestMapping(BaseUrl.LOG_ERROR)
@RequiredArgsConstructor
public class LogEntryController {
    private final LoggingService loggingService;

    @GetMapping(BaseUrl.GET_ALL)
    public ResponseEntity<Page<LogEntry>> getAll(){
        return loggingService.getAll();
    }

    @GetMapping(BaseUrl.DELETE_BY_ID)
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id){
        return loggingService.deleteLog(id);
    }
}
