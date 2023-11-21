import java.util.Arrays;

public class FinalIntegerListImp implements IntegerList {
    private Integer storage[];
    private int size;

    public FinalIntegerListImp() {
        storage = new Integer[10];
    }

    public FinalIntegerListImp(int initSize) {
        storage = new Integer[initSize];
    }

    public void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemExeption();
        }
    }

    private void validateSize() {
        if (size == storage.length) {
            throw new StorageIsFullExeption();
        }
    }

    private void growIfNeeded() {
        if (size == storage.length) {
            grow();
        }
    }

    private void validateIndexIn(int index) {
        if (index < 0 || index >= size) {
            throw new InvalidIndexExeption();
        }
    }

    private void validateIndexOut(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexExeption();
        }
    }

    @Override
    public Integer add(Integer item) {
        growIfNeeded();
        validateItem(item);
        return storage[size++] = item;
    }

    @Override
    public Integer add(int index, Integer item) {
        growIfNeeded();
        validateItem(item);
        validateIndexOut(index);
        if (index == size) {
            return storage[size++] = item;
        }
        System.arraycopy(storage, index, storage, index + 1, size - index);
        size++;
        return storage[index] = item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndexIn(index);
        validateItem(item);
        return storage[index] = item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);

        int index = indexOf(item);

        if (index == -1) {
            throw new ElementNotFoundExeption();
        }
        if (index != size) {
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        }

        size--;
        return item;
    }

    @Override
    public Integer remove(int index) {
        validateIndexIn(index);
        Integer item = storage[index];
        if (index != size) {
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        }

        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] storageCopy = toArray();
        sort(storageCopy);
        return binerySearch(storageCopy, item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndexIn(index);
        return storage[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new NullItemExeption();
        }
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    private void sortInactivInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Integer temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private void sort(Integer[] arr) {
        quickSort(arr,0,arr.length-1);
    }

    private boolean binerySearch(Integer[] arr, Integer item) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == arr[mid]) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    private void grow() {
        storage = Arrays.copyOf(storage, size + size / 2);
    }

    private void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }

    }

    private int partition(Integer[] arr, int begin, int end) {
        Integer pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private void swapElements(Integer[] arr, int i1, int i2) {
        Integer temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }


}