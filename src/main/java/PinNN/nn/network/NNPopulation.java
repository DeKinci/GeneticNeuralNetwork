package PinNN.nn.network;

import PinNN.nn.training.TrainingSet;
import PinNN.nn.training.TrainingSetGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class NNPopulation {
    private static long generation = 0;
    private ArrayList<GeneticNeuralNetwork> networks;
    private int size;

    public NNPopulation(int size, int[] architecture) {
        networks = new ArrayList<>();
        this.size = size;

        for (int i = 0; i < size; i++)
            networks.add(new GeneticNeuralNetwork(architecture));
    }

    public void evolve(TrainingSetGenerator trainingSetGenerator, double partSurvives) {
        go(trainingSetGenerator);
        killWeakest(partSurvives);
        breed();
    }

    public void killWeakest(double partSurvives) {
        if (!checkPart(partSurvives))
            throw new IllegalArgumentException();

        int loosers = (int) Math.round(networks.size() * (1 - partSurvives));
        if (networks.size() - loosers < 2 && networks.size() >= 2)
            loosers = networks.size() - 2;

        Collections.sort(networks, Collections.reverseOrder());

        printNetworks();
        networks = new ArrayList<>(networks.subList(loosers, networks.size()));
        printNetworks();
    }

    public void go(TrainingSetGenerator setGen) {
        double avg = 0, max = Double.MIN_VALUE, min = Double.MAX_VALUE;

        for (GeneticNeuralNetwork network : networks) {
            double fitness = 0d;

            for (int i = 0; i < 10; i++) {
                TrainingSet set = setGen.nextSet();
                ArrayList<Double> results = network.doTheThing(set.getArgument());

                for (int j = 0; j < results.size(); j++)
                    fitness += Math.abs(results.get(j) - set.getResult().get(j));
            }

            network.setFitness(fitness);

            avg += fitness;
            if (fitness > max)
                max = fitness;
            if (fitness < min)
                min = fitness;
        }

        avg /= networks.size();
        printFitnessInfo(avg, max, min);
    }

    public void breed() {
        Random r = new Random();
        while (networks.size() < size)
            networks.add(
                    new GeneticNeuralNetwork(
                            networks.get(r.nextInt(networks.size())),
                            networks.get(r.nextInt(networks.size()))));
    }

    public void breed(int size) {
        this.size = size;
        breed();
    }

    private void printFitnessInfo(double avg, double max, double min) {
        System.out.printf("Generation %d\t\tAvg %.5f\t   Min %.5f\t Max %.5f\n",
                generation++, avg, min, max);
    }

    private boolean checkPart(double part) {
        return part >= -0.0 && part <= 1.0;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<GeneticNeuralNetwork> getNetworks() {
        return networks;
    }

    private void printNetworks() {
//        ////
//        for (GeneticNeuralNetwork network : networks)
//            System.out.println(network);
//        System.out.println("----------***----------");
//        ////
    }
}
