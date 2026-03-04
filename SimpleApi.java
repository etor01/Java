import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleApi {
    public static void main(String[] args) throws IOException {
        // 1. Create the server on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // 2. Define a "Context" (the URL path) and its handler
        server.createContext("/api/hello", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = "{\"message\": \"Hello from Vanilla Java!\"}";
                
                // Set headers (JSON content type and 200 OK status)
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, response.length());

                // Write the response body
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            }
        });

        // 3. Start the server
        System.out.println("Server started on http://localhost:8080/api/hello");
        server.start();
    }
}