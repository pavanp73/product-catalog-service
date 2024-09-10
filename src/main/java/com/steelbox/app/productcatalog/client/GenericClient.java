package com.steelbox.app.productcatalog.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public interface GenericClient {

    default <T> ResponseEntity<T> requestForEntity(String url,
                                                   HttpMethod httpMethod,
                                                   Class<T> responseType,
                                                   Object... uriVariables) throws RestClientException {
        return requestForEntity(url, null, httpMethod, responseType, uriVariables);
    }

    default <T> ResponseEntity<T> requestForEntity(String url,
                                                  @Nullable Object request,
                                                  HttpMethod httpMethod,
                                                  Class<T> responseType,
                                                  Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

}
