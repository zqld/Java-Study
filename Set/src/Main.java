class MySet<T> {
    private Object[] items; // массив для хранения элементов множества
    private int count; // количество элементов в множестве

    public MySet() {
        this.items = new Object[10]; // создаем массив изначально на 10 элементов
        this.count = 0; // количество элементов изначально равно нулю
    }
    public boolean set(T item) {
        if (contains(item)) {
            return false;
        }
        if (count == items.length) {
            resize();
        }
        items[count++] = item;
        return true;
    }

    // добавляет элемент в множество, если его еще нет в множестве
    public void add(T item) {
        if (!set(item)) {
            throw new IllegalArgumentException("Item already exists in the set.");
        }
    }

    // добавляет элементы из переданного массива в множество
    public void addRange(T[] items) {
        for (T item : items) {
            set(item);
        }
    }
    private void resize(){
        Object[] newItems = new Object[items.length * 2];
        System.arraycopy(items, 0, newItems, 0, items.length);
        items = newItems;
    }

    // удаляет элемент из множества
    public boolean remove(T item) {
        for (int i = 0; i < count; i++) {
            if (items[i].equals(item)) {
                items[i] = items[count - 1];
                items[count - 1] = null;
                count--;
                return true;
            }
        }
        return false;
    }

    // проверяет, содержится ли элемент в множестве
    public boolean contains(T item) {
        for (int i = 0; i < count; i++) {
            if (items[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    // возвращает количество элементов в множестве
    public int count() {
        return count;
    }
}

public class Main {
    public static void main(String[] args) {
        MySet set = new MySet();
        set.add(1);
        set.add(2);
        // добавляем массив элементов в список
        set.addRange(new Integer[]{3, 4, 5});
        set.remove(2);
        System.out.println("Есть ли 1 в списке?\n" + set.contains(1)); // true
        System.out.println("Есть ли 2 в списке?\n" + set.contains(2)); // false
        System.out.println("Есть ли 3 в списке?\n" + set.contains(3)); // false
        System.out.println("Сколько элементов в списке?\n" + set.count());
    }
}