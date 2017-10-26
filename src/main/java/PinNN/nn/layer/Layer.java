package PinNN.nn.layer;

import PinNN.nn.neuron.Neuron;

import java.util.ArrayList;

public abstract class Layer {
    ArrayList<Neuron> neurons;

    Layer(int neuronsInLayer) {
        neurons = new ArrayList<>(neuronsInLayer);
    }

    public ArrayList<Double> getLayerValues() {
        ArrayList<Double> valArrayList = new ArrayList<>(this.neurons.size());

        for (Neuron neuron : neurons)
            valArrayList.add(neuron.getValue());

        return valArrayList;
    }

    abstract public void compute(ArrayList<Double> input);
}
