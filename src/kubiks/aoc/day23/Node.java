package kubiks.aoc.day23;

public class Node<T> {
    Node<T> next;
    Node<T> previous;
    T value;

    Node(T value, Node<T> previous, Node<T> next) {
        this.value = value;
        this.previous = previous;
        this.next = next;
    }

    public T getValue() {
        return value;
    }
}
