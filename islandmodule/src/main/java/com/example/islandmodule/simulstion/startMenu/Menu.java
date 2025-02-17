package com.example.islandmodule.simulstion.startMenu;

import field.IslandField;
import simulation.IslandSimulation;

public class Menu {
    private final Parameters parameters;

    public Menu(){
        psrsmetrs = new Parameters();
    }

    public void startSimulation(){
        System.out.println("--------------------");
        System.out.println("Хотите внести свои параметры перед началом симуляции?");
        System.out.println("Выберете режим:");
        System.out.println("1 - да, 0 - нет");
        int answerParameters.takeInt(1,0);

        if(answerParameters == 1){
            parameters.changeParameters();
        } else {
            IslandField.getInstance

        }
    }
}
