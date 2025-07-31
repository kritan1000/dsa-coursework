package ques2;

public class WeatherAnomaly {

    /*
     * Problem Summary:
     * Given an array of daily temperature changes, count the number of continuous time periods (subarrays)
     * where the total temperature change is within the range [lowThreshold, highThreshold].
     * 
     * Approach:
     * 1. Use prefix sums to efficiently calculate subarray sums in O(1) time.
     * 2. Iterate over all possible subarrays defined by start and end indices.
     * 3. For each subarray, calculate its sum using prefix sums.
     * 4. If the sum is within the specified range, increment the count.
     * 
     * Complexity:
     * The brute-force approach runs in O(n^2) time, which is acceptable for moderate input sizes.
     * For large datasets, more advanced data structures or algorithms might be needed.
     */
    
    public static int countValidPeriods(int[] temperatureChanges, int lowThreshold, int highThreshold) {
        // Input validation
        if (temperatureChanges == null) {
            throw new IllegalArgumentException("Temperature changes array cannot be null");
        }
        
        if (lowThreshold > highThreshold) {
            throw new IllegalArgumentException("Low threshold cannot be greater than high threshold");
        }
        
        int n = temperatureChanges.length;
        if (n == 0) {
            return 0;
        }
        
        int count = 0;

        // Step 1: Compute prefix sums array
        // prefixSum[i] stores the sum of temperatureChanges[0] to temperatureChanges[i-1]
        long[] prefixSum = new long[n + 1]; // Using long to prevent overflow
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + temperatureChanges[i - 1];
        }

        // Step 2: Iterate over all possible subarrays
        for (int start = 0; start < n; start++) {
            for (int end = start; end < n; end++) {
                // Calculate subarray sum using prefix sums:
                // sum of elements from start to end = prefixSum[end+1] - prefixSum[start]
                long subarraySum = prefixSum[end + 1] - prefixSum[start];

                // Step 3: Check if sum lies within the anomaly range
                if (subarraySum >= lowThreshold && subarraySum <= highThreshold) {
                    count++;  // Valid anomaly period found
                }
            }
        }

        // Step 4: Return total count of valid periods
        return count;
    }

    // Main method to run example test cases and output results
    public static void main(String[] args) {
        // Test case 1
        int[] temperatureChanges1 = {3, -1, -4, 6, 2};
        int lowThreshold1 = 2, highThreshold1 = 5;
        System.out.println("Example 1 Output: " + countValidPeriods(temperatureChanges1, lowThreshold1, highThreshold1)); // Expected: 4

        // Test case 2
        int[] temperatureChanges2 = {-2, 3, 1, -5, 4};
        int lowThreshold2 = -1, highThreshold2 = 2;
        System.out.println("Example 2 Output: " + countValidPeriods(temperatureChanges2, lowThreshold2, highThreshold2)); // Expected: 4
        
        // Test edge cases
        try {
            System.out.println("Empty array test: " + countValidPeriods(new int[]{}, 1, 5)); // Should return 0
        } catch (Exception e) {
            System.out.println("Empty array test: " + e.getMessage());
        }
        
        try {
            System.out.println("Single element test: " + countValidPeriods(new int[]{3}, 2, 5)); // Should return 1
        } catch (Exception e) {
            System.out.println("Single element test: " + e.getMessage());
        }
    }
}

