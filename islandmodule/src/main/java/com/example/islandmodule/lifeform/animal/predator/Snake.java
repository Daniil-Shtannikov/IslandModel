package com.example.islandmodule.lifeform.animal.predator;

import com.example.islandmodule.field.IslandField;
import com.example.islandmodule.field.Location;
import com.example.islandmodule.lifeform.animal.Animal;
public class Snake extends Predator {
    public Snake() { super(15,1,3,30,"Snake"); }

    @Override
    public double getChanceToEat(String foodName){
        switch (foodName)
        {
            case "Duck": return 0.1;
            case "Fox": return 0.15;
            case "Rabbit": return 0.2;
            case "Mouse": return 0.4;
            default: return 0;
        }
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Snake) {
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Snake(), location.getRow(), location.getColumn());
        }
    }
}
