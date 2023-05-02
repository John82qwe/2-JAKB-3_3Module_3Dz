public class MyTreeMap {
    // Добавление элемента, получение элемента по ключу, удаление элемента по ключу
    private int size = 0;
    private Node root = null;

    private class Node {
        public int key;
        public Node left = null;
        public Node right = null;
        public Node (int entryKey) {
            this.key = entryKey;
        }
    }

    public Integer put(int entryKey) {
        if (root == null) {
            root = new Node(entryKey);
            size++;
        }
        return putHelper(root,entryKey);
    }
    private Integer putHelper(Node node, int entryKey) {
        Comparable<? super Integer> k = (Comparable<? super Integer>)entryKey;
        int cmp = k.compareTo(node.key);
        if (cmp < 0) {
            if (node.left == null) {
                node.left = new Node(entryKey);
                size++;

            }
            return putHelper(node.left,entryKey);
        }
        if (cmp > 0) {
            if (node.right == null) {
                node.right = new Node(entryKey);
                size++;

            }
            return putHelper(node.right,entryKey);
        }
        int oldKey = node.key;
        node.key = entryKey;
        return oldKey;

    }
    public Integer get(Object key) {
        Node node = findNode(key);
        if (node == null) return null;
        return node.key;
    }
    private Node findNode(Object target) {
        Comparable<? super Integer> k = (Comparable<? super Integer>) target;
        Node node = root;
        while (node != null) {
            int cmp = k.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            }
            if (cmp > 0) {
                node = node.right;
            }
            if (cmp == 0) return node;
        }
        return null;
    }
    private Node findParent(Object target) {
        Comparable<? super Integer> k = (Comparable<? super Integer>) target;
        Node node = root;
        Node parent = root;
        while (node != null) {
            int cmp = k.compareTo(node.key);
            if (cmp < 0) {
                parent = node;
                node = node.left;
            }
            if (cmp > 0) {
                parent = node;
                node = node.right;
            }
            if (cmp == 0) return parent;
        }
        return null;
    }
    public Integer remove(Object key) {
        int oldValue = get(key);
        if (key == root) root = delRecursive(key);
        else delRecursive(key);
        System.out.println("Root = " + root.key);
        return oldValue;
    }
    private Node delRecursive(Object key) {
        Node node = findNode(key);
        Node parent = findParent(key);
        if (node.left == null && node.right == null) {
            if (node == parent.left) parent.left = null;
            if (node == parent.right) parent.right = null;
            size--;
            return parent;
        }
        if (node.right == null) {
            if (node == parent.left) parent.left = node.left;
            if (node == parent.right) parent.right = node.left;
            size--;
            return parent;
        }
        if (node.left == null) {
            if (node == parent.left) parent.left = node.right;
            if (node == parent.right) parent.right = node.right;
            size--;
            return parent;
        }
        Node tempNode = findSmallest(node.right);
        delRecursive(tempNode.key);
        node.key = tempNode.key;
        return parent;
    }
    private Node findSmallest(Node node) {
        if (node.left == null) return node;
        else {
            return findSmallest(node.left);
        }
    }
    public void printTree() {
        LER(root);
        System.out.println("__________");
    }
    private void LER(Node node) {
        if (node.left != null) LER(node.left);
        System.out.println(node.key);
        if (node.right != null) LER(node.right);
    }
}


