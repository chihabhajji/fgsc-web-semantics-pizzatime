package tn.esprit.fgsc.ws_pizza_ontology.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tn.esprit.fgsc.ws_pizza_ontology.test.utils.OwlReaderUtil;

import java.util.*;

@Slf4j
@RestController
public class Subclasses {
    // http://localhost:8080/subclasses?type=Salty_Topping
    @GetMapping("/subclasses")
    protected Map<String, Object> getSubclasses(@RequestParam String type) {
        Map<String, Object> response = new HashMap<>();
        response.put("subclasses", getTypeSubclasses(type));
        return response;
    }
    private List<String> getTypeSubclasses(String type) {
        String queryString =
                OwlReaderUtil.QUERY_PREFIX
                        + " "
                        + "SELECT DISTINCT ?x "
                        + "WHERE "
                        + "{ "
                        + "?x		rdfs:subClassOf 	pizza:%s"
                        + " }";
        // String.format(queryString, type)
        return OwlReaderUtil.executeQueryOneColumn(String.format(queryString, type));
    }
    // http://localhost:8080/subclasses?superClass=Pizza
    //
//    @GetMapping("/xyz")
//    public List<Map<String, String>> getIndividus(@RequestParam String superClass) {
//        List<Map<String, String>> response= new ArrayList<>();
//        Iterator<OntClass> individus = Objects.requireNonNull(OwlReaderUtil.readModel()).();
//        while (individus.hasNext()) {
//            OntClass sub = individus.next();
//            OntClass sc = sub.getModel().;
//            if(sc != null){
//                if(sc.getLocalName().endsWith(superClass)){
//                    Map<String , String> obj = new HashMap<>();
//                    obj.put("name",sub.getLocalName());
//                    obj.put("uri",sub.getURI());
//                    response.add(obj);
//                }
//            }
//
//        }
//        return response;
//    }


}
