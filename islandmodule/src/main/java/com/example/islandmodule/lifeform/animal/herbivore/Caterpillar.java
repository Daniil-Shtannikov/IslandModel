package com.example.islandmodule.lifeform.animal.herbivore;

import com.example.islandmodule.field.IslandField;
import com.example.islandmodule.field.Location;
import com.example.islandmodule.lifeform.animal.Animal;
import com.example.islandmodule.lifeform.animal.predator.Eagle;
public class Caterpillar extends Herbivore {
    public Caterpillar(){
        super(0.01, 0, 0, 1000, "Caterpillar");
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Caterpillar){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Caterpillar(), location.getRow(), location.getColumn());
        }
    }
}
