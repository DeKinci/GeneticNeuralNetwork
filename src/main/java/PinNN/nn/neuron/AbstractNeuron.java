package PinNN.nn.neuron;

import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractNeuron implements Neuron {
    private final static double SIGMA_COEFFICIENT = 2;

    private static final double MUTATION_RATE = 0.1;

    private static final double[] PERIOD_EDGES = {0.0, 1.0};
    private static final double PERIOD_SIZE = PERIOD_EDGES[1] - PERIOD_EDGES[0];
    private static final int POWER_OF_2_AMOUNT_OF_CHUNKS = 8;
    private static final int AMOUNT_OF_CHUNKS = 1 << POWER_OF_2_AMOUNT_OF_CHUNKS;
    private static final double CHUNK_SIZE = PERIOD_SIZE / AMOUNT_OF_CHUNKS;

    private Random r = new Random();

    private ArrayList<Double> weights = new ArrayList<>();
    private double value;

    AbstractNeuron() {
    }

    AbstractNeuron(Neuron first, Neuron second) {
        setWeights(calculateGenes(first.getWeights(), second.getWeights()));
    }

    private ArrayList<Double> calculateGenes(ArrayList<Double> first, ArrayList<Double> second) {
        ArrayList<Double> result = new ArrayList<>();

        for (int i = 0; i < first.size(); i++)
            result.add(calculateGen(first.get(i), second.get(i)));

        return result;
    }

    @Override
    public void update(ArrayList<Double> data) {
        double sum = 0;

        for (int i = 0; i < data.size(); i++)
            sum += data.get(i) * weights.get(i);

        updateValue(sum);
    }

    private double calculateGen(double first, double second) {
        int firstChunk = GrayCode.code(toChunk(first));
        int secondChunk = GrayCode.code(toChunk(second));

        int result = GrayCode.decode(mutate(crossingOver(firstChunk, secondChunk)));
        return fromChunk(result);
    }

    private int crossingOver(int firstCode, int secondCode) {
        int threshold = r.nextInt(POWER_OF_2_AMOUNT_OF_CHUNKS);
        int lastBitsMask = (1 << threshold) - 1;
        int firstBitsMask = Integer.MAX_VALUE - lastBitsMask;

        return r.nextBoolean() ?
                (firstCode & firstBitsMask) +
                        (secondCode & lastBitsMask) :
                (firstCode & lastBitsMask) +
                        (secondCode & firstBitsMask);
    }

    private int mutate(int gen) {
        for (int i = 0; i < POWER_OF_2_AMOUNT_OF_CHUNKS; i++)
            if (r.nextDouble() < MUTATION_RATE)
                gen = ((gen >> i) & 1) == 1 ? gen | (1 << i) : gen & ~(1 << i); //reverses bit to mutate
        return gen;
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

    private int toChunk(double weight) {
        checkWeightRange(weight);
        return (int) Math.round(Math.ceil(weight * AMOUNT_OF_CHUNKS)) - 1;
    }

    private double fromChunk(int chunk) {
        return chunk * CHUNK_SIZE + 0.5 * CHUNK_SIZE;
    }

    private void checkWeightRange(double weight) {
        if (weight < PERIOD_EDGES[0] || weight > PERIOD_EDGES[1])
            throw new IllegalArgumentException();
    }

    private double sigma(double x) {
        return 1 / (1 + Math.exp(-SIGMA_COEFFICIENT * x));
    }

    private double sigmaDerivative(double x) {
        return sigma(x) * (1 - sigma(x));
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[ ");
        for (double w : weights)
            str.append(Integer.toBinaryString(GrayCode.code(toChunk(w)))).append(" ");
        str.append("] ");

        return str.toString();
    }
}
