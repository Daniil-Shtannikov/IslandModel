package com.example.islandmodule.lifeform.animal.herbivore;

import com.example.islandmodule.field.IslandField;
import com.example.islandmodule.field.Location;
import com.example.islandmodule.lifeform.animal.Animal;

public class Deer extends Herbivore{

    public Deer(){
        super(0.01, 0, 0, 1000, "Caterpillar");
    }
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Deer){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Deer(), location.getRow(), location.getColumn());
        }
    }
}
