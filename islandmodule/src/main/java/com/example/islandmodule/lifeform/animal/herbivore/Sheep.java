package com.example.islandmodule.lifeform.animal.herbivore;

import com.example.islandmodule.field.IslandField;
import com.example.islandmodule.field.Location;
import com.example.islandmodule.lifeform.animal.Animal;

public class Sheep extends Herbivore{
    public Sheep (){
        super(70, 3, 15, 140, "Sheep");
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Sheep){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Sheep(), location.getRow(), location.getColumn());
        }
    }
}
