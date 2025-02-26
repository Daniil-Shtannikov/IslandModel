package com.example.islandmodule.simulation.thread.animalLifecycleTask.Task;

import android.os.Build;

import com.example.islandmodule.field.*;
import com.example.islandmodule.lifeform.*;
import com.example.islandmodule.lifeform.animal.Animal;
import com.example.islandmodule.lifeform.plant.Plant;
import com.example.islandmodule.simulation.IslandSimulation;
import com.example.islandmodule.simulation.thread.StatisticsTask;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class AnimalEatTask implements Runnable {
    private final CountDownLatch latch;
    private int animalsEaten;

    public AnimalEatTask(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run() {
        animalsEaten = 0;
        List<Animal> animals = IslandField.getInstance().getAllAnimals();
        List<LifeForm> lifeFormsEaten = new ArrayList<>();
        int countAnimalsStart = animals.size();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            if (countAnimalsStart > 0 && animals.stream().filter(c -> !(c.getName().equals("Caterpillar"))).toList().size() > 0) {
                Iterator<Animal> iterator = animals.iterator();

                while (iterator.hasNext()) {
                    Animal currentAnimal = iterator.next();
                    if (currentAnimal.getMaxHP() > 0) {
                        Location location = IslandField.getInstance().getLocation(currentAnimal.getRow(), currentAnimal.getColumn());
                        List<LifeForm> locationLifeForms = location.getLifeForms();

                        if (!locationLifeForms.isEmpty()) {
                            for (LifeForm lifeForm : locationLifeForms) {
                                if (currentAnimal.getChanceToEat(lifeForm.getName()) > 0 && !(lifeFormsEaten.contains(lifeForm))) {
                                    boolean isEaten = currentAnimal.eat(lifeForm);

                                    if (isEaten) {
                                        if (lifeForm.getClass().equals(Animal.class)) {
                                            Animal animalEaten = (Animal) lifeForm;
                                            if (location.getAnimals().contains(animalEaten)) {
                                                IslandField.getInstance().removeAnimal(animalEaten, location.getRow(), location.getColumn());
                                            }
                                            lifeFormsEaten.add(animalEaten);
                                            animalsEaten++;
                                        } else {
                                            Plant plant = (Plant) lifeForm;
                                            if (location.getPlants().size() > 0) {
                                                IslandField.getInstance().removePlant(plant, location.getRow(), location.getColumn());
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    iterator.remove();
                }
            } else if (countAnimalsStart == 0) {
                System.out.printf("ВЫ ПРОИГРАЛИ! ВСЕ ЖИВОТНЫЕ УМЕРЛИ НА %d ДЕНЬ!", StatisticsTask.getCurrentDay());
                IslandSimulation.getInstance().getExecutorService().shutdown();
                System.exit(0);
            } else {
                System.out.printf("ПОБЕДИЛИ ГУСЕНИЦЫ! В ЖИВЫХ ОСТАЛИСЬ ТОЛЬКО ОНИ НА %d ДЕНЬ!", StatisticsTask.getCurrentDay()); // так как они бесссмертные и не требуют пищи
                IslandSimulation.getInstance().getExecutorService().shutdown();
                System.exit(0);
            }
        }
        latch.countDown();
    }
    public int getAnimalsEaten() {
        return animalsEaten;
    }
}
