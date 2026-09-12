import java.util.*;

class Solution {

    class Interval {
        int l, r, w, idx;

        Interval(int l, int r, int w, int idx) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.idx = idx;
        }
    }

    List<Integer>[][] memo;
    long[][] score;
    Interval[] arr;
    int n;

    public int[] maximumWeight(List<List<Integer>> intervals) {

        n = intervals.size();
        arr = new Interval[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            );
        }
        Arrays.sort(arr, (a, b) -> {
            if (a.l != b.l) return Integer.compare(a.l, b.l);
            if (a.r != b.r) return Integer.compare(a.r, b.r);
            return Integer.compare(a.idx, b.idx);
        });

        score = new long[n + 1][5];
        memo = new ArrayList[n + 1][5];

        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                memo[i][k] = null;
            }
        }

        List<Integer> ans = solve(0, 4);
        int[] result = new int[ans.size()];

        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }

        Arrays.sort(result);

        return result;
    }

    List<Integer> solve(int i, int k) {
        if (i == n || k == 0) {
            return new ArrayList<>();
        }

        if (memo[i][k] != null) {
            return memo[i][k];
        }
        List<Integer> skip = solve(i + 1, k);
        int next = findNext(i);

        List<Integer> chooseRest = solve(next, k - 1);

        List<Integer> choose = new ArrayList<>();

        choose.add(arr[i].idx);
        choose.addAll(chooseRest);

        long chooseScore = arr[i].w + getScore(next, k - 1);
        long skipScore = getScore(i + 1, k);

        List<Integer> answer;

        if (chooseScore > skipScore) {
            answer = choose;
        } 
        else if (chooseScore < skipScore) {
            answer = skip;
        } 
        else {
            answer = lexicographicallySmaller(choose, skip);
        }

        memo[i][k] = answer;

        score[i][k] = Math.max(chooseScore, skipScore);

        return answer;
    }

    long getScore(int i, int k) {

        if (i == n || k == 0) {
            return 0;
        }

        if (memo[i][k] == null) {
            solve(i, k);
        }

        return score[i][k];
    }
    int findNext(int i) {

        int left = i + 1;
        int right = n;

        int end = arr[i].r;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr[mid].l > end) {
                right = mid;
            } 
            else {
                left = mid + 1;
            }
        }

        return left;
    }

    List<Integer> lexicographicallySmaller(
        List<Integer> a,
        List<Integer> b
    ) {

        Collections.sort(a);
        Collections.sort(b);

        int size = Math.min(a.size(), b.size());

        for (int i = 0; i < size; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i) ? a : b;
            }
        }
        return a.size() <= b.size() ? a : b;
    }
}