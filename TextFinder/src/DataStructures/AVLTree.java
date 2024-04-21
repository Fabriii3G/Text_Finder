package DataStructures;
class WordNode {
    String word;
    SinglyLinkedList occurrences;

    WordNode(String word) {
        this.word = word;
        this.occurrences = new SinglyLinkedList();
    }
}
class AVLNode {
    WordNode wordNode;
    int height;
    AVLNode left, right;

    AVLNode(WordNode wordNode) {
        this.wordNode = wordNode;
        this.height = 1;
        this.left = this.right = null;
    }
}

public class AVLTree {
    private AVLNode root;

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
    public void insert(String word, SinglyLinkedList occurrence) {
        root = insertRecursive(root, word, occurrence);
    }

    // Recursive function to insert a key into the tree
    private AVLNode insertRecursive(AVLNode node, String word, SinglyLinkedList occurrence) {
        // Perform normal BST insertion
        if (node == null)
            return new AVLNode(new WordNode(word));

        if (word.compareTo(node.wordNode.word) < 0)
            node.left = insertRecursive(node.left, word, occurrence);
        else if (word.compareTo(node.wordNode.word) > 0)
            node.right = insertRecursive(node.right, word, occurrence);

        // Update height of this ancestor node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Get the balance factor of this ancestor node
        int balance = getBalance(node);

        // If node becomes unbalanced, perform rotations
        // Left Left Case
        if (balance > 1 && word.compareTo(node.left.wordNode.word) < 0)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && word.compareTo(node.right.wordNode.word) > 0)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && word.compareTo(node.left.wordNode.word) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && word.compareTo(node.right.wordNode.word) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }
    public void printTree() {
        printTreeInorder(root);
    }

    // Método recursivo para imprimir el árbol AVL en orden inorder
    private void printTreeInorder(AVLNode node) {
        if (node != null) {
            printTreeInorder(node.left);
            System.out.println("Word: " + node.wordNode.word + ", Occurrences: " + node.wordNode.occurrences);
            printTreeInorder(node.right);
        }
    }
}