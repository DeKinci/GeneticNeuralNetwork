package PinNN.nn.layer;

import PinNN.nn.neuron.InputNeuron;

import java.util.ArrayList;

public class InputLayer extends Layer {
    public InputLayer(int neuronsInLayer) {
        super(neuronsInLayer);

        for (int i = 0; i < neuronsInLayer; i++)
            neurons.add(new InputNeuron(0));
    }

    @Override
    public void compute(ArrayList<Double> input) {
        if (input.size() != this.neurons.size())
            throw new IllegalArgumentException();

        for (int i = 0; i < input.size(); i++)
            this.neurons.set(i, new InputNeuron(input.get(i)));
    }
}
