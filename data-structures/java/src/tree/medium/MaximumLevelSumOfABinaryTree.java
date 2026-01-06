package tree.medium;

import tree.repr.BinaryTreeBuilder;
import tree.repr.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Given the root of a binary tree, the level of its root is 1, the level of its children
 * is 2 and so on.
 * Return the smallest level x such that the sum of all the values of nodes at level x is
 * maximal
 *
 * Example:
 *
 * Input: root = [1,7,0,7,-8,null,null]
 * Output: 2
 * Explanation:
 * Level 1 sum = 1.
 * Level 2 sum = 7 + 0 = 7.
 * Level 3 sum = 7 + -8 = -1.
 * So we return the level with the maximum sum which is level 2.
 *
 * @author Ripan Baidya
 * @date 07/01/26
 */
public class MaximumLevelSumOfABinaryTree {

    /**
     * We will do simple level order traversal using BFS to calculate the sum of each level
     * and update the answer level only if the current sum of the level is greater than the
     * max sum
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int maxLevelSum(TreeNode root) {
        // Calculate max sum of a level
        int maxSum = Integer.MIN_VALUE;
        // Current level & answer level
        int currLevel = 0, ansLevel = 1;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            int currSum = 0;
            // Update the current level
            currLevel ++;

            for (int i = 0; i < size; i++) {
                TreeNode currNode = queue.poll();
                // Update the current sum
                currSum += currNode.val;

                // Add the left & right children to the queue if they are not null
                if (currNode.left != null) {
                    queue.add(currNode.left);
                }
                if (currNode.right != null) {
                    queue.add(currNode.right);
                }
            }
            // Update the answer level only if the current sum of the level is greater than
            // the max sum
            if (currSum > maxSum) {
                maxSum = currSum;
                ansLevel = currLevel;
            }
        }

        return ansLevel;
    }

    // Main
    public static void main(String[] args) {
        var ref = new MaximumLevelSumOfABinaryTree();

        String data = "[1,7,0,7,-8,null,null]";
        TreeNode root = BinaryTreeBuilder.buildTree(data);

        int res = ref.maxLevelSum(root);
        System.out.println(res); // 2
    }
}
