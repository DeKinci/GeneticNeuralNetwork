package PinNN.nn.neuron;

public class InnerNeuron extends AbstractNeuron {
    public InnerNeuron(int prevLayerSize) {
        for (int i = 0; i < prevLayerSize; i++)
            addRandomWeight();
    }

    public InnerNeuron(Neuron first, Neuron second) {
        super(first, second);
    }
}
