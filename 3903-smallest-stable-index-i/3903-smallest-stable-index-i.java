class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] suffix = new int[n];

        int minimum = Integer.MAX_VALUE;
        for(int i=n-1; i>=0; i--){
            minimum  = Math.min(minimum, nums[i]);
            suffix[i]=minimum;
        }
        int max = 0;
        for(int i=0; i<n; i++){
            max = Math.max(max, nums[i]);
            int score  = max-suffix[i];
            if(score<=k){
                return i;
            }
        }
        return -1;
    }
}