package com.example.islandmodule.lifeform.animal.herbivore;

import com.example.islandmodule.field.IslandField;
import com.example.islandmodule.field.Location;
import com.example.islandmodule.lifeform.animal.Animal;
public class Goat extends Herbivore{

    public Goat(){
        super(60, 3, 10, 140, "Goat");
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Goat){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Goat(), location.getRow(), location.getColumn());
        }
    }
}
