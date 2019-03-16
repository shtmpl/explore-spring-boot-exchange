package explore.spring.exchange.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "api_exchange")
@SequenceGenerator(name = "api_exchange_id_seq")
public class ApiExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "api_exchange_id_seq")
    @Column
    private Long id;

    @Column
    private Date dateCreated;

    @ManyToOne
    private ApiExchange parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ApiExchange> children;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "url", column = @Column(name = "request_url")),
            @AttributeOverride(name = "method", column = @Column(name = "request_method")),
            @AttributeOverride(name = "body", column = @Column(name = "request_body", columnDefinition = "TEXT"))
    })
    private ApiRequest request;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "statusCode", column = @Column(name = "response_status_code")),
            @AttributeOverride(name = "body", column = @Column(name = "response_body", columnDefinition = "TEXT"))
    })
    private ApiResponse response;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public ApiExchange getParent() {
        return parent;
    }

    public void setParent(ApiExchange parent) {
        this.parent = parent;
    }

    public Set<ApiExchange> getChildren() {
        return children;
    }

    public void setChildren(Set<ApiExchange> children) {
        this.children = children;
    }

    public ApiRequest getRequest() {
        return request;
    }

    public void setRequest(ApiRequest request) {
        this.request = request;
    }

    public ApiResponse getResponse() {
        return response;
    }

    public void setResponse(ApiResponse response) {
        this.response = response;
    }
}
