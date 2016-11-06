package cu.uci.auctoritas.domain;

/**
 * Created by bichos on 4/05/16.
 */

public class AuthorizedTerm {
    private String term;
    private String vocabulary_uri;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getVocabulary_uri() {
        return vocabulary_uri;
    }

    public void setVocabulary_uri(String vocabulary_uri) {
        this.vocabulary_uri = vocabulary_uri;
    }
}
