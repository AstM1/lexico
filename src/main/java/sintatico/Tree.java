package sintatico;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pedrochagas
 * @param <T>
 */
public class Tree<T> {
    private Node<T> root;

    public Tree() {
        root = new Node<>();
        root.data = RegrasSintatico.EXPRESSAO;
        root.children = new ArrayList<>();
    }

    public static class Node<T> {
        private T data;
        private Node<T> parent;
        private List<Node<T>> children;
    }
}
