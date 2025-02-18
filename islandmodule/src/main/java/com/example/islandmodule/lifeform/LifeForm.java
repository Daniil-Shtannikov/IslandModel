package com.example.islandmodule.lifeform;

public class LifeForm {
    private final double Weight;
    private final int MaxPopulation;
    private final String Name;
    private int row;
    private int column;


    public LifeForm(double weight, int maxPopulation, String name) {
        Weight = weight;
        MaxPopulation = maxPopulation;
        Name = name;
    }
    public double getWeight() {
        return Weight;
    }
    public int getMaxPopulation() {
        return MaxPopulation;
    }
    public String getName() {
        return Name;
    }
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setColumn(int column) {
        this.column = column;
    }
}
