package com.example.islandmodule.field;

import com.example.islandmodule.lifeform.LifeForm;
import com.example.islandmodule.lifeform.animal.Animal;
import com.example.islandmodule.lifeform.plant.Plant;
import java.util.*;
public class Location {
    private final int row;
    private final int column;
    private final List<Animal> animals;
    private final List<Plant> plants;

    public Location(int row, int column) {
        this.row = row;
        this.column = column;

        animals = new ArrayList<>();
        plants = new ArrayList<>();
    }

    public void addAnimal(Animal animal){
        animal.setRow(row);
        animal.setColumn(column);

        animals.add(animal);
    }
    public void addPlant(Plant plant){
        plant.setRow(row);
        plant.setColumn(column);

        plants.add(plant);
    }

    public void removeAnimal(Animal animal){
        animals.remove(animal);
    }
    public void removePlant(Plant plant){
        plants.remove(plant);
    }
    public List<LifeForm> getLifeForm(){
        List<LifeForm> lifeForms = new ArrayList<>();
        lifeForms.addAll(animals);
        lifeForms.addAll(plants);
        return lifeForms;
    }

    public List<Animal> getAnimals(){
        return animals;
    }
    public List<Plant> getPlants(){
        return plants;
    }

    public List<LifeForm> getLifeForms() {
        List<LifeForm> lifeForms = new ArrayList<>();
        lifeForms.addAll(animals);
        lifeForms.addAll(plants);
        return lifeForms;
    }

    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }

}
