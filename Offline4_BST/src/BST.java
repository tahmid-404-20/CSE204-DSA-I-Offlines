class BST_Node<Key,T> {
    Key key;
    T element;
    BST_Node<Key,T> left;
    BST_Node<Key,T> right;

    public BST_Node(Key key, T element) {
        this.key = key;
        this.element = element;
        left = right = null;
    }

    public BST_Node(Key key) {
        this.key = key;
        this.element = null;
        left = right = null;
    }

    public BST_Node(Key key, T element, BST_Node<Key,T> left, BST_Node<Key,T> right) {
        this.key = key;
        this.element = element;
        this.left = left;
        this.right = right;
    }

    public void setLeft(BST_Node<Key,T> left) {
        this.left = left;
    }
    public void setRight(BST_Node<Key,T> right) {
        this.right = right;
    }

    // for printing
    public boolean isLeaf() {
        return (this.left == null) && (this.right == null);
    }
}

public class BST<Key extends Comparable,T> {
    private BST_Node<Key,T> root;
    private int count;

    //helpers
    private BST_Node<Key,T> inserthelp(BST_Node<Key,T> root, Key key, T element) {
        if (root == null) {
            return new BST_Node<>(key, element);
        }
        if (key.compareTo(root.key) <= 0) {
            root.setLeft(inserthelp(root.left, key, element));
        } else {
            root.setRight(inserthelp(root.right, key, element));
        }
        return root;
    }

    private BST_Node<Key,T> removehelp(BST_Node<Key,T> root, Key key) {
        if(root == null) {
            return null;
        } if(key.compareTo(root.key) > 0){
            root.setRight(removehelp(root.right,key));
        } else if(key.compareTo(root.key) < 0) {
            root.setLeft(removehelp(root.left,key));
        } else {
            if (root.left == null) {
                root = root.right;
            } else if (root.right == null) {
                root = root.left;
            } else { //Has two children
                BST_Node<Key,T> temp = getMax(root.left);
                root.key = temp.key;
                root.element = temp.element;
                root.setLeft(deleteMax(root.left));
            }
        }
        return root;
    }

    private BST_Node<Key,T> findhelp(BST_Node<Key,T> root, Key key) {
        if (root == null) {
            return null;            //Not found
        }
        if (key.compareTo(root.key) > 0) {
            return findhelp(root.right, key);
        } else if (key.compareTo(root.key) < 0) {
            return findhelp(root.left, key);
        } else {
            return root;
        }
    }

    private BST_Node<Key,T> getMax(BST_Node<Key,T> root) {
        if (root.right == null) {
            return root;
        } else {
            return getMax(root.right);
        }
    }

    private BST_Node<Key,T> deleteMax(BST_Node<Key,T> root) {
        if (root.right == null) {
            return root.left;
        } else {
            root.setRight(deleteMax(root.right));
            return root;
        }
    }

    /*private BST_Node<Key,T> getMin(BST_Node root) {
        if (root.left == null) {
            return root;
        } else {
            return getMin(root.left);
        }
    }

    private BST_Node<Key,T> deleteMin(BST_Node root) {
        if (root.left == null) {
            return root.right;
        } else {
            root.setLeft(deleteMin(root.left));
            return root;
        }
        // root = deleteMin()
    }*/

    //Traversals
    private void preOrder(BST_Node<Key,T> root) {
        if (root == null) {
            return;
        } else {
            System.out.print(root.key + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    private void inOrder(BST_Node<Key,T> root) {
        if (root == null) {
            return;
        } else {
            inOrder(root.left);
            System.out.print(root.key + " ");
            inOrder(root.right);
        }
    }

    private void postOrder(BST_Node<Key,T> root) {
        if (root == null) {
            return;
        } else {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.key + " ");
        }
    }

    //Printing
    private void printhelp(BST_Node<Key,T> root) {

        if (root != null) {
            System.out.print(root.key);

            if (!root.isLeaf()) {
                System.out.print("(");
                printhelp(root.left);
                System.out.print(")");

                System.out.print("(");
                printhelp(root.right);
                System.out.print(")");
            }
        }
    }

    // Member functions
    public BST() {
        root = null;
        count = 0;
    }

    public void insert(Key key) {
        this.root = inserthelp(this.root, key, null);
        count++;
        printhelp(this.root);
        System.out.println();
    }
    public void insert(Key key, T element) {
        root = inserthelp(root, key, element);
        count++;
        printhelp(root);
        System.out.println();
    }

    public void delete(Key key) {
        BST_Node<Key,T> temp = findhelp(root,key);

        if(temp == null) {
            System.out.println("Invalid Operation");
        } else {
            this.root = removehelp(this.root, key);
            count--;
            printhelp(this.root);
            System.out.println();
        }
    }

    public boolean find(Key key) {
        return findhelp(root, key) != null;
    }

    public void traversePreOrder() {
        preOrder(root);
        System.out.println();
    }

    public void traverseInOrder() {
        inOrder(root);
        System.out.println();
    }

    public void traversePostOrder() {
        postOrder(root);
        System.out.println();
    }
}
