package cu.uci.auctoritas.domain;


import java.util.ArrayList;
import java.util.List;

public class AuthorizedTerm {
    private String term;
    private String vocabulary_uri;
    private List<Relation> relatedConcepts;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setVocabulary_uri(String vocabulary_uri) {
        this.vocabulary_uri = vocabulary_uri;
    }

    public String getVocabulary_uri() {
        return vocabulary_uri;
    }

    public List<Relation> getRelatedConcepts() {
        return relatedConcepts;
    }

    public void setRelatedConcepts(List<Relation> relatedConcepts) {
        if (null == relatedConcepts)
            relatedConcepts = new ArrayList<>();

        for (Relation relatedConcept : relatedConcepts) {
            if (!this.relatedConcepts.contains(relatedConcept))
                this.relatedConcepts.add(relatedConcept);
        }
    }

    public void addRelatedConcept(Relation relation) {
        if (null == relatedConcepts)
            relatedConcepts = new ArrayList<>();
        for (Relation relatedConcept : relatedConcepts) {
            if (relatedConcept.getConcept().getTerm().equals(relation.getConcept().getTerm()))
                return;
        }

        relatedConcepts.add(relation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorizedTerm that = (AuthorizedTerm) o;

        if (!term.equals(that.term)) return false;
        if (vocabulary_uri != null ? !vocabulary_uri.equals(that.vocabulary_uri) : that.vocabulary_uri != null)
            return false;
        return relatedConcepts != null ? relatedConcepts.equals(that.relatedConcepts) : that.relatedConcepts == null;

    }

    @Override
    public int hashCode() {
        int result = term.hashCode();
        result = 31 * result + (vocabulary_uri != null ? vocabulary_uri.hashCode() : 0);
        result = 31 * result + (relatedConcepts != null ? relatedConcepts.hashCode() : 0);
        return result;
    }
}
