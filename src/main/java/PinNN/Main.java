package PinNN;

import PinNN.nn.NNFacade;

public class Main {
    public static void main(String... args) {
        int[] arch = {2, 2, 1};

        NNFacade network = new NNFacade(arch, 10000);
        network.doTheEvolution(10000);
    }
}
