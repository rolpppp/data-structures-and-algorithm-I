// Note that duplicate characters are outputted as one.

public class BST {
    Node root;

    BST() {
        root = null;
    }

    private boolean precedes(char a, char b) {                                              // Custom precedence check
        String precedence = "ABCDEFGHIJKLMNOPQRSTUVWXYZ+-*/0123456789";
        return precedence.indexOf(a) < precedence.indexOf(b);
    }

    void insert(char key) {                                                                 // Inserts a character into the BST                                                          
        root = insertRec(root, key);
    }

    Node insertRec(Node root, char key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (precedes(key, root.data))
            root.left = insertRec(root.left, key);
        else if (precedes(root.data, key))
            root.right = insertRec(root.right, key);

        return root;
    }

    void inorder() {
        inorderRec(root);                                                                       // In-order traversal
    }

    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.data + " ");
            inorderRec(root.right);
        }
    }
}
