package PinNN.nn.layer;

import PinNN.nn.neuron.AbstractNeuron;
import PinNN.nn.neuron.InputNeuron;
import PinNN.nn.neuron.Neuron;

import java.util.ArrayList;

public class InputLayer extends AbstractLayer {
    public InputLayer(int neuronsInLayer) {
        for (int i = 0; i < neuronsInLayer; i++)
            neurons.add(new InputNeuron(0));
    }

    public InputLayer(InputLayer first, InputLayer second) {
        super(first, second);
    }

    @Override
    protected AbstractNeuron createNeuron(Neuron first, Neuron second) {
        return new InputNeuron(first, second);
    }

    @Override
    public void compute(ArrayList<Double> input) {
        if (input.size() != this.neurons.size())
            throw new IllegalArgumentException();

        for (int i = 0; i < input.size(); i++)
            this.neurons.set(i, new InputNeuron(input.get(i)));
    }
}
