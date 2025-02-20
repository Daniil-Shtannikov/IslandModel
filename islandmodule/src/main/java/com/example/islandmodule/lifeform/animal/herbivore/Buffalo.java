package com.example.islandmodule.lifeform.animal.herbivore;

import com.example.islandmodule.field.IslandField;
import com.example.islandmodule.field.Location;
import com.example.islandmodule.lifeform.animal.Animal;
import com.example.islandmodule.lifeform.animal.predator.Eagle;

public class Buffalo extends Herbivore{
    public Buffalo(){
        super(700,3,100,10,"Buffalo");
    }

    @Override
    public void multiply(Animal partner){
        if (partner instanceof Buffalo){
            Location location = IslandField.getInstance().getLocation(partner.getRow(),
                    partner.getColumn());
            IslandField.getInstance().addAnimal(new Buffalo(), location.getRow(), location.getColumn());
        }
    }
}
