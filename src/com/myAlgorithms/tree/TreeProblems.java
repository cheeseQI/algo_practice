package com.myAlgorithms.tree;
import java.util.*;
public class TreeProblems {
}

class AllCombination {
    LinkedList<Integer> track;
    List<List<Integer>> ans;

    public List<List<Integer>> permute(int[] nums) {
        track = new LinkedList<>();
        ans = new LinkedList<>();
        traverse(nums);
        return ans;
    }

    void traverse(int[] nums) {
        if (track.size() == nums.length) {
            ans.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i ++) {
            if (track.contains(nums[i])) continue;
            track.add(nums[i]);
            traverse(nums);
            track.removeLast();
        }
    }
}


class DivideSubset {
    boolean[] used;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int i : nums) sum += i;
        if (sum % k != 0) return false;

        used = new boolean[nums.length];
        return traverse(0, k, 0, nums, sum / k);
    }

    public boolean traverse(int idx, int k, int bucket, int[] nums, int target) {
        if (k == 0) return true;
        if (bucket == target) return traverse(0, k - 1, 0, nums, target);

        for (int i = idx; i < nums.length; i ++) {
            if (used[i]) continue;
            if (bucket + nums[i] > target) continue;

            bucket += nums[i];
            used[i] = true;
            if (traverse(idx + 1, k, bucket, nums, target)) return true;
            used[i] = false;
            bucket -= nums[i];
        }
        return false;
    }
}


class NQuenn {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<List<String>>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        Set<Integer> columns = new HashSet<Integer>();
        Set<Integer> diagonals1 = new HashSet<Integer>();
        Set<Integer> diagonals2 = new HashSet<Integer>();
        backtrack(solutions, queens, n, 0, columns, diagonals1, diagonals2);
        return solutions;
    }

    public void backtrack(List<List<String>> solutions, int[] queens, int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) {
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }
                queens[row] = i;
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                backtrack(solutions, queens, n, row + 1, columns, diagonals1, diagonals2);
                queens[row] = -1;
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }
    }

    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}



class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
 }


class RecorverTree {
    /* 主函数 */
    TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    /*
       定义：前序遍历数组为 preorder[preStart..preEnd]，
       中序遍历数组为 inorder[inStart..inEnd]，
       构造这个二叉树并返回该二叉树的根节点
    */
    TreeNode build(int[] preorder, int preStart, int preEnd,
                   int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }

        // root 节点对应的值就是前序遍历数组的第一个元素
        int rootVal = preorder[preStart];
        // rootVal 在中序遍历数组中的索引
        int indexOfRoot = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                indexOfRoot = i;
                break;
            }
        }

        int leftSize = indexOfRoot - inStart;

        // 先构造出当前根节点
        TreeNode root = new TreeNode(rootVal);
        // 递归构造左右子树
        root.left = build(preorder, preStart + 1, preStart + leftSize, inorder, inStart, indexOfRoot - 1);

        root.right = build(preorder, preStart + leftSize + 1, preEnd, inorder, indexOfRoot + 1, inEnd);
        return root;
    }
}