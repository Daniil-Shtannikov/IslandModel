package com.example.islandmodule.simulation.thread.animalLifecycleTask;

import com.example.islandmodule.simulation.thread.animalLifecycleTask.Task.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnimalLifecycleTask implements Runnable {
    private final AnimalEatTask animalEatTask;
    private final AnimalMultiplyTask animalMultiplyTask;
    private final AnimalHpDecreaseTask animalHpDecreaseTask;
    private final CountDownLatch latch;

    public AnimalLifecycleTask() {
        latch = new CountDownLatch(3);
        animalEatTask = new AnimalEatTask(latch);
        animalMultiplyTask = new AnimalMultiplyTask(latch);
        animalHpDecreaseTask = new AnimalHpDecreaseTask(latch);
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.submit(animalEatTask);
        executorService.submit(animalMultiplyTask);
        executorService.submit(animalHpDecreaseTask);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.submit(new AnimalMoveTask());
    }

    public AnimalMultiplyTask getObjectMultiplyTask() {
        return animalMultiplyTask;
    }

    public AnimalEatTask getAnimalEatTask() {
        return animalEatTask;
    }

    public AnimalHpDecreaseTask getAnimalHpDecreaseTask() {
        return animalHpDecreaseTask;
    }

}
