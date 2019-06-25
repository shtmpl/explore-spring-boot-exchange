package explore.spring.boot.exchange.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ApiResponse {

    @Column
    private Integer statusCode;

    @Column(columnDefinition = "TEXT")
    private String body;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
