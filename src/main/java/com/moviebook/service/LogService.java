package com.moviebook.service;

import com.moviebook.model.Log;
import com.moviebook.repository.LogRepository;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void criarLog(Log log){
        logRepository.save(log);
    }

}
