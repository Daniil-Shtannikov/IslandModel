package com.example.islandmodule.simulation.startMenu;

import com.example.islandmodule.field.IslandField;
import com.example.islandmodule.simulation.IslandSimulation;

import java.util.Scanner;
public class Menu {
    private final Parameters parameters;
    public Menu(){
        parameters = new Parameters();
    }

    public void startSimulation(){
        Scanner in = new Scanner(System.in);

        System.out.println("--------------------");
        System.out.println("Хотите внести свои параметры перед началом симуляции?");
        System.out.println("Выберете режим:");
        System.out.println("1 - да, 0 - нет");
        int answerParameters = in.nextInt();

        if(answerParameters == 1){
            parameters.changeParameters();
        } else {
            IslandField.getInstance();

        }
    }
}
