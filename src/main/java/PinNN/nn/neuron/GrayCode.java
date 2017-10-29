package PinNN.nn.neuron;

class GrayCode {
    static int code(int number) {
        return number ^ (number >> 1);
    }

    static int decode(int code) {
        int bin = 0;

        while (code != 0) {
            bin ^= code;
            code >>= 1;
        }

        return bin;
    }
}
