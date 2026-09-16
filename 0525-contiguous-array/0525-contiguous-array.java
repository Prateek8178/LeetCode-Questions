class Solution {
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> set = new HashMap<>();
        set.put(0, -1);
        int sum=0;
        int maxLength=0;
        for(int i=0; i<n; i++){
            if(nums[i]==0){
                sum--;
            }
            else{
                sum++;
            }
            if(set.containsKey(sum)){
                int length = i-set.get(sum);
                maxLength = Math.max(maxLength, length);
            }
            else{
                set.put(sum, i);
            }

        }
        return maxLength;
        
    }
}