package tn.esprit.fgsc.ws_pizza_ontology.test;

import org.springframework.web.bind.annotation.*;
import tn.esprit.fgsc.ws_pizza_ontology.dto.QueryDTO;
import tn.esprit.fgsc.ws_pizza_ontology.test.utils.OwlReaderUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RequestMapping("/pizzas/query")
@RestController
public class QueryPizzas {
    // WORKS
    // http://localhost:8080/pizzas/query
    // you can select more properties by execute2colum
    @PostMapping
    public List<String> doGet(@RequestBody QueryDTO query) {
        String queryString = OwlReaderUtil.QUERY_PREFIX + " "
                + "SELECT   ?x ?y ?price "
                + "WHERE "
                + "{ "
                + (query.getType()    != null && !query.getType().isEmpty()    ? "?x rdf:type 	      pizza:" + query.getType()    + " . " : "")
                + (query.getCrust()   != null && !query.getCrust().isEmpty()   ? "?x pizza:hasCrust   pizza:" + query.getCrust()   + " . " : "")
                + (query.getTopping() != null && !query.getTopping().isEmpty() ? "?x pizza:hasTopping pizza:" + query.getTopping() + " . " : "")
                + (query.getSauce()   != null && !query.getSauce().isEmpty()   ? "?x pizza:hasSauce   pizza:" + query.getSauce()   + " . " : "")
                + " ?x     pizza:Calories     ?y     . "
                + " ?x     pizza:Price        ?price . "
                + (query.getMinCalories() != 0 && query.getMaxCalories() != 0 ?" FILTER (?y     > "+query.getMinCalories()+") FILTER ( ?y     < "+query.getMaxCalories()+" ) ": "")
                + (query.getMinPrice()    != 0 && query.getMaxPrice()    != 0 ?" FILTER (?price > "+query.getMinPrice()   +") FILTER ( ?price < "+query.getMaxPrice()   +" ) ": "")
                + "} "
                + (query.getLimit() != 0 ? " LIMIT "+query.getLimit() : "")
                + "OFFSET %s ";
        return OwlReaderUtil.executeQueryOneColumn(String.format(queryString,query.getOffset()));
    }
//{
//    "crust":"Cheesy_Edge",
//    "topping":"Black_Olives",
//    "sauce": "Tomato",
//    "minCalories":300,
//    "maxCalories":400,
//    "minPrice":1,
//    "maxPrice":100,
//    "limit": 10,
//    "offset": 0
//}
}
