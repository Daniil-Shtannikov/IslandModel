package com.example.islandmodule.field;

import com.example.islandmodule.lifeform.animal.Animal;
import com.example.islandmodule.lifeform.plant.Plant;
import java.util.ArrayList;
import java.util.List;
public class IslandField {
    private Location[][] locations;
    private final int numRows = 10;
    private final int numColumns = 4;
    private static volatile IslandField instance;


    private IslandField(){
        public static IslandField getInstance(){
            if(instance == null){
                synchronized (IslandField.class){
                    if(instance == null){
                        instance= new IslandField();
                    }
                }
            }
            return instance;
        }
    }

    public void initializedLocations(int numRows, int numColumns){
        locations = new Location[numRows][numColumns];
        for (int i =0; i<locations.length; i++){
            for (int j=0;j<locations[i].length; j++){
                locations[i][j]= new Location(i, j);
            }
        }
    }

    public void initializeLocations() {
        locations = new Location[numRows][numColumns];
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                locations[i][j] = new Location(i, j);
            }
        }
    }

    public  synchronized Location getLocation(int row, int column){
        return locations[row][column];
    }
    public void addAnimal(Animal animal, int row, int column){
        Location location = getLocation(row,column);
        location.removeAnimal(animal);
    }
    public void addPlant(Plant plant, int row, int column){
        Location location = getLocation(row,column);
        location.removePlant(plant);
    }

    public synchronized List<Animal> getAllAnimals(){
        List<Animal> allAnimals = new ArrayList<>();
        for(Location[] row : locations){
            for (Location location : row){
                allAnimals.addAll(location.getAnimals());
            }
        }
        return allAnimals;
    }
    public synchronized List<Plant> getAllPlants(){
        List<Plant> allPlants = new ArrayList<>();
        for(Location[] row : locations){
            for (Location location : row){
                allPlants.addAll(location.getPlants());
            }
        }
        return allPlantss;
    }

    public int getNumRows() { return numRows; }
    public int getNumColumns() { return numColumns; }
}
