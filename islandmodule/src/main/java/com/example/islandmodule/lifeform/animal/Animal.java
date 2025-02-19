package com.example.islandmodule.lifeform.animal;

import com.example.islandmodule.error.ObjectNotLifeFormException;
import com.example.islandmodule.field.IslandField;
import com.example.islandmodule.field.Location;
import com.example.islandmodule.lifeform.LifeForm;
import com.example.islandmodule.lifeform.plant.Plant;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
public abstract class Animal extends LifeForm {
    private final int Step;
    private final double MaxHP;
    private double Hp;

    public Animal(double weight, int step, double maxHP, int MaxPopulation, String name){
        super(weight, MaxPopulation, name);
        Step = step;
        MaxHP = maxHP;
        Hp = maxHP;
    }

    public boolean eat(Object food){
        double chanceToEat;
        LifeForm lifeForm = null;
        boolean animalEatFood;

        if (food instanceof LifeForm){
            lifeForm = (LifeForm) food;
        } else {
            try {
                throw new ObjectNotLifeFormException("Объект не является формой жизни");
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        String foodName = lifeForm.getName();
        chanceToEat = getChanceToEat(foodName);

        animalEatFood = ThreadLocalRandom.current().nextDouble() < chanceToEat;
        if (animalEatFood){
            setHp(Math.min((getHp() + lifeForm.getWeight()), getMaxHP()));
            Location location = IslandField.getInstance().getLocation(lifeForm.getRow(), lifeForm.getColumn());
            if (lifeForm.getClass().equals(Animal.class)){
                Animal animal = (Animal) lifeForm;
                if(location.getAnimals().contains(animal)){
                    IslandField.getInstance().removeAnimal(animal, location.getRow(), location.getColumn());
                } else {
                    return false;
                }
            } else {
                Plant plant = (Plant) lifeForm;
                if (location.getPlants().size() > 0){
                    IslandField.getInstance().removePlant(plant, location.getRow(), location.getColumn());
                } else {
                    return false;
                }
            }
        }
        return animalEatFood;
    }

    public void move(){
        Random random = new Random();
        int randomCells = random.nextInt(getStep()) + 1;

        int direction = random.nextInt(4);
        int newRow = getRow();
        int newColumn = getColumn();
        switch(direction) {
            case 0:
                    newRow -= randomCells;
            case 1:
                    newRow += randomCells;
            case 2:
                    newColumn -= randomCells;
            case 3:
                    newColumn += randomCells;
        }

        while (newRow < 0 || newRow >= IslandField.getInstance().getNumRows() || newColumn < 0 || newColumn >= IslandField.getInstance().getNumColumns()) {
            randomCells = random.nextInt(getStep()) + 1;
            direction = random.nextInt(4);

            newRow = getRow();
            newColumn = getColumn();
            switch (direction) {
                case 0:
                        newRow -= randomCells;
                case 1:
                        newRow += randomCells;
                case 2:
                        newColumn -= randomCells;
                case 3:
                        newColumn += randomCells;
            }
        }
        IslandField.getInstance().removeAnimal(this, getRow(), getColumn());
        setRow(newRow);
        setColumn(newColumn);
        IslandField.getInstance().addAnimal(this, newRow, newColumn);
    }
    public int getStep(){
        return Step;
    }
    public double getHp(){
        return Hp;
    }
    public double getMaxHP(){
        return MaxHP;
    }
    public void setHp(double hp){
        Hp = hp;
    }

    public abstract double getChanceToEat(String foodName);
    public abstract void multyply(Animal partner);
}
