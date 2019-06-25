package explore.spring.boot.exchange.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ApiRequest {

    @Column
    private String url;

    @Column
    private String method;

    @Column(columnDefinition = "TEXT")
    private String body;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
