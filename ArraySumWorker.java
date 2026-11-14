class InvalidRangeException extends Exception {
    public InvalidRangeException(String message) {
        super(message);
    }
}

class SumTask extends Thread {
    private int[] arr;
    private int start, end, partialSum = 0;

    public SumTask(int[] arr, int start, int end) throws InvalidRangeException {
        if (start < 0 || end > arr.length || start >= end) {
            throw new InvalidRangeException("Invalid array bounds");
        }
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            partialSum += arr[i];
        }
    }

    public int getPartialSum() {
        return partialSum;
    }
}

public class ArraySumWorker {
    public static void main(String[] args) {
        int[] numbers = {10, 20, 30, 40};
        try {
            SumTask t1 = new SumTask(numbers, 0, 2);
            SumTask t2 = new SumTask(numbers, 2, 4);

            t1.start();
            t2.start();
            t1.join();
            t2.join();

            System.out.println("Total Sum: " + (t1.getPartialSum() + t2.getPartialSum()));
        } catch (InvalidRangeException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
