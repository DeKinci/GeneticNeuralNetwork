package PinNN.nn.layer;

import PinNN.nn.neuron.AbstractNeuron;
import PinNN.nn.neuron.Neuron;

import java.util.ArrayList;

public abstract class AbstractLayer {
    ArrayList<Neuron> neurons;

    AbstractLayer() {
        neurons = new ArrayList<>();
    }

    AbstractLayer(AbstractLayer first, AbstractLayer second) {
        this();
        first.checkSimilarity(second);

        for (int i = 0; i < first.getNeurons().size(); i++)
            neurons.add(createNeuron(
                    first.getNeurons().get(i),
                    second.getNeurons().get(i)));
    }

    protected abstract AbstractNeuron createNeuron(Neuron first, Neuron second);

    public ArrayList<Double> getLayerValues() {
        ArrayList<Double> valArrayList = new ArrayList<>(this.neurons.size());

        for (Neuron neuron : neurons)
            valArrayList.add(neuron.getValue());

        return valArrayList;
    }

    abstract public void compute(ArrayList<Double> input);

    public ArrayList<Neuron> getNeurons() {
        return neurons;
    }

    private void checkSimilarity(AbstractLayer other) {
        if (!isSimilar(other))
            throw new IllegalArgumentException();
    }

    private boolean isSimilar(AbstractLayer other) {
        return this.getClass().equals(other.getClass()) &&
                this.neurons.size() == other.getNeurons().size();
    }
}
