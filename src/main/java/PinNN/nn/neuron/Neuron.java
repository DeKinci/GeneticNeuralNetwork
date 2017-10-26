package PinNN.nn.neuron;

import java.util.ArrayList;

public abstract class Neuron {
    private final static double T = 1;
    double value;

    Neuron(double data) {
        this.value = sigma(data);
    }

    Neuron() {
    }

    public double getValue() {
        return value;
    }

    void updateValue(double value) {
        this.value = sigma(value);
    }

    private double sigma(double x) {
        return 1 / (1 + Math.exp(-T * x));
    }

    private double sigmaDeriative (double x) {
        return sigma(x) * (1 - sigma(x));
    }

    abstract public void update(ArrayList<Double> data);
}
