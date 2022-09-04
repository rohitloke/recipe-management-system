package com.rms.assignment.core.search.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.stereotype.Service;

import com.rms.assignment.core.search.service.IndexingService;

/**
 * Service to create Index for entities using Lucene
 * 
 * @author Rohit
 */
@Service
public class LuceneIndexingService implements IndexingService {
    private static final Logger LOG = LogManager.getLogger(LuceneIndexingService.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void initiateIndexing() {
        LOG.info("Initiating indexing...");
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
        try {
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            LOG.error("Indexing failed, seach will not work correctly", e);
        }
        LOG.info("Indexind finished");
    }

}
