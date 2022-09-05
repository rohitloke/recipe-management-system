package com.rms.assignment.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.assignment.core.search.service.IndexingService;

@RestController
@RequestMapping("indexing")
public class IndexingController {

    @Autowired
    private IndexingService indexingService;

    @GetMapping
    public void index() {
        indexingService.initiateIndexing();
    }

}
