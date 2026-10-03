class Solution {
    public int longestValidParentheses(String s) {
        int[] stack = new int[s.length()+1];
        int head = 0;
        stack[head++]=-1;
        int max =0;
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(ch == '('){
                stack[head++]=i;
            }
            else{
                head--;
                if(head ==0){
                    stack[head++]=i;
                
                }
                else{
                    max = Math.max(max, i-stack[head-1]);
                }
            }
            
        }
        return max;
    }
}