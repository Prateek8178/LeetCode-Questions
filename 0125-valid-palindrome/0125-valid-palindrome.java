class Solution {
    public boolean isPalindrome(String s) {
        int n = s.length();
        String ans = "";
        for(int i=0; i<n; i++){
            if(Character.isUpperCase(s.charAt(i))){
                ans+=Character.toLowerCase(s.charAt(i));
            }
            else if(!Character.isLetterOrDigit(s.charAt(i))){
                continue;
            }
            else{
                ans+=s.charAt(i);
            }
        }
        int left=0;
        int right = ans.length()-1;
        while(left<=right){
            if(ans.charAt(left)!=ans.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
}