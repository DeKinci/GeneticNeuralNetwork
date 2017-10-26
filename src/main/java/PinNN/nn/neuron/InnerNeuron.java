package PinNN.nn.neuron;

import java.util.ArrayList;
import java.util.Random;

public class InnerNeuron extends Neuron {
    private ArrayList<Double> weights;

    public InnerNeuron(int prevLayerSize) {
        weights = new ArrayList<>(prevLayerSize);
        super.value = 0d;

        Random r = new Random();

        for (int i = 0; i < prevLayerSize; i++)
            weights.add(0.25 + 0.5 * r.nextDouble());
    }

    @Override
    public void update(ArrayList<Double> data) {
        double sum = 0;

        for (int i = 0; i < data.size(); i++)
            sum += data.get(i) * weights.get(i);

        updateValue(sum);
    }
}
