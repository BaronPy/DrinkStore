package co.id.example;

import co.id.example.model.LiquorType;
import java.util.ArrayList;
import java.util.List;

public class LiquorService {

    public List getAvailableBrands(LiquorType type){

        List brands = new ArrayList();

        if(type.equals(LiquorType.WINE)){
            brands.add("Adrianna Vineyard");
            brands.add(("J. P. Chenet"));
            brands.add(("Bogle Old Vine California Zinfandel"));
            brands.add(("Pine Ridge Chenin Blanc"));
            brands.add(("Ravenswood Old Vine Lodi Zinfandel"));


        }else if(type.equals(LiquorType.WHISKY)){
            brands.add("Glenfiddich");
            brands.add("Johnnie Walker");
            brands.add("Jack Daniel's Tennessee");
            brands.add("Crown Royal Canadian");
            brands.add("Fireball Cinnamon");
            brands.add("Jim Beam Bourbon");

         }else if(type.equals(LiquorType.BEER)){
            brands.add("Corona");
            brands.add("Skol");
            brands.add("Heineken");
            brands.add("Harbin");

        }else {
            brands.add("No Brand Available");
        }
        return brands;
    }
}