class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] dp = new int[n];

        Arrays.fill(dp, Integer.MAX_VALUE);

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int prefix = 0;
        int answer = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++) {
            prefix += arr[i];

            int need = prefix - target;

            if(map.containsKey(need)) {
                int start = map.get(need) + 1;
                int length = i - start + 1;

                if(start > 0 && dp[start - 1] != Integer.MAX_VALUE) {
                    answer = Math.min(answer, length + dp[start - 1]);
                }

                dp[i] = Math.min(dp[i], length);
            }

            if(i > 0) {
                dp[i] = Math.min(dp[i], dp[i - 1]);
            }

            map.put(prefix, i);
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}