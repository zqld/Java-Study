class Node<T> {
    T value;
    Node<T> next;

    public Node(T value) {
        this.value = value;
        this.next = null;
    }
}

class MyList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public MyList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    public boolean remove(T value) {
        if (head == null) {
            return false;
        }
        if (head.value.equals(value)) {
            if (head == tail) {
                tail = null;
            }
            head = head.next;
            size--;
            return true;
        }
        Node<T> current = head;
        while (current.next != null) {
            if (current.next.value.equals(value)) {
                if (current.next == tail) {
                    tail = current;
                }
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean contains(T value) {
        Node<T> current = head;
        while (current != null) {
            if (current.value.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public void copyTo(T[] array, int index) {
        Node<T> current = head;
        while (current != null) {
            array[index++] = current.value;
            current = current.next;
        }
    }

    public boolean isReadOnly() {
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        MyList list = new MyList();

        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println("Хранится ли 2 в списке?\n" + list.contains(2)); // output: true

        list.remove(2);

        System.out.println("Хранится ли 2 в списке после удаления?\n" + list.contains(2)); // output: false

        Integer[] array = new Integer[2];

        list.copyTo(array, 0);

        System.out.println("Элементы массива array: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " "); // output: 1 3
        }

        System.out.println("\nРазмер списка:\n" + list.size());
        list.clear();
        System.out.println("Размер списка после очистки:\n" + list.size());
    }
}