package cu.uci.auctoritas.util;

/**
 * Created by bichos on 7/05/16.
 */
public class OntologyUtil {
    public static String clean(String data){
        int pos = data.indexOf("^^");
        data = pos >= 0 ? data.substring(0, pos) : data;
        return data.replace("\"","");
    }
    public static String clean2(String data){
        data = data.replace(">","");
        return data.replace("<","");
       }
}
