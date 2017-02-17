package cu.uci.auctoritas.domain;

/**
 * Created by leandro on 2/16/17.
 */
public class Relation {
    public static final String RELATION_TYPE_BROADER_TERM = "Broader Term";
    public static final String RELATION_TYPE_NARROWER_TERM = "Narrower Term";

    private String type;
    private AuthorizedTerm concept;

    public Relation(String type, AuthorizedTerm concept) {
        this.type = type;
        this.concept = concept;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AuthorizedTerm getConcept() {
        return concept;
    }

    public void setConcept(AuthorizedTerm concept) {
        this.concept = concept;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Relation relation = (Relation) o;

        if (!type.equals(relation.type)) return false;
        return concept.equals(relation.concept);

    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + concept.hashCode();
        return result;
    }
}
