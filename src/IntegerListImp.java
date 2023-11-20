import java.util.Arrays;

public class IntegerListImp implements IntegerList {
    private final Integer storage[];
    private int size;

    public IntegerListImp() {
        storage = new Integer[10];
    }

    public IntegerListImp(int initSize) {
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
        validateSize();
        validateItem(item);
        return storage[size++] = item;
    }

    @Override
    public Integer add(int index, Integer item) {
        validateSize();
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
        sortInsertion(storageCopy);
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

    private void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
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


}