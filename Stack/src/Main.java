class Stack<T> {
    private Node<T> top;
    private int size;

    public Stack() {
        this.top = null;
        this.size = 0;
    }

    //push(T value) - добавляет элемент на вершину стека.
    public void push(T value) {
        Node<T> node = new Node<T>(value);
        node.next = top;
        top = node;
        size++;
    }

    //pop() - удаляет и возвращает элемент с вершины стека.
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T value = top.value;
        top = top.next;
        size--;
        return value;
    }

    //peek() - возвращает элемент с вершины стека, не удаляя его.
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.value;
    }

    //isEmpty() - возвращает true, если стек пуст, и false в противном случае.
    public boolean isEmpty() {
        return top == null;
    }

    //size() - возвращает количество элементов в стеке.
    public int size() {
        return size;
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
            this.next = null;
        }
    }
}

class check {
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(34);
        System.out.println("Stack size: " + stack.size());
        System.out.println("Top element: " + stack.peek());
        System.out.println("Popped element: " + stack.pop());
        System.out.println("Popped element: " + stack.pop());
        System.out.println("Stack size: " + stack.size());
        System.out.println("IsEmpty: " + stack.isEmpty());
    }
}
