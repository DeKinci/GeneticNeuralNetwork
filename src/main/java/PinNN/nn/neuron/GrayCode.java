package PinNN.nn.neuron;

public class GrayCode {
    private int code;

    public GrayCode(int number) {
        code = number ^ (number >> 1);
    }

    public GrayCode(boolean[] array) {
        for (boolean anArray : array)
            code = (code << 1) + (anArray ? 1 : 0);
    }

    public int decode() {
        int bin = 0;
        int gray = code;

        while (gray != 0) {
            bin ^= gray;
            gray >>= 1;
        }

        return bin;
    }

    public int getCode() {
        return code;
    }

    public boolean[] toBooleanArray() {
        String str = Integer.toBinaryString(code);
        boolean[] arr = new boolean[str.length()];

        for (int i = 0; i < str.length(); i++)
            arr[i] = str.charAt(i) == '1';

        return arr;
    }

    @Override
    public String toString() {
        return decode() + "";
    }
}
