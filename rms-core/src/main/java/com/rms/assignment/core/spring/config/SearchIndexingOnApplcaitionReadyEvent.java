package com.rms.assignment.core.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.rms.assignment.core.search.service.IndexingService;

@Component
public class SearchIndexingOnApplcaitionReadyEvent implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private IndexingService indexingService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        indexingService.initiateIndexing();
    }
}
