package com.example.islandmodule.lifeform.animal.predator;

import com.example.islandmodule.field.IslandField;
import com.example.islandmodule.field.Location;
import com.example.islandmodule.lifeform.animal.Animal;

public class Wolf extends Predator {
    public Wolf() { super(50,3,8,30,"Wolf"); }

    @Override
    public double getChanceToEat(String foodName){
        switch (foodName)
        {
            case "Horse": return 0.1;
            case "Buffalo": return 0.1;
            case "WildBoar": return 0.15;
            case "Deer":  return 0.15;
            case "Duck": return 0.4;
            case "Goat": return 0.6;
            case "Rabbit": return 0.6;
            case "Sheep": return 0.7;
            case "Mouse": return 0.8;
            default: return 0;

        }
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Wolf) {
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Wolf(), location.getRow(), location.getColumn());
        }
    }
}
