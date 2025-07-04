package com.patterns.demo.actions;

import com.patterns.demo.configuration.properties.TestProperties;
import com.patterns.demo.mapper.JsonMapper;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

/**
 * Template Pattern para las acciones REST
 */
public abstract class AbstractRestAction{
    
    protected ActionResult executeGet(String url, TestProperties config) throws Exception {
        Response response = Request.get(url)
                .addHeader("Content-Type", "application/json")
                .execute();
        return processResponse(response);
    }

    protected ActionResult executePost(String url, Object body, TestProperties config) throws Exception {
        String jsonBody = JsonMapper.getInstance().toJson(body);
        Response response = Request.post(url)
                .bodyString(jsonBody, ContentType.APPLICATION_JSON)
                .addHeader("Content-Type", "application/json")
                .execute();
        return processResponse(response);
    }

    private ActionResult processResponse(Response response) throws Exception {
        ClassicHttpResponse httpResponse = (ClassicHttpResponse) response.returnResponse();
        String responseBody = EntityUtils.toString(httpResponse.getEntity());
        
        Map<String, String> headers = new HashMap<>();
        Arrays.stream(httpResponse.getHeaders()).forEach(header -> 
            headers.put(header.getName(), header.getValue()));

        return ActionResult.builder()
                .statusCode(httpResponse.getCode())
                .rawBody(responseBody)
                .headers(headers)
                .build();
    }

    protected String buildUrl(TestProperties config, String path) {
        return config.getRest().getBaseUrl() + path;
    }
} 