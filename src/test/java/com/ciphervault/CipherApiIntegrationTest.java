package com.ciphervault;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CipherApiIntegrationTest {

    @Test
    void testEncryptApi() throws Exception {

        CipherApiServer server =
                new CipherApiServer(8081);

        server.start();

        try {

            HttpClient client =
                    HttpClient.newHttpClient();

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(
                                    URI.create(
                                            "http://localhost:8081/api/encrypt"
                                                    + "?text=HELLO&key=3"
                                    )
                            )
                            .GET()
                            .build();

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            assertEquals(200, response.statusCode());

            assertEquals(
                    "KHOOR",
                    response.body()
            );

        } finally {

            server.stop();
        }
    }
}