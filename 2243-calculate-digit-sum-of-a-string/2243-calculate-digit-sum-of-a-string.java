
class Solution {
    public String digitSum(String s, int k) {
        while (s.length() > k) {
            String ans = "";
            int i = 0;
            while (i < s.length()) {
                int sum = 0;
                int j = i;
                while (j < i + k && j < s.length()) {
                    sum = sum + (s.charAt(j) - '0');
                    j++;
                }
                ans = ans + sum;
                i = i + k;
            }
            s = ans;
        }
        return s;
    }
}
