package com.example.islandmodule.lifeform.animal.herbivore;

import com.example.islandmodule.field.IslandField;
import com.example.islandmodule.field.Location;
import com.example.islandmodule.lifeform.animal.Animal;

public class WildBoar extends Herbivore{
    public WildBoar(){
        super(400, 2, 50, 50, "WildBoar");
    }

    @Override
    public double getChanceToEat(String foodName) {
        switch (foodName) {
            case "Mouse": return 0.5;
            case "Caterpillar": return 0.9;
            case "Plant": return 1;
            default: return 0;
        }
    }
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof WildBoar){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new WildBoar(), location.getRow(), location.getColumn());
        }
    }
}
