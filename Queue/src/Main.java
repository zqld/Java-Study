class Queue<T> {
    private int front; // индекс первого элемента в очереди
    private int rear; // индекс последнего элемента в очереди
    private int size; // текущий размер очереди
    private T[] elements; // массив для хранения элементов очереди

    // создаем пустую очередь
    public Queue(int capacity) {
        // создаем массив заданной вместимости и инициализируем переменные
        this.elements = (T[]) new Object[capacity];
        this.size = 0;
        this.front = 0;
        this.rear = -1;
    }

    // проверка пустая ли очередь
    public boolean isEmpty() {
        // очередь пуста, если ее размер равен 0
        return (this.size == 0);
    }

    // проверка заполнена ли очередь
    public boolean isFull() {
        // очередь полна, если ее размер равен вместимости массива
        return (this.size == this.elements.length);
    }

    // добавление элемента
    public void enqueue(T item) {
        if (isFull()) {
            // если очередь полна, генерируем исключение
            throw new RuntimeException("Queue is full.");
        }
        // иначе добавляем элемент в конец очереди
        this.rear = (this.rear + 1) % this.elements.length;
        this.elements[this.rear] = item;
        this.size++;
    }

    // удаление первого элемента
    public T dequeue() {
        if (isEmpty()) {
            // если очередь пуста, генерируем исключение
            throw new RuntimeException("Queue is empty.");
        }
        // иначе удаляем первый элемент из очереди и возвращаем его
        T item = this.elements[this.front];
        this.front = (this.front + 1) % this.elements.length;
        this.size--;
        return item;
    }

    // просмотр первого элемента
    public T peek() {
        if (isEmpty()) {
            // если очередь пуста, генерируем исключение
            throw new RuntimeException("Queue is empty.");
        }
        // иначе возвращаем первый элемент очереди без его удаления
        return this.elements[this.front];
    }

    public int count() {
        return this.size;
    }
}

// проверка функций
public class Main {
    public static void main(String[] args) {
        Queue queue = new Queue(5);
        System.out.println("Пустая ли очередь? " + queue.isEmpty());
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println("Заполнена ли очередь? " + queue.isFull());
        System.out.println("Удаленный первый элемент: " + queue.dequeue()); // Output: 1
        System.out.println("Первый элемент: " + queue.peek());
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        System.out.println("Заполнена ли очередь? " + queue.isFull());
        System.out.println("Размер очереди: " + queue.count());
    }
}