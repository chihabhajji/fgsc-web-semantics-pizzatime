package tn.esprit.fgsc.ws_pizza_ontology.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class QueryDTO {
    String crust;
    String topping;
    String sauce;
    int minCalories;
    int maxCalories;
    int minPrice;
    int maxPrice;
    int limit;
    int offset;
}
