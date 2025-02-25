public class PrimeGeneratorOld {
    public static int[] generatePrimes(int maxValue) {
        if (maxValue < 2) {
            return new int[0];
        }
        boolean[] isCrossed = new boolean[maxValue + 1];
        for (int i = 2; i < isCrossed.length; i++) {
            isCrossed[i] = false;
        }
        int maxPrimeFactor = (int) Math.sqrt(isCrossed.length) + 1;
        for (int i = 2; i <= maxPrimeFactor; i++) {
            if (!isCrossed[i]) {
                for (int multiple = 2 * i; multiple < isCrossed.length; multiple += i) {
                    isCrossed[multiple] = true;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < isCrossed.length; i++) {
            if (!isCrossed[i]) {
                count++;
            }
        }
        int[] result = new int[count];
        for (int i = 2, j = 0; i < isCrossed.length; i++) {
            if (!isCrossed[i]) {
                result[j++] = i;
            }
        }
        return result;
    }
}