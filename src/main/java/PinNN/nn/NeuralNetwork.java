package PinNN.nn;

import PinNN.nn.layer.InnerLayer;
import PinNN.nn.layer.InputLayer;
import PinNN.nn.layer.Layer;

import java.util.ArrayList;

public class NeuralNetwork {
    private ArrayList<Layer> layers;

    public NeuralNetwork(int[][] networkArchitecture) {
        if (networkArchitecture.length < 1)
            throw new IllegalArgumentException("Not enough layers");

        layers = new ArrayList<>(networkArchitecture.length);
        layers.add(new InputLayer(networkArchitecture[0].length));

        for (int i = 1; i < networkArchitecture.length; i++)
            layers.add(new InnerLayer(networkArchitecture[i].length, networkArchitecture[i - 1].length));

    }

    public void setData(ArrayList<Double> inputData) {
        layers.get(0).compute(inputData);
    }

    public void compute() {
        for (int i = 1; i < layers.size(); i++)
            layers.get(i).compute(layers.get(i - 1).getLayerValues());
    }

    public ArrayList<Double> getResults() {
        return layers.get(layers.size() - 1).getLayerValues();
    }

    public double train(ArrayList<Double> data, ArrayList<Double> trainer) {
        setData(data);
        compute();

        ArrayList<Double> result = getResults();

        ArrayList<Double> error = new ArrayList<>(result.size());

        for (int i = 0; i < result.size(); i++)
            error.add(Math.abs(trainer.get(i) - result.get(i)));

        //TODO
        return 0.0;
    }
}
