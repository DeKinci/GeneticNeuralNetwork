package PinNN.nn.layer;

import PinNN.nn.neuron.InnerNeuron;
import PinNN.nn.neuron.Neuron;

import java.util.ArrayList;

public class InnerLayer extends Layer {
    public InnerLayer(int neuronsInLayer, int neuronsInPrevLayer) {
        super(neuronsInLayer);

        for (int i = 0; i < neuronsInLayer; i++)
            neurons.add(new InnerNeuron(neuronsInPrevLayer));
    }

    @Override
    public void compute(ArrayList<Double> prevLayerValues) {
        for (Neuron neuron : neurons)
            neuron.update(prevLayerValues);
    }
}
