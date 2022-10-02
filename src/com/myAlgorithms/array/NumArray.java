package com.myAlgorithms.array;

public class NumArray {
    class TreeNode {
        int sum;
        int start, end;
        TreeNode leftNode, rightNode;
        public TreeNode(int sum, int start, int end) {
            this.sum = sum;
            this.start = start;
            this.end = end;
        }
    }

    public TreeNode root;
    public NumArray(int[] nums) {
        root = buildTree(nums, 0, nums.length - 1);
    }

    public void update(int index, int val) {
        update(index, val, root);
    }

    public void update(int index, int val, TreeNode root) {
        int mid = root.start + (root.end - root.start) / 2;
        if (index == root.start && index == root.end) {
            root.sum = val;
            return;
        }
        if (index <= mid) {
            update(index, val, root.leftNode);
        } else {
            update(index, val, root.rightNode);
        }

        root.sum = root.leftNode.sum + root.rightNode.sum;
    }

    public int sumRange(int left, int right) {
        return sumRange(left, right, root);
    }

    public int sumRange(int left, int right, TreeNode root) {
        if (left == root.start && right == root.end) { // or start == end
            return root.sum;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (left > mid) {
            return sumRange(left, right, root.rightNode);
        } else if (right <= mid) {
            return sumRange(left, right, root.leftNode);
        } else {
            return sumRange(left, mid, root.leftNode) + sumRange(mid + 1, right, root.rightNode);
        }
    }

    public TreeNode buildTree(int[] nums, int start, int end) {
        if (start == end) {
            return new TreeNode(nums[start], start, start);
        }
        int mid = start + (end - start) / 2;
        TreeNode l = buildTree(nums, start, mid);
        TreeNode r = buildTree(nums, mid + 1, end);
        TreeNode curr = new TreeNode(l.sum + r.sum, start, end);
        curr.leftNode = l;
        curr.rightNode = r;
        return curr;
    }

    public void printTree(TreeNode curr) {
        if (curr == null) return;
        System.out.println(curr.sum);
        printTree(curr.leftNode);
        printTree(curr.rightNode);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */