# Searching Algorithms

### File: `LinearSearch.java`
```java
public class LinearSearch {

    static int linearSearch(int[] arr, int n, int target) {
        for (int i = 0; i < n; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
```

### File: `BinarySearch.java`
```java
public class BinarySearch {

    static int binarySearch(int[] arr, int n, int target) {
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
```

---

# Sorting Algorithms

### File: `BubbleSort.java`
```java
public class BubbleSort {

    static void bubbleSort(int[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
```

### File: `SelectionSort.java`
```java
public class SelectionSort {

    static void selectionSort(int[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }
}
```

### File: `InsertionSort.java`
```java
public class InsertionSort {

    static void insertionSort(int[] arr, int n) {
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }
}
```

### File: `MergeSort.java`
```java
public class MergeSort {

    static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        for (int i = 0; i < n1; i++)
            leftArr[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            rightArr[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }
}
```

### File: `QuickSort.java`
```java
public class QuickSort {

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
}
```

### File: `HeapSort.java`
```java
public class HeapSort {

    static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest])
            largest = left;

        if (right < n && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, n, largest);
        }
    }

    static void heapSort(int[] arr, int n) {
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }
}
```

### File: `CountingSort.java`
```java
public class CountingSort {

    static void countingSort(int[] arr, int n) {
        int maxVal = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > maxVal)
                maxVal = arr[i];

        int[] count = new int[maxVal + 1];

        for (int i = 0; i < n; i++)
            count[arr[i]]++;

        int idx = 0;
        for (int i = 0; i <= maxVal; i++) {
            while (count[i] > 0) {
                arr[idx] = i;
                idx++;
                count[i]--;
            }
        }
    }
}
```

### File: `RadixSort.java`
```java
public class RadixSort {

    static int getMax(int[] arr, int n) {
        int maxVal = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > maxVal)
                maxVal = arr[i];
        return maxVal;
    }

    static void countSortByDigit(int[] arr, int n, int exp) {
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        for (int i = 0; i < n; i++)
            arr[i] = output[i];
    }

    static void radixSort(int[] arr, int n) {
        int maxVal = getMax(arr, n);

        for (int exp = 1; maxVal / exp > 0; exp *= 10)
            countSortByDigit(arr, n, exp);
    }
}
```

### File: `BucketSort.java`
```java
import java.util.ArrayList;
import java.util.Collections;

public class BucketSort {

    static void bucketSort(float[] arr, int n) {
        ArrayList<Float>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++)
            buckets[i] = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int bucketIdx = (int) (n * arr[i]);
            buckets[bucketIdx].add(arr[i]);
        }

        for (int i = 0; i < n; i++)
            Collections.sort(buckets[i]);

        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (float val : buckets[i]) {
                arr[idx] = val;
                idx++;
            }
        }
    }
}
```
