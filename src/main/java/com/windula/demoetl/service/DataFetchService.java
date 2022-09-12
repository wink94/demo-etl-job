package com.windula.demoetl.service;

import com.fasterxml.jackson.databind.JsonNode;

public interface DataFetchService {

    JsonNode getData(int page);
}
