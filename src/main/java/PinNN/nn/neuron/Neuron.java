package PinNN.nn.neuron;

import java.util.ArrayList;

public interface Neuron {
    void update(ArrayList<Double> data);
    ArrayList<Double> getWeights();
    double getValue();
    void setWeights(ArrayList<Double> weights);
}
