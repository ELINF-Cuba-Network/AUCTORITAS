package cu.uci.auctoritas.source;

import cu.uci.auctoritas.domain.PersonalAuthor;
import cu.uci.auctoritas.source.mapper.EntityMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leandro on 2/9/17.
 */
public class DatasourceORCIDResolver<T> implements DatasourceResolver<T> {

    @Override
    public List<T> getElementByDynamicQuery(String query, String endpoint, String username, String password, Class<T> clazz) {

        List<T> result = new ArrayList<>();

        try {
            final String CONTENT_TYPE = "application/orcid+json";
            String urlString = endpoint + query;
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(urlString);
            request.addHeader(HttpHeaders.ACCEPT, CONTENT_TYPE);
            request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer bd790c69-def5-4dcd-ad67-472462161364");

            HttpResponse response = client.execute(request);
            InputStream is = response.getEntity().getContent();
            JsonReader reader = Json.createReader(is);

            JsonObject jsonObject = reader.readObject();
            JsonObject orcidSearchResultsObject = jsonObject.getJsonObject("orcid-search-results");
            JsonArray orcidSearchResultsArray = orcidSearchResultsObject.getJsonArray("orcid-search-result");


            for (JsonObject orcidSearchResult : orcidSearchResultsArray.getValuesAs(JsonObject.class)) {
                PersonalAuthor author = new PersonalAuthor();
                JsonObject orcidProfile = orcidSearchResult.getJsonObject("orcid-profile");
                JsonObject orcidIdentifier = orcidProfile.getJsonObject("orcid-identifier");

                author.setUri(orcidIdentifier.getString("uri"));
                author.setAuthority(orcidIdentifier.getString("path"));

                JsonObject orcidBio = orcidProfile.getJsonObject("orcid-bio");
                JsonObject orcidPersonalDetails = orcidBio.getJsonObject("personal-details");

                author.setName(orcidPersonalDetails.getJsonObject("given-names").getString("value"));
                author.setLastname(orcidPersonalDetails.getJsonObject("family-name").getString("value"));

                result.add((T) author);
            }

            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void postElementByDynamicQuery(String query, String endpoint, String username, String password, Class<T> clazz) {

    }

    private EntityMapper<T> getRowMapper(Class<T> clazz) {
        return null;
    }

}
