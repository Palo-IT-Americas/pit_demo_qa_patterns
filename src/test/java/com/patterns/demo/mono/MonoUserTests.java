package com.patterns.demo.mono;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpResponse;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.assertj.core.api.Assertions.*;

public class MonoUserTests {
    
    @Test
    public void testCreateUser() throws Exception {
        // Bad practice: Hardcoded URL
        String url = "https://jsonplaceholder.typicode.com/users";
        
        // Bad practice: Hardcoded JSON as string
        String jsonBody = """
                {
                    "name": "Leanne Graham",
                    "username": "Bret",
                    "email": "Sincere@april.biz",
                    "address": {
                      "street": "Kulas Light",
                      "suite": "Apt. 556",
                      "city": "Gwenborough",
                      "zipcode": "92998-3874",
                      "geo": {
                        "lat": "-37.3159",
                        "lng": "81.1496"
                      }
                    },
                    "phone": "1-770-736-8031 x56442",
                    "website": "hildegard.org",
                    "company": {
                      "name": "Romaguera-Crona",
                      "catchPhrase": "Multi-layered client-server neural-net",
                      "bs": "harness real-time e-markets"
                    }
                }
                """;

        // Bad practice: Direct API call without abstraction
        long startTime = System.currentTimeMillis();
        Response response = Request.post(url)
                .bodyString(jsonBody, ContentType.APPLICATION_JSON)
                .execute();
        long endTime = System.currentTimeMillis();
        
        // Bad practice: Response time check mixed with functional test
        long responseTime = endTime - startTime;
        assertThat(responseTime).isLessThan(1000);

        // Bad practice: Status code validation mixed with other checks
        HttpResponse httpResponse = response.returnResponse();
        assertThat(httpResponse.getCode()).isEqualTo(201);

        // Bad practice: Manual JSON parsing without proper error handling
        String responseBody = response.returnContent().asString();
        JsonNode jsonResponse = new ObjectMapper().readTree(responseBody);
        
        // Bad practice: Multiple assertions without proper description
        assertThat(jsonResponse.has("id")).isTrue();
        assertThat(jsonResponse.get("id").isNull()).isFalse();
    }
}
