package com.example.islandmodule.simulation.thread.animalLifecycleTask.Task;

import com.example.islandmodule.field.IslandField;
import com.example.islandmodule.lifeform.animal.Animal;

import java.util.List;
public class AnimalMoveTask implements Runnable {
    @Override
    public void run() {
        List<Animal> animals = IslandField.getInstance().getAllAnimals().stream().filter(c -> c.getStep() > 0).toList();
        for (Animal animal : animals) {
            animal.move();
        }
    }
}
