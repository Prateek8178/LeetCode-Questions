class Solution {
    static final long MOD = 1000000007;

    public int numberOfSets(int n, int k) {
        int N = n + k - 1;
        int R = 2 * k;

        long[] fact = new long[N + 1];

        fact[0] = 1;

        for (int i = 1; i <= N; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        long numerator = fact[N];
        long denominator1 = fact[R];
        long denominator2 = fact[N - R];

        long inverse1 = power(denominator1, MOD - 2);
        long inverse2 = power(denominator2, MOD - 2);

        long answer = numerator;
        answer = answer * inverse1 % MOD;
        answer = answer * inverse2 % MOD;

        return (int) answer;
    }

    private long power(long a, long b) {
        long result = 1;

        while (b > 0) {
            if ((b & 1) == 1) {
                result = result * a % MOD;
            }

            a = a * a % MOD;
            b >>= 1;
        }

        return result;
    }
}