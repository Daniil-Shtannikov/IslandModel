package com.example.islandmodule.lifeform.animal.herbivore;

import com.example.islandmodule.lifeform.animal.Animal;
public abstract class Herbivore extends Animal {
    public Herbivore(double weight, int step, double maxHp, int maxPopulation, String name) {
        super(weight, step, maxHp, maxPopulation, name);
    }
    @Override
    public double getChanceToEat(String foodName){
         switch (foodName){
            case "Plant": return 1;
            default: return 0;
        }
    }
}
