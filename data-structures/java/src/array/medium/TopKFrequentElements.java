package array.medium;

import java.util.*;

/*
 * Top K Frequent Elements
 * Given an integer array nums and an integer k, return the k most frequent elements. You may
 * return the answer in any order.
 *
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 *
 * Example 3:
 * Input: nums = [1,2,1,2,1,2,3,1,3,2], k = 2
 * Output: [1,2]
 *
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the
 * array's size.
 *
 * @author Ripan Baidya
 * @date 04/01/26
 */
public class TopKFrequentElements {
    /*
     * Brute Force
     * To find the k most frequent elements, we first need to know how often each number appears.
     * Once we count the frequencies, we can sort the unique numbers based on how many times they
     * occur.
     * After sorting, the numbers with the highest frequencies will naturally appear at the end
     * of the list. By taking the last k entries, we get the k most frequent elements.
     *
     * This approach is easy to reason about:
     * count the frequencies → sort by frequency → take the top k.
     *
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        // Build the frequency map
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // List to store the entry of frequency map in the form of array
        List<int[]> list = new ArrayList<>();
        // Add the entry of frequency map
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            // Store in the form of [frequency, number]
            list.add(new int[]{entry.getValue(), entry.getKey()});
        }
        // Sort the list in descending order of frequency, so that the most frequent element
        // appears at first
        list.sort((a, b) -> Integer.compare(b[0], a[0]));

        // Store the first k elements in the result array
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = list.get(i)[1];
        }

        return res;
    }


    /*
     * Optimal Solution
     *
     * After counting how often each number appears, we want to efficiently keep track of only the
     * k most frequent elements.
     * A min-heap is perfect for this because it always keeps the smallest element at the top.
     * By pushing (frequency, value) pairs into the heap and removing the smallest whenever the heap
     * grows beyond size k, we ensure that the heap always contains the top k most frequent elements.
     * In the end, the heap holds exactly the k values with the highest frequencies.
     *
     * Time Complexity: O(n log k)
     * Space Complexity: O(n + k)
     */
    public int[] topKFrequentOptimal(int[] nums, int k) {
        // Step 1: Build the frequency map
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Use a min heap to keep track of the top K elements
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
                (a, b) -> a.getValue() - b.getValue()  // Compare by frequency
        );

        // Step 3: Add each entry to the heap, and maintain size K
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            minHeap.add(entry);
            if (minHeap.size() > k) {
                // Remove the element with the lowest frequency
                minHeap.poll();
            }
        }

        // Step 4: Extract the elements from the heap
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            // Get the element (key) from the heap
            result[i] = minHeap.poll().getKey();
        }

        return result;
    }

    public static void main(String[] args) {
        var ref = new TopKFrequentElements();

        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;

        System.out.println(Arrays.toString(ref.topKFrequent(nums, k)));
        System.out.println(Arrays.toString(ref.topKFrequentOptimal(nums, k)));
    }
}
