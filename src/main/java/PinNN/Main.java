package PinNN;

import PinNN.nn.NeuralNetwork;

import java.util.ArrayList;

public class Main {
    public static void main(String... args) {
        int[][] arch = {
                {1},
                {1}
        };
        NeuralNetwork nn = new NeuralNetwork(arch);

        ArrayList<Double> data = new ArrayList<>();
        data.add(1.0);

        nn.setData(data);
        nn.compute();

        ArrayList<Double> results = nn.getResults();
        for (double res : results)
            System.out.print(res + " ");
    }
}
