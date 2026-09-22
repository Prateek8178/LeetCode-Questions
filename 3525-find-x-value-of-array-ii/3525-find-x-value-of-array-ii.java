class Solution {

    class Node {
        int product;
        int[] cnt;

        Node(int k) {
            cnt = new int[k];
        }
    }

    int n;
    int k;
    Node[] tree;

    Node merge(Node left, Node right) {
        Node res = new Node(k);

        res.product = (left.product * right.product) % k;

        for (int r = 0; r < k; r++) {
            res.cnt[r] += left.cnt[r];

            int newRemainder = (left.product * r) % k;
            res.cnt[newRemainder] += right.cnt[r];
        }

        return res;
    }

    Node makeNode(int value) {
        Node node = new Node(k);

        int rem = value % k;

        node.product = rem;
        node.cnt[rem] = 1;

        return node;
    }

    void build(int index, int left, int right, int[] nums) {

        if (left == right) {
            tree[index] = makeNode(nums[left]);
            return;
        }

        int mid = left + (right - left) / 2;

        build(index * 2, left, mid, nums);
        build(index * 2 + 1, mid + 1, right, nums);

        tree[index] = merge(tree[index * 2], tree[index * 2 + 1]);
    }

    void update(int index, int left, int right, int pos, int value) {

        if (left == right) {
            tree[index] = makeNode(value);
            return;
        }

        int mid = left + (right - left) / 2;

        if (pos <= mid) {
            update(index * 2, left, mid, pos, value);
        } else {
            update(index * 2 + 1, mid + 1, right, pos, value);
        }

        tree[index] = merge(tree[index * 2], tree[index * 2 + 1]);
    }

    Node query(int index, int left, int right, int ql, int qr) {

        if (ql <= left && right <= qr) {
            return tree[index];
        }

        int mid = left + (right - left) / 2;

        if (qr <= mid) {
            return query(index * 2, left, mid, ql, qr);
        }

        if (ql > mid) {
            return query(index * 2 + 1, mid + 1, right, ql, qr);
        }

        Node leftNode = query(index * 2, left, mid, ql, qr);
        Node rightNode = query(index * 2 + 1, mid + 1, right, ql, qr);

        return merge(leftNode, rightNode);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.n = nums.length;
        this.k = k;

        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, index, value);

            Node ans = query(1, 0, n - 1, start, n - 1);

            result[i] = ans.cnt[x];
        }

        return result;
    }
}