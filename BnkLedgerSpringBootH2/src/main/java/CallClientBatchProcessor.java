import java.util.ArrayList;
import java.util.List;

public class CallClientBatchProcessor {

    public static void main(String args[]){
        ClientBatchProcessor cbp = new ClientBatchProcessor();
        List<String> urls = new ArrayList<String>();

        urls.add("http://localhost:8080/api/events");
        urls.add("http://localhost:8080/api/hello");
        urls.add("http://localhost:8080/api/events/evt-001");
        cbp.processUrlsInBatch(urls);
    }
}
