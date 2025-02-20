package com.example.islandmodule.lifeform.animal.herbivore;

import com.example.islandmodule.field.IslandField;
import com.example.islandmodule.field.Location;
import com.example.islandmodule.lifeform.animal.Animal;

public class Rabbit extends Herbivore{
    public Rabbit(){
        super(2, 2, 0.45, 150, "Rabbit");
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Rabbit){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Rabbit(), location.getRow(), location.getColumn());
        }
    }
}
