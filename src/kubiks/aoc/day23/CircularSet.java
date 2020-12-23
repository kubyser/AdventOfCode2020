package kubiks.aoc.day23;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CircularSet<T> {

    Map<T, Node<T>> map = new HashMap<>();
    Node<T> start = null;

    Node<T> getFirst() {
        if (map.size() == 0) {
            return null;
        }
        return start;
    }

    public Set<T> getValues() {
        return map.keySet();
    }

    public boolean contains(T element) {
        return map.containsKey(element);
    }

    public void add(T element) {
        if (map.size() == 0) {
            Node<T> node = new Node<>(element, null, null);
            node.previous = node;
            node.next = node;
            map.put(element, node);
            start = node;
        } else {
            Node<T> start = getFirst();
            addAfter(start.value, element);
        }
    }

    Node<T> get(T element) {
        return map.get(element);
    }

    public int getSize() {
        return map.size();
    }

    public T getNext(T element) {
        Node<T> node = get(element);
        if (node == null) {
            return null;
        }
        return node.next.value;
    }

    public T removeNext(T element) {
        T next = getNext(element);
        if (next == null) {
            return null;
        }
        return remove(next);
    }

    public T remove(T element) {
        if (map.containsKey(element)) {
            Node<T> node = map.get(element);
            node.previous.next = node.next;
            node.next.previous = node.previous;
            map.remove(element);
            if (node == start) {
                if (map.size() > 0) {
                    start = node.next;
                } else {
                    start = null;
                }
            }
            return node.value;
        }
        return null;
    }

    public void addAfter(T element, List<T> list) {
        Node<T> node = get(element);
        Node next = node.next;
        for (T e:list) {
            if (map.containsKey(e)) {
                remove(e);
            }
            Node<T> newNode = new Node<T>(e, node, null);
            node.next = newNode;
            node = newNode;
            map.put(e, newNode);
        }
        node.next = next;
        next.previous = node;
    }

    public Node<T> addAfter(T element, T newElement) {
        Node<T> node = get(element);
        Node next = node.next;
        if (map.containsKey(newElement)) {
            remove(newElement);
        }
        Node<T> newNode = new Node<T>(newElement, node, next);
        node.next = newNode;
        next.previous = newNode;
        map.put(newElement, newNode);
        return newNode;
    }

    public String toString() {
        if (map.size() == 0) {
            return "";
        }
        String s = "[";
        Node<T> node = start;
        do {
            s = s + node.value;
            node = node.next;
            if (node != start) {
                s = s + ", ";
            }
        } while (node != start);
        s = s + "]";
        return s;
    }

}
