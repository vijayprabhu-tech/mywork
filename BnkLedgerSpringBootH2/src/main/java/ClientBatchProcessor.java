import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ClientBatchProcessor {

    // Fix thread count to control the degree of parallelism (batch size)
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    private final HttpClient httpClient = HttpClient.newBuilder()
            .executor(executor)
            .build();

    public void processUrlsInBatch(List<String> urls) {
        // Map URLs into individual asynchronous HTTP requests
        List<CompletableFuture<String>> futures = urls.stream()
                .map(url -> {
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .GET()
                            .build();

                    return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                            .thenApply(HttpResponse::body)
                            .exceptionally(ex -> "Error fetching " + url + ": " + ex.getMessage());
                })
                .collect(Collectors.toList());

        // Wait for the entire batch to complete execution
        CompletableFuture<Void> allOf = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
        );

        // Process all combined results once the batch completes
        allOf.thenRun(() -> {
            List<String> results = futures.stream()
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList());

            results.forEach(System.out::println);
        }).join(); // Blocks parent thread until batch is fully processed

        executor.shutdown();
    }
}
