package co.id.example;

import co.id.example.model.DrinkType;
import java.util.ArrayList;
import java.util.List;

public class DrinkService {

    public List getAvailableBrands(DrinkType type){

        List brands = new ArrayList();

        if(type.equals(DrinkType.JUICE)){
            brands.add("Orange Juice");
            brands.add(("Blueberry Juice"));
            brands.add(("Grapefruit Juice"));
            brands.add((" Cranberry Juice"));

        }else if(type.equals(DrinkType.SOFTDRINK)){
            brands.add("Cocola");
            brands.add("Fanta");
            brands.add("Sprite");
            brands.add("7up");

        }else {
            brands.add("No Brand Available");
        }
        return brands;
    }
}