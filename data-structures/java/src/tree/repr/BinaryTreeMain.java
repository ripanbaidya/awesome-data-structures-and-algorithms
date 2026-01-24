package tree.repr;

public class BinaryTreeMain {

    public static TreeNode buildTree() {
        /*
                    1
                  /   \
                 2     3
               /  \
              4    5
         */
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        return root;
    }

    public static Node buildBinaryTree() {
        /*
                    1
                  /   \
                 2     3
               /  \
              4    5
         */
        Node root = new Node(1);

        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        return root;
    }

    public static void main(String[] args) {
        /*
                    1
                  /   \
                 2     3
               /  \
              4    5
         */
        Node root = new Node(1);

        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);
    }
}