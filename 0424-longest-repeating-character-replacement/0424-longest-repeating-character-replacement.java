class Solution {
    public int characterReplacement(String s, int k) {
        int l=0;
        int r = 0;
        int maxFreq = 0;
        int ans = 0;

        int[] hash = new int[26];
        while(r<s.length()){
            hash[s.charAt(r)-'A']++;
            maxFreq = Math.max(maxFreq, hash[s.charAt(r)-'A']);
            while((r-l+1)-maxFreq >k){
                hash[s.charAt(l)-'A']--;
                maxFreq = 0;
                for(int i=0; i<26; i++){
                    maxFreq = Math.max(maxFreq, hash[i]);
                }
                l++;
            }
            ans = Math.max(ans, r-l+1);
            r++;
        }
        return ans;
    }
}