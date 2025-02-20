package com.example.islandmodule.lifeform.animal.herbivore;

import com.example.islandmodule.field.IslandField;
import com.example.islandmodule.field.Location;
import com.example.islandmodule.lifeform.animal.Animal;

public class Mouse extends Herbivore{
    public Mouse(){
        super(0.05, 1, 0.01, 500, "Mouse");
    }

    @Override
    public double getChanceToEat(String foodName) {
        switch (foodName) {
            case "Caterpillar": return 0.9;
            case "Plant": return 1;
            default: return 0;
        }
    }
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Mouse){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Mouse(), location.getRow(), location.getColumn());
        }
    }
}
