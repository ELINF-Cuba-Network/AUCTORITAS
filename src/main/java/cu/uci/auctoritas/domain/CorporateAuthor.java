package cu.uci.auctoritas.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by bichos on 4/05/16.
 */
@XmlRootElement(name = "corpo")
@Entity
public class CorporateAuthor {

    @Id
    private String uri;
    private String name;
    private String authority;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
