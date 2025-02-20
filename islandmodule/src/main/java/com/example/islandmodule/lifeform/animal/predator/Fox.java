package com.example.islandmodule.lifeform.animal.predator;

import com.example.islandmodule.field.IslandField;
import com.example.islandmodule.field.Location;
import com.example.islandmodule.lifeform.animal.Animal;
public class Fox extends Predator {
    public Fox() { super(8,2,2,30,"Fox"); }

    @Override
    public double getChanceToEat(String foodName){
        switch (foodName)
        {
            case "Caterpillar": return 0.4;
            case "Duck": return 0.6;
            case "Rabbit": return 0.7;
            case "Mouse": return 0.9;
            default: return 0;
        }
    }

    @Override
    public void multiply(Animal partner){
        if (partner instanceof Fox){
            Location location = IslandField.getInstance().getLocation(partner.getRow(),
                    partner.getColumn());
            IslandField.getInstance().addAnimal(new Fox(), location.getRow(), location.getColumn());
        }
    }
}
