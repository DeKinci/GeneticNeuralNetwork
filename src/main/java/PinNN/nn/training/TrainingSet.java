package PinNN.nn.training;

import java.util.ArrayList;

public class TrainingSet {
    private ArrayList<Double> argument;
    private ArrayList<Double> function;

    public TrainingSet(ArrayList<Double> argument, ArrayList<Double> result) {
        this.argument = argument;
        this.function = result;
    }

    public ArrayList<Double> getArgument() {
        return argument;
    }

    public ArrayList<Double> getResult() {
        return function;
    }
}
