class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        int[] ans = new int[k];

        for(int i=0; i<k; i++){
            int max = 0;
            int maxnum=0;
            
        for(int num:map.keySet()){
            if(map.get(num)>max){
                max= map.get(num);
                maxnum=num;
            }
        }
        ans[i]=maxnum;
        map.remove(maxnum);
        }
        return ans;
    }
}