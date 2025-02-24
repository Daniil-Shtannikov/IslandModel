package com.example.islandmodule.simulation.thread;

import com.example.islandmodule.simulation.IslandSimulation;
public class PlantGrowthTask implements Runnable {
    @Override
    public void run(){
        int countPlants=20;
        if (IslandSimulation.getInstance().getTimeNow() >=2){
            IslandSimulation.getInstance().placePlants(countPlants /2);
        } else{
            IslandSimulation.getInstance().placePlants(countPlants);
        }
    }
}
