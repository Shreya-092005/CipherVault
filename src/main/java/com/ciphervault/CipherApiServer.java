package com.ciphervault;

import com.ciphervault.cipher.CaesarCipher;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class CipherApiServer {

    private final HttpServer server;

    public CipherApiServer(int port) throws IOException {

        server = HttpServer.create(
                new InetSocketAddress(port),
                0
        );

        server.createContext("/api/encrypt", this::handleEncrypt);

        server.setExecutor(null);
    }

    public void start() {
        server.start();
        System.out.println(
                "CipherVault API running on port "
                        + server.getAddress().getPort()
        );
    }

    public void stop() {
        server.stop(0);
    }

    private void handleEncrypt(HttpExchange exchange) throws IOException {

        if (!exchange.getRequestMethod().equalsIgnoreCase("GET")) {

            sendResponse(
                    exchange,
                    405,
                    "Method Not Allowed"
            );

            return;
        }

        URI uri = exchange.getRequestURI();

        Map<String, String> params =
                parseQuery(uri.getRawQuery());

        String text = params.get("text");
        String key = params.get("key");

        if (text == null || key == null) {

            sendResponse(
                    exchange,
                    400,
                    "Missing text or key"
            );

            return;
        }

        try {

            int shift = Integer.parseInt(key);

            String encrypted =
                    CaesarCipher.encrypt(text, shift);

            sendResponse(
                    exchange,
                    200,
                    encrypted
            );

        } catch (NumberFormatException e) {

            sendResponse(
                    exchange,
                    400,
                    "Key must be a number"
            );
        }
    }

    private Map<String, String> parseQuery(String query) {

        Map<String, String> params = new HashMap<>();

        if (query == null || query.isEmpty()) {
            return params;
        }

        for (String parameter : query.split("&")) {

            String[] pair = parameter.split("=", 2);

            if (pair.length == 2) {

                String key =
                        URLDecoder.decode(
                                pair[0],
                                StandardCharsets.UTF_8
                        );

                String value =
                        URLDecoder.decode(
                                pair[1],
                                StandardCharsets.UTF_8
                        );

                params.put(key, value);
            }
        }

        return params;
    }

    private void sendResponse(
            HttpExchange exchange,
            int statusCode,
            String response
    ) throws IOException {

        byte[] responseBytes =
                response.getBytes(StandardCharsets.UTF_8);

        exchange.sendResponseHeaders(
                statusCode,
                responseBytes.length
        );

        try (OutputStream outputStream =
                     exchange.getResponseBody()) {

            outputStream.write(responseBytes);
        }
    }
}
