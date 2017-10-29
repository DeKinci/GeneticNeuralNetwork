package PinNN.nn.neuron;

import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractNeuron implements Neuron {
    private final static double T = 1;
    private Random r = new Random();

    private ArrayList<Double> weights = new ArrayList<>();
    private double value;

    private static final double mutationRate = 0.01;
    private static final double mutationStrength = 0;

    private static final double[] chunk = {0.0, 1.0};
    private static final int amountOfChunks = 100;
    private static final double chunkSize = (chunk[1] - chunk[0]) / amountOfChunks;

    AbstractNeuron() {
    }

    AbstractNeuron(Neuron first, Neuron second) {
        setWeights(calculateGenes(first.getWeights(), second.getWeights()));
    }

    private ArrayList<Double> calculateGenes(ArrayList<Double> first, ArrayList<Double> second) {
        ArrayList<Double> result = crossingOver(first, second);
        mutate(result);
        return result;
    }

    @Override
    public void update(ArrayList<Double> data) {
        double sum = 0;

        for (int i = 0; i < data.size(); i++)
            sum += data.get(i) * weights.get(i);

        updateValue(sum);
    }

    private ArrayList<Double> crossingOver(ArrayList<Double> first, ArrayList<Double> second) {


        if (first.size() != second.size())
            throw new IllegalArgumentException();

        Random r = new Random();

        int threshold = r.nextInt(first.size());
        ArrayList<Double> result1 = new ArrayList<>(first.subList(0, threshold));
        ArrayList<Double> result2 = new ArrayList<>(second.subList(threshold, second.size()));

        if (r.nextInt(2) == 0) {
            result1.addAll(result2);
            return result1;
        } else {
            result2.addAll(result1);
            return result2;
        }
    }

    private void mutate(ArrayList<Double> list) {
        Random r = new Random();

        for (int i = 0; i < list.size(); i++)
            if (r.nextDouble() < mutationRate)
                list.set(i, r.nextDouble() * mutationStrength);
    }

    private int toChunk(double num) {
        return (int) Math.round(Math.ceil(num * amountOfChunks));
    }

    @Override
    public double getValue() {
        return value;
    }

    private void updateValue(double value) {
        this.value = sigma(value);
    }

    @Override
    public ArrayList<Double> getWeights() {
        return weights;
    }

    void addRandomWeight() {
        weights.add(0.25 + 0.5 * r.nextDouble());
    }

    @Override
    public void setWeights(ArrayList<Double> weights) {
        this.weights = weights;
    }

    private double sigma(double x) {
        return 1 / (1 + Math.exp(-T * x));
    }

    private double sigmaDerivative(double x) {
        return sigma(x) * (1 - sigma(x));
    }
}
