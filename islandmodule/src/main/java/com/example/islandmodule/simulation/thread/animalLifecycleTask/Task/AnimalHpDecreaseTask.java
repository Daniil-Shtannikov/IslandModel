package com.example.islandmodule.simulation.thread.animalLifecycleTask.Task;

import android.os.Build;

import com.example.islandmodule.field.*;
import com.example.islandmodule.lifeform.animal.Animal;
import com.example.islandmodule.simulation.IslandSimulation;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class AnimalHpDecreaseTask implements Runnable {
    private double precentOfHpToDecrease = 15;
    private final CountDownLatch latch;
    private  int animalsDiedByHungry;

    public AnimalHpDecreaseTask(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run() {
        animalsDiedByHungry = 0;
        List<Animal> animals = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            animals = IslandField.getInstance().getAllAnimals().stream().filter(c -> c.getMaxHP() > 0).toList();
        }
        if (IslandSimulation.getInstance().getTimeNow() / 60 >= 3) {
            precentOfHpToDecrease = precentOfHpToDecrease * 2;
        }
        for (Animal animal : animals) {
            double hpToDecrease = animal.getMaxHP() * precentOfHpToDecrease / 100.0;
            if (animal.getHp() - hpToDecrease > 0) {
                animal.setHp(animal.getHp() - hpToDecrease);
            } else {
                Location location = IslandField.getInstance().getLocation(animal.getRow(), animal.getColumn());
                IslandField.getInstance().removeAnimal(animal, location.getRow(), location.getColumn());
                animalsDiedByHungry++;
            }
        }
        latch.countDown();
    }
    public int getAnimalsDiedByHungry() {
        return animalsDiedByHungry;
    }
}
