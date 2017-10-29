package PinNN.nn.layer;

import PinNN.nn.neuron.AbstractNeuron;
import PinNN.nn.neuron.InnerNeuron;
import PinNN.nn.neuron.Neuron;

import java.util.ArrayList;

public class InnerLayer extends AbstractLayer {
    public InnerLayer(int neuronsInLayer, int neuronsInPrevLayer) {
        for (int i = 0; i < neuronsInLayer; i++)
            neurons.add(new InnerNeuron(neuronsInPrevLayer));
    }

    public InnerLayer(InnerLayer first, InnerLayer second) {
        super(first, second);
    }

    @Override
    protected AbstractNeuron createNeuron(Neuron first, Neuron second) {
        return new InnerNeuron(first, second);
    }

    @Override
    public void compute(ArrayList<Double> input) {
        for (int i = 0; i < neurons.size(); i++)
            neurons.get(i).update(input);
    }
}
