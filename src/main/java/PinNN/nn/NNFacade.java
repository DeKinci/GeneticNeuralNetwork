package PinNN.nn;

import PinNN.nn.network.NNPopulation;
import PinNN.nn.training.TrainingSetGenerator;

import java.util.Random;

public class NNFacade {
    private NNPopulation recentPopulation;

    public NNFacade(int[] architecture, int size) {
        recentPopulation = new NNPopulation(size, architecture);
    }

    public void doTheEvolution(int iterations) {
        Random r = new Random();
        for (int i = 0; i < iterations; i++) {
            recentPopulation.evolve(new TrainingSetGenerator(),0.5);
        }
    }
}
