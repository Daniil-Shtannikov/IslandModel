package com.example.islandmodule.simulation;

import com.example.islandmodule.field.IslandField;
import com.example.islandmodule.field.Location;
import com.example.islandmodule.lifeform.animal.herbivore.*;
import com.example.islandmodule.lifeform.animal.predator.*;
import com.example.islandmodule.lifeform.plant.Plant;
import com.example.islandmodule.simulation.thread.PlantGrowthTask;
import com.example.islandmodule.simulation.thread.StatisticsTask;
import com.example.islandmodule.simulation.thread.animalLifecycleTask.AnimalLifecycleTask;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class IslandSimulation {
    private final long startTime;
    private final int countHerbivores = 35;
    private final int countPlants = 40;
    private final int countPredators = 20;
    private static volatile IslandSimulation instance;
    private volatile ScheduledExecutorService executorService;

    private IslandSimulation(){
        startTime = System.currentTimeMillis();
    }

    public static IslandSimulation getInstance(){
        if(instance == null){
            synchronized (IslandSimulation.class){
                if(instance == null){
                    instance = new IslandSimulation();
                }
            }
        }
        return instance;
    }

    public void createIslandModel(int countHerbivores, int countPredators, int countPlants){
        placeHerbivores(countHerbivores);
        placePredators(countPredators);
        placePlants(countPlants);

        runIslandModel();
    }

    private void runIslandModel(){
        executorService = Executors.newScheduledThreadPool(3);

        AnimalLifecycleTask animalLifecycleTask = new AnimalLifecycleTask();
        PlantGrowthTask plantGrowthTask = new PlantGrowthTask();
        StatisticsTask statisticsTask = new StatisticsTask();

        executorService.scheduleAtFixedRate(animalLifecycleTask, 1, 8, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(plantGrowthTask, 40, 30, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(statisticsTask, 0, 8, TimeUnit.SECONDS);
    }

    private List<Herbivore> createHerbivores(int countHerbivores){
        List<Herbivore> herbivores= new ArrayList<>();
        Random random = new Random();

        herbivores.add(new Buffalo());
        herbivores.add(new Caterpillar());
        herbivores.add(new Deer());
        herbivores.add(new Duck());
        herbivores.add(new Goat());
        herbivores.add(new Horse());
        herbivores.add(new Mouse());
        herbivores.add(new Rabbit());
        herbivores.add(new Sheep());
        herbivores.add(new WildBoar());

        int reminingCount = countHerbivores - herbivores.size();
        for (int i=0; i<reminingCount; i++)
        {
            int randomIndex = random.nextInt(herbivores.size());
            Herbivore randomHerbivore = herbivores.get(randomIndex);
            try{
                Herbivore newHerbivore = randomHerbivore.getClass().newInstance();
                herbivores.add(newHerbivore);
            } catch (InstantiationException | IllegalAccessException e){
                e.printStackTrace();
            }
        }
        return herbivores;
    }
    public void placeHerbivores(int countHerbivores){
        List<Herbivore> herbivores = createHerbivores(countHerbivores);
        Random random = ThreadLocalRandom.current();
        for (Herbivore herbivore : herbivores){
            boolean placed = false;
            while (!placed){
                int row = random.nextInt(IslandField.getInstance().getNumRows());
                int column = random.nextInt(IslandField.getInstance().getNumColumns());
                Location location = IslandField.getInstance().getLocation(row, column);
                if(location.getAnimals().stream().filter(c -> c.getName().eguals(herbivore.getName())).toList().size() <= herbivore.getMaxPopulation()){
                    IslandField.getInstance().addAnimal(herbivore, row, column);
                    placed = true;
                }
            }
        }
    }


    private List<Predator> createPredators(int countPredators){
        List<Predator> predators = new ArrayList<>();
        Random random = new Random();

        predators.add(new Bear());
        predators.add(new Eagle());
        predators.add(new Fox());
        predators.add(new Snake());
        predators.add(new Wolf());

        int reminingCount = countPredators - predators.size();
        for (int i=0; i<reminingCount; i++)
        {
            int randomIndex = random.nextInt(predators.size());
            Predator randomPredator = predators.get(randomIndex);
            try{
                Predator newPredator = randomPredator.getClass().newInstance();
                predators.add(newPredator);
            } catch (InstantiationException | IllegalAccessException e){
                e.printStackTrace();
            }
        }
        return predators;
    }
    public void placePredators(int countPredators){
        List<Predator> predators = createPredators(countPredators);
        Random random = ThreadLocalRandom.current();
        for (Predator predator : predators){
            boolean placed = false;
            while (!placed){
                int row = random.nextInt(IslandField.getInstance().getNumRows());
                int column = random.nextInt(IslandField.getInstance().getNumColumns());
                Location location = IslandField.getInstance().getLocation(row, column);
                if(location.getAnimals().stream().filter(c -> c.getName().eguals(predator.getName())).toList().size() <= predator.getMaxPopulation()){
                    IslandField.getInstance().addAnimal(predator, row, column);
                    placed = true;
                }
            }
        }
    }

    private List<Plant> createPlants(int countPlants){
        List<Plant> plants = new ArrayList<>();
        for(int i =0; i<countPlants; i++){
        plants.add(new Plant());
        }
        return plants;
    }
    public void placePlants(int countPlants){
        List<Plant> plants = createPlants(countPlants);
        Random random = ThreadLocalRandom.current();
        for (Plant plant : plants){
            boolean placed = false;
            while (!placed){
                int row = random.nextInt(IslandField.getInstance().getNumRows());
                int column = random.nextInt(IslandField.getInstance().getNumColumns());
                Location location = IslandField.getInstance().getLocation(row, column);
                if(location.getPlants().size() <=plant.getMaxPopulation()){
                    IslandField.getInstance().addPlant(plant, row, column);
                    placed = true;
                }
            }
        }
    }

    public long getTimeNow(){
        return (System.currentTimeMillis() - startTime) / 1000;
    }
    public ScheduledExecutorService getExecutorService(){
        return executorService;
    }
    public int getCountHerbivores(){
        return countHerbivores;
    }
    public int getCountPredators(){
        return countPredators;
    }
    public int getCountPlants(){
        return countPlants;
    }
}
