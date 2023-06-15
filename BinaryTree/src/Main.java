import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

class BinaryTree<T extends Comparable<T>> implements Iterable<T> {
    private Node<T> root;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public BinaryTree() {
        this.root = null;
        this.size = 0;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (root == null) {
            root = newNode;
        } else {
            addNode(root, newNode);
        }
        size++;
    }

    private void addNode(Node<T> currentNode, Node<T> newNode) {
        if (newNode.data.compareTo(currentNode.data) < 0) {
            if (currentNode.left == null) {
                currentNode.left = newNode;
            } else {
                addNode(currentNode.left, newNode);
            }
        } else {
            if (currentNode.right == null) {
                currentNode.right = newNode;
            } else {
                addNode(currentNode.right, newNode);
            }
        }
    }

    public boolean remove(T data) {
        if (root == null) return false;

        boolean result = false;
        Node<T> currentNode = root;
        Node<T> parentNode = root;
        boolean isLeftChild = true;

        while (currentNode != null) {
            if (data.compareTo(currentNode.data) < 0) {
                parentNode = currentNode;
                currentNode = currentNode.left;
                isLeftChild = true;
            } else if (data.compareTo(currentNode.data) > 0) {
                parentNode = currentNode;
                currentNode = currentNode.right;
                isLeftChild = false;
            } else {
                result = true;
                size--;
                if (currentNode.left == null && currentNode.right == null) {
                    if (currentNode == root) {
                        root = null;
                    } else if (isLeftChild) {
                        parentNode.left = null;
                    } else {
                        parentNode.right = null;
                    }
                } else if (currentNode.right == null) {
                    if (currentNode == root) {
                        root = currentNode.left;
                    } else if (isLeftChild) {
                        parentNode.left = currentNode.left;
                    } else {
                        parentNode.right = currentNode.left;
                    }
                } else if (currentNode.left == null) {
                    if (currentNode == root) {
                        root = currentNode.right;
                    } else if (isLeftChild) {
                        parentNode.left = currentNode.right;
                    } else {
                        parentNode.right = currentNode.right;
                    }
                } else {
                    Node<T> successor = getSuccessor(currentNode);
                    if (currentNode == root) {
                        root = successor;
                    } else if (isLeftChild) {
                        parentNode.left = successor;
                    } else {
                        parentNode.right = successor;
                    }
                    successor.left = currentNode.left;
                }
                break;
            }
        }
        return result;
    }

    private Node<T> getSuccessor(Node<T> deleteNode) {
        Node<T> successor = null;
        Node<T> successorParent = null;
        Node<T> currentNode = deleteNode.right;

        while (currentNode != null) {
            successorParent = successor;
            successor = currentNode;
            currentNode = currentNode.left;
        }

        if (successor != deleteNode.right) {
            successorParent.left = successor.right;
            successor.right = deleteNode.right;
        }

        return successor;
    }

    public boolean contains(T data) {
        return findNode(data) != null;
    }

    private Node<T> findNode(T data) {
        Node<T> currentNode = root;
        while (currentNode != null) {
            if (data.compareTo(currentNode.data) < 0) {
                currentNode = currentNode.left;
            } else if (data.compareTo(currentNode.data) > 0) {
                currentNode = currentNode.right;
            } else {
                return currentNode;
            }
        }
        return null;
    }

    public int count() {
        return size;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    private class BinaryTreeIterator implements Iterator<T> {
        private Stack<Node<T>> stack;

        public BinaryTreeIterator() {
            stack = new Stack<>();
            if (root != null) {
                stack.push(root);
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the tree");
            }
            Node<T> node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            return node.data;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported.");
        }
    }

    public void preorder() {
        preorderTraversal(root);
    }

    private void preorderTraversal(Node<T> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorderTraversal(node.left);
            preorderTraversal(node.right);
        }
    }

    public void postorder() {
        postorderTraversal(root);
    }

    private void postorderTraversal(Node<T> node) {
        if (node != null) {
            postorderTraversal(node.left);
            postorderTraversal(node.right);
            System.out.print(node.data + " ");
        }
    }

    public void inorder() {
        inorderTraversal(root);
    }

    private void inorderTraversal(Node<T> node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.print(node.data + " ");
            inorderTraversal(node.right);
        }
    }
}


public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.add(5);
        tree.add(2);
        tree.add(7);
        tree.add(1);
        tree.add(6);
        tree.add(9);

        System.out.println(tree.contains(7)); // true
        System.out.println(tree.contains(8)); // false
        System.out.println(tree.count()); // 6

        tree.remove(7);

        tree.preorder(); // 5 2 1 9 6
        System.out.println();

        tree.postorder(); // 1 2 6 9 5
        System.out.println();

        tree.inorder(); // 1 2 5 6 9
        System.out.println();

    }
}