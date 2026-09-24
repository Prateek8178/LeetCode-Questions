class Solution {
    public int smallestIndex(int[] nums) {
        int n=nums.length;
        for(int i=0;i<n;i++){
            int ele=nums[i];
            int sum=0;
            while(ele!=0){
                sum=sum+ele%10;
                ele=ele/10;
            }
            if(sum==i){
                return i;
            }
        }
        return -1;
    }
}