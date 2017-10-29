package PinNN.nn.neuron;

import java.util.ArrayList;

public class InputNeuron extends AbstractNeuron {
    public InputNeuron(double data) {
        addRandomWeight();

        ArrayList<Double> dataList = new ArrayList<>();
        dataList.add(data);

        update(dataList);
    }

    public InputNeuron(Neuron first, Neuron second) {
        super(first, second);
    }
}
