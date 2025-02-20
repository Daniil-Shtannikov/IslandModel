package com.example.islandmodule.lifeform.animal.predator;

import com.example.islandmodule.field.IslandField;
import com.example.islandmodule.field.Location;
import com.example.islandmodule.lifeform.animal.Animal;
public class Bear extends Predator {
    public Bear() { super(500,2,80,5,"Bear"); }

    @Override
    public double getChanceToEat(String foodName){
        switch (foodName)
        {
            case "Duck": return 0.1;
            case "Buffalo": return 0.2;
            case "Horse": return 0.4;
            case "WildBoar": return 0.5;
            case "Goat" : return 0.7;
            case "Sheep": return 0.7;
            case "Deer": return 0.8;
            case "Rabbit": return 0.8;
            case "Snake": return 0.8;
            case "Mouse": return 0.9;
            default: return 0;
        }
    }

    @Override
    public void multiply(Animal partner){
        if (partner instanceof Bear){
            Location location = IslandField.getInstance().getLocation(partner.getRow(),
                                                                      partner.getColumn());
            IslandField.getInstance().addAnimal(new Bear(), location.getRow(), location.getColumn());
        }
    }
}
