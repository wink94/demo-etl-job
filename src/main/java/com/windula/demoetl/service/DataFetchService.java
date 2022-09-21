package com.windula.demoetl.service;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * The interface Data fetch service.
 */
public interface DataFetchService {

    /**
     * Gets data.
     *
     * @param page the page
     * @return the data
     */
    JsonNode getData(int page);
}
