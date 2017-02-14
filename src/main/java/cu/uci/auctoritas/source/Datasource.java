package cu.uci.auctoritas.source;


public class Datasource {

    private String datasource;
    private String mapped;
    private String endpoint;
    private String username;
    private String password;
    private String type;

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    public String getMapped() {
        return mapped;
    }

    public void setMapped(String mapped) {
        this.mapped = mapped;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
