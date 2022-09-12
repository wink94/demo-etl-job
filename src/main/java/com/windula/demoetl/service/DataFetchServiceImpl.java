package com.windula.demoetl.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.windula.demoetl.exception.APIException;
import com.windula.demoetl.exception.ExceptionEnum;
import com.windula.demoetl.steps.FetchDataInitializeStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DataFetchServiceImpl implements DataFetchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataFetchServiceImpl.class);
    @Value("${urlBase}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public JsonNode getData(int page) {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            String url = baseUrl + "?page=" + page + "&per_page=10";
            ResponseEntity<JsonNode> response = restTemplate.exchange(url, HttpMethod.GET, entity, JsonNode.class);

            if (response.getBody() != null) {
                return response.getBody();
            }
            return null;
        } catch (Exception e) {
            LOGGER.error("data fetch failed for page {}", page, e);
            throw new APIException(ExceptionEnum.API_CALL_FAILURE,e);
        }

    }
}
