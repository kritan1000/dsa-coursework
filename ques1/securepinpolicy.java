
public class securepinpolicy {

    public static int strongPinChanges(String pinCode) {
        int n = pinCode.length();
        boolean hasLower = false, hasUpper = false, hasDigit = false;

        int repeatCount = 0;
        int[] repeats = new int[n];

        for (int i = 0; i < n; ) {
            char ch = pinCode.charAt(i);

            if (Character.isLowerCase(ch)) hasLower = true;
            if (Character.isUpperCase(ch)) hasUpper = true;
            if (Character.isDigit(ch)) hasDigit = true;

            int j = i;
            while (j < n && pinCode.charAt(j) == ch) j++;

            int len = j - i;
            if (len >= 3) {
                repeats[repeatCount++] = len;
            }
            i = j;
        }

        int missingTypes = 0;
        if (!hasLower) missingTypes++;
        if (!hasUpper) missingTypes++;
        if (!hasDigit) missingTypes++;

        if (n < 6) {
            return Math.max(missingTypes, 6 - n);
        }

        int replace = 0;
        for (int i = 0; i < repeatCount; i++) {
            replace += repeats[i] / 3;
        }

        if (n <= 20) {
            return Math.max(missingTypes, replace);
        }

        int deleteCount = n - 20;
        int over = deleteCount;

        for (int i = 0; i < repeatCount && over > 0; i++) {
            if (repeats[i] >= 3) {
                int reduce = Math.min(over, repeats[i] - 2);
                repeats[i] -= reduce;
                over -= reduce;
            }
        }

        replace = 0;
        for (int i = 0; i < repeatCount; i++) {
            if (repeats[i] >= 3) {
                replace += repeats[i] / 3;
            }
        }

        return deleteCount + Math.max(missingTypes, replace);
    }

    public static void main(String[] args) {
        System.out.println("Example 1:");
        System.out.println("Input: X1!");
        System.out.println("Output: " + strongPinChanges("X1!"));

        System.out.println("\nExample 2:");
        System.out.println("Input: 123456");
        System.out.println("Output: " + strongPinChanges("123456"));

        System.out.println("\nExample 3:");
        System.out.println("Input: Aa1234!");
        System.out.println("Output: " + strongPinChanges("Aa1234!"));
    }
}