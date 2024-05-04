package DataStructures;
class AVLNode {
    public String word;
    public SinglyLinkedList occurrences;
    public int height;
    public AVLNode left, right;

    AVLNode(String word) {
        this.word = word;
        this.height = 1;
        this.left = this.right = null;
        this.occurrences = new SinglyLinkedList();
    }
}

public class AVLTree {
    public AVLNode root;

    // Get height of node
    public int height(AVLNode node) {
        if (node == null)
            return 0;
        return node.height;
    }

    // Get balance factor of node
    public int getBalance(AVLNode node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    // Right rotate subtree rooted with y
    public AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Left rotate subtree rooted with x
    public AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Insert a key into the tree
    public void insert(String word, int Pos, String doc) {
        root = insertRecursive(root, word, Pos, doc);
    }
    // Recursive function to insert a key into the tree
    private AVLNode insertRecursive(AVLNode node, String word, int Pos, String doc) {
        // Perform normal BST insertion
        if (node == null){
            AVLNode nuevo = new AVLNode(word);
            nuevo.occurrences.add(Pos, doc);
            return nuevo;
        }
        else if (word.compareTo(node.word) < 0){
            node.left = insertRecursive(node.left, word, Pos, doc);
            node.left.occurrences.add(Pos, doc);
        }
        else if (word.compareTo(node.word) > 0){
            node.right = insertRecursive(node.right, word, Pos, doc);
            node.right.occurrences.add(Pos, doc);
        }
        else if (word.compareTo(node.word) == 0){
            node.occurrences.add(Pos, doc);
        }

        // Update height of this ancestor node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Get the balance factor of this ancestor node
        int balance = getBalance(node);

        // If node becomes unbalanced, perform rotations
        // Left Left Case
        if (balance > 1 && word.compareTo(node.left.word) < 0)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && word.compareTo(node.right.word) > 0)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && word.compareTo(node.left.word) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && word.compareTo(node.right.word) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }
    public void printTree() {
        printTreeInorder(root);
    }

    private void printTreeInorder(AVLNode node) {
        if (node != null) {
            printTreeInorder(node.left);
            System.out.println("Word: " + node.word + ", Occurrences: " + node.occurrences.getListAsString());
            printTreeInorder(node.right);
        }
    }
}