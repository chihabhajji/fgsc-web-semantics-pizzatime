package tn.esprit.fgsc.ws_pizza_ontology.test;

import org.springframework.web.bind.annotation.*;
import tn.esprit.fgsc.ws_pizza_ontology.dto.QueryDTO;
import tn.esprit.fgsc.ws_pizza_ontology.test.utils.OwlReaderUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/pizzas/query")
@RestController
public class QueryPizzas {
    // WORKS
    // http://localhost:8080/pizzas/query
    // you can select more properties by execute2colum
    @PostMapping
    protected List<String> doGet(@RequestBody QueryDTO query) {
        String queryString =
                OwlReaderUtil.QUERY_PREFIX
                + " "
                +"SELECT DISTINCT  ?x ?price"
                + "WHERE "
                + "{ "
                + "    ?x     pizza:hasCrust    pizza:%s."
                + "    ?x     pizza:hasTopping  pizza:%s."
                + "    ?x     pizza:hasSauce    pizza:%s."
                + "    ?x     pizza:Calories    ?y."
                + "    ?x     pizza:Price       ?price."
                + "    FILTER (?y > %s)"
                + "    FILTER (?y < %s)"
                + "    FILTER (?price > %s)  "
                + "    FILTER (?price < %s) "
//                + "    FILTER (regex(?x, \"^\"))" TODO : I THINK ITS DONE WITH rdfs: something
                + "}"
                + "    LIMIT  %s " +
                "      OFFSET %s";
        queryString = String.format(
                queryString,
                query.getCrust(),
                query.getTopping(),
                query.getSauce(),
                query.getMinCalories(),
                query.getMaxCalories(),
                query.getMinPrice(),
                query.getMaxPrice(),
                query.getLimit(),
                query.getOffset()
        );
        return OwlReaderUtil.executeQueryOneColumn(queryString);
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
