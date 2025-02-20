package com.example.islandmodule.lifeform.animal.predator;

import com.example.islandmodule.field.IslandField;
import com.example.islandmodule.field.Location;
import com.example.islandmodule.lifeform.animal.Animal;
public class Eagle extends Predator {
    public Eagle() { super(6,3,1,20,"Eagle"); }

    @Override
    public double getChanceToEat(String foodName){
        switch (foodName)
        {
            case "Fox": return 0.1;
            case "Duck": return 0.8;
            case "Rabbit": return 0.9;
            case "Mouse": return 0.9;
            default: return 0;
        }
    }

    @Override
    public void multiply(Animal partner){
        if (partner instanceof Eagle){
            Location location = IslandField.getInstance().getLocation(partner.getRow(),
                    partner.getColumn());
            IslandField.getInstance().addAnimal(new Eagle(), location.getRow(), location.getColumn());
        }
    }
}
