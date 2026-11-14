class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int ans  = Integer.MAX_VALUE;
        
        for(int  i=0; i<n; i++){
            int sum = 0;
            for(int j=i; j<n; j++) {
                int length = j-i+1;
                if(length>r){
                    break;
                }
                sum+=nums.get(j);
                if(length>=l && sum>0){
                    ans= Math.min(ans, sum);
                }
            }
        }
        if(ans == Integer.MAX_VALUE){
            return -1;
        }
        else return ans;

    }
}