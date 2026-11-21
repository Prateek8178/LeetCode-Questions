class Solution {
    public int maxFrequencyElements(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        int count =0;

        for(int i=0; i<nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i],0)+1);
        }
        for(int key: map.keySet()){
            max= Math.max(map.get(key), max);
        }
        for(int key: map.keySet()){
            if(map.get(key)==max){
                count+=map.get(key);
            }
        }
        return count;
    }
}