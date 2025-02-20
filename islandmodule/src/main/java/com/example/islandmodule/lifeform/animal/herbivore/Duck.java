package com.example.islandmodule.lifeform.animal.herbivore;

import com.example.islandmodule.field.IslandField;
import com.example.islandmodule.field.Location;
import com.example.islandmodule.lifeform.animal.Animal;

public class Duck extends Herbivore{
    public Duck(){
        super(300, 4, 50, 20, "Deer");
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
        if (partner instanceof Duck){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Duck(), location.getRow(), location.getColumn());
        }
    }
}
