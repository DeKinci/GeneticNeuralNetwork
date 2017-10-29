package PinNN.nn.network;

import PinNN.nn.layer.*;
import PinNN.nn.neuron.Neuron;

import java.util.ArrayList;
import java.util.Arrays;

public class GeneticNeuralNetwork implements Comparable {
    private ArrayList<AbstractLayer> layers;
    private int[] networkArchitecture;

    private double fitness = 0;

    GeneticNeuralNetwork(GeneticNeuralNetwork first, GeneticNeuralNetwork second) {
        this();
        if (!first.isSimilar(second))
            throw new IllegalArgumentException();
        this.networkArchitecture = first.getNetworkArchitecture();

        layers.add(new InputLayer(
                (InputLayer) first.getLayers().get(0),
                (InputLayer) second.getLayers().get(0)));

        for (int i = 1; i < first.getLayers().size(); i++)
            layers.add(
                    new InnerLayer(
                            (InnerLayer) first.getLayers().get(i),
                            (InnerLayer) second.getLayers().get(i)));
        checkArchitecture();
    }

    GeneticNeuralNetwork(int[] networkArchitecture) {
        this();
        this.networkArchitecture = networkArchitecture;

        if (networkArchitecture.length < 1)
            throw new IllegalArgumentException("Not enough layers");

        layers.add(new InputLayer(networkArchitecture[0]));
        for (int i = 1; i < networkArchitecture.length; i++)
            layers.add(new InnerLayer(
                    networkArchitecture[i],
                    networkArchitecture[i - 1]));
    }

    private GeneticNeuralNetwork() {
        layers = new ArrayList<>();
    }

    public ArrayList<Double> doTheThing(ArrayList<Double> inputData) {
        setData(inputData);
        compute();
        return getResults();
    }

    public void compute() {
        for (int i = 1; i < layers.size(); i++)
            layers.get(i).compute(layers.get(i - 1).getLayerValues());
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public void setData(ArrayList<Double> inputData) {
        layers.get(0).compute(inputData);
    }

    public ArrayList<AbstractLayer> getLayers() {
        return layers;
    }

    public ArrayList<Double> getResults() {
        return layers.get(layers.size() - 1).getLayerValues();
    }

    public int[] getNetworkArchitecture() {
        return networkArchitecture;
    }

    private void checkArchitecture() {
        if (layers.size() != networkArchitecture.length)
            throw new IllegalStateException();
    }

    public boolean isSimilar(GeneticNeuralNetwork otherNetwork) {
        return this.getClass().equals(otherNetwork.getClass()) &&
                Arrays.equals(networkArchitecture, otherNetwork.getNetworkArchitecture());
    }

    @Override
    public int compareTo(Object o) {
        return Double.compare(fitness, ((GeneticNeuralNetwork) o).getFitness());
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (AbstractLayer layer : layers) {
            str.append("{ ");
            for (Neuron neuron: layer.getNeurons()) {
                str.append(neuron);
            }
            str.append("} ");
        }

        return str.toString();
    }
}
