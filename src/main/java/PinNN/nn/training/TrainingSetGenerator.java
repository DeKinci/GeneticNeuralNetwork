package PinNN.nn.training;

import java.util.ArrayList;
import java.util.Random;

public class TrainingSetGenerator {
    private Random r = new Random();
    private double arg;

    public TrainingSet nextSet() {
        return new TrainingSet(generateArg(), generateResult());
    }

    private ArrayList<Double> generateArg() {
        arg = r.nextDouble() * 3.1415;

        ArrayList<Double> data = new ArrayList<>();
        data.add(arg);
        data.add(1d);
        return data;
    }

    private ArrayList<Double> generateResult() {
        ArrayList<Double> results = new ArrayList<>();
        results.add(Math.sin(arg));
        return results;
    }
}