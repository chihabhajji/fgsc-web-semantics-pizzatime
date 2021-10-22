package tn.esprit.fgsc.ws_pizza_ontology.test;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.RDFS;
import org.springframework.web.bind.annotation.*;
import tn.esprit.fgsc.ws_pizza_ontology.test.utils.OwlReaderUtil;

import java.util.*;


@RestController
@RequestMapping("/generic")
public class GetOwlData {
    // WORKS
    // http://localhost:8080/generic?key=hasCrust&value=hasTopping
    @GetMapping
    public HashMap<String, Object> getCrustAndTopping(@RequestParam String key, @RequestParam String value) {
        HashMap<String, Object> response = new HashMap<>();
        response.put(key, getCrust(key));
        response.put(value, getTopping(value));
        return  response;
    }

    private List<String> getCrust(String key) {
        String queryString =
                OwlReaderUtil.QUERY_PREFIX
                + " "
                + "SELECT DISTINCT  ?x "
                + "WHERE "
                + "{"
                + "    ?d     pizza:%s    ?x"
                + "}";

        return OwlReaderUtil.executeQueryOneColumn(String.format(queryString,key));
    }
    
    private List<String> getTopping(String key) {        

        String queryString =
                OwlReaderUtil.QUERY_PREFIX
                + " "
                + "SELECT DISTINCT  ?x "
                + "WHERE "
                + "{"
                + "    ?d     pizza:%s    ?x"
                + "}";

        return OwlReaderUtil.executeQueryOneColumn(String.format(queryString,key));
    }

    @GetMapping(value = "/properties")
    public List<Map<String,String>> getClassProperties(@RequestParam String className) {
        ArrayList<Map<String, String>> list= new ArrayList();
        OntClass ontClass = OwlReaderUtil.readModel().getOntClass("http://www.semanticweb.org/firas/ontologies/2021/9/pizza-ontology#".concat(className) );
        Iterator<OntProperty> subIter = ontClass.listDeclaredProperties();
        while (subIter.hasNext()) {
            OntProperty property = subIter.next();
            Map<String, String> obj = new HashMap<>();
            obj.put("propertyName",property.getLocalName());
            obj.put("propertyType",property.getRDFType().getLocalName());
            if(property.getDomain()!=null) obj.put("domain", property.getDomain().getLocalName());
            if(property.getRange()!=null) obj.put("range",property.getRange().getLocalName());
            list.add(obj);
        }
        return list;
    }
}
