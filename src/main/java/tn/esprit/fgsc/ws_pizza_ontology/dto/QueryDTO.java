package tn.esprit.fgsc.ws_pizza_ontology.dto;

import lombok.Data;

@Data
public class QueryDTO {
    String crust;
    String topping;
    String sauce;
    String type;
    int minCalories;
    int maxCalories;
    int minPrice;
    int maxPrice;
    int limit;
    int offset;
}
