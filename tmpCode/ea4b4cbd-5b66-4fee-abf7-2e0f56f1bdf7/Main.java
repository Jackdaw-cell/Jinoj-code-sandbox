

import java.util.LinkedList;
import java.util.Queue;

public class Main {
   static class Node{
        Node left;
        Node right;
        int val;

        Node() 
/*
** 二叉树节点
public class Node {
    Node left;
    Node right;
    int val;
    Node() {}
    Node(int val) {this.val = val;}
}
*/
public static void breadthFirstTraversal(Node root) {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();

                if (current != null) {
                    System.out.print(current.val + " ");

                    // Only add non-null children to the queue
                    if (current.left != null) {
                        queue.add(current.left);
                    }

                    if (current.right != null) {
                        queue.add(current.right);
                    }
                } else {
                    System.out.print("null ");
                }
            }
       }
}

        Node(int val) {
            this.val = val;
        }
    }

    //构建二叉树
    public static Node createBinaryTree(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        Node[] nodes = new Node[array.length];

        // Create nodes
        for (int i = 0; i < array.length; i++) {
            if (array[i] != -1) {
                nodes[i] = new Node(array[i]);
            }
        }
        // Link nodes
        for (int i = 0; i < array.length; i++) {
            if (nodes[i] != null) {
                int leftIndex = 2 * i + 1;
                int rightIndex = 2 * i + 2;

                if (leftIndex < array.length && array[leftIndex] != -1) {
                    nodes[i].left = nodes[leftIndex];
                }

                if (rightIndex < array.length && array[rightIndex] != -1) {
                    nodes[i].right = nodes[rightIndex];
                }
            }
        }

        return nodes[0];  // The root node is at index 0
    }
    //字符串数组转为整形数组
    public static int[] convertToIntArray(String[] stringArray) {
        int[] intArray = new int[stringArray.length];

        for (int i = 0; i < stringArray.length; i++) {
            try {
                intArray[i] = Integer.parseInt(stringArray[i]);
            } catch (NumberFormatException e) {
                // Handle the case where the string is not a valid integer
                // You can choose to ignore it, set a default value, or take other actions.
                // For example, intArray[i] = 0;
                System.err.println("Invalid integer: " + stringArray[i]);
            }
        }

        return intArray;
    }
    public static void main(String[] args) {
        int[] array = convertToIntArray(args);
        Node root = createBinaryTree(array);

        breadthFirstTraversal(root);

    }


{}
}