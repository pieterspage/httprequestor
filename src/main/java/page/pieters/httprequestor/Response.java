package page.pieters.httprequestor;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.HashMap;
import tools.jackson.databind.ObjectMapper;

public class Response<T> {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    Integer statusCode;
    String rawBody;
    HashMap<String, String> headers;
    T body;

    @SuppressWarnings("unchecked")
    Response(HttpResponse<String> response, Type type) {
        try {
            this.rawBody = response.body();
            this.statusCode = response.statusCode();

            if (type == String.class) {
                this.body = (T) this.rawBody;
            } else if (type != null) {
                this.body = MAPPER.readValue(this.rawBody, MAPPER.constructType(type));
            } else {
                this.body = (T) MAPPER.readValue(this.rawBody, Object.class);
            }
        } catch (Exception e) {
            System.out.println("HttpRequestor Response Exception:");
            System.out.println(e);
        }
    }
}
