/*
Given a non-empty string str and an integer k, rearrange the string such that the 
same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to 
rearrange the string, return an empty string "".

Example 1:
str = "aabbcc", k = 3

Result: "abcabc"

The same letters are at least distance 3 from each other.
Example 2:
str = "aaabc", k = 3 

Answer: ""

It is not possible to rearrange the string.
Example 3:
str = "aaadbbcc", k = 2

Answer: "abacabcd"

Another possible answer is: "abcabcda"

The same letters are at least distance 2 from each other.
*/

import java.util.*;
public class RearrangeStringKDistanceApart {

	private static boolean dfs(List<Map.Entry<Character, Integer>> charCountsList, int total,
		Map<Character, Integer> excluded, int k, StringBuilder sb) {
		if (sb.length() == total) {
			return true;
		}
		for (Map.Entry<Character, Integer> charCount : charCountsList) {
			char c = charCount.getKey();
			int count = charCount.getValue();
			if (count == 0) {
				continue;
			}
			Integer prevId = excluded.get(c);
			if (prevId == null || sb.length() - prevId >= k) {
				charCount.setValue(--count);
				excluded.put(c, sb.length());
				sb.append(c);
				if (dfs(charCountsList, total, excluded, k, sb)) {
					return true;
				}
				charCount.setValue(++count);
				excluded.put(c, prevId);
				sb.setLength(sb.length()-1);
			}
		}
		return false;
	}

	//Brute force using DFS. If n is the length of string and m is the number of
	//unique characters, the time complexity is O(m^n). Space is O(m+n)
    public static String rearrangeString0(String str, int k) {
    	Map<Character, Integer> charCounts = new HashMap<>();
    	for (int i = 0; i < str.length(); ++i) {
    		char c = str.charAt(i);
    		int prevCount = charCounts.getOrDefault(c, 0);
    		charCounts.put(c, prevCount + 1);
    	}
    	List<Map.Entry<Character, Integer>> charCountsList = 
    		new ArrayList<>(charCounts.entrySet());
    	StringBuilder sb = new StringBuilder();
    	boolean found = dfs(charCountsList, str.length(), new HashMap<Character, Integer>(),
    		k, sb);
    	return found ? sb.toString() : "";
    }

    private static class Pair implements Comparable<Pair> {
    	final char c;
    	int count;
    	Pair(char c, int count) {
    		this.c = c;
    		this.count = count;
    	}

    	//Use natrual order
    	public int compareTo(Pair other) {
    		if (this.count == other.count) {
    			return Character.compare(this.c, other.c);
    		}
    		return Integer.compare(this.count, other.count);
    	}
    }

    //More efficient solution based on greedy selection.
    //Cannot prove the correctness of it.
    //https://discuss.leetcode.com/topic/48091/c-unordered_map-priority_queue-solution-using-cache
    //O(nlogm) time and O(n + m) space
	public static String rearrangeString(String str, int k) {
		Map<Character, Integer> charCounts = new HashMap<>();
    	for (int i = 0; i < str.length(); ++i) {
    		char c = str.charAt(i);
    		int prevCount = charCounts.getOrDefault(c, 0);
    		charCounts.put(c, prevCount + 1);
    	}
    	PriorityQueue<Pair> heap = new PriorityQueue<>(charCounts.size(),
    		Collections.reverseOrder());
    	for (Map.Entry<Character, Integer> charCount : charCounts.entrySet()) {
    		Pair pair = new Pair(charCount.getKey(), charCount.getValue());
    		heap.offer(pair);
    	}
    	int numLeft = str.length();
    	Deque<Pair> usedPairs = new ArrayDeque<>();
    	StringBuilder result = new StringBuilder();
    	for (; numLeft > 0; ) {
    		int numChars = Math.min(k, numLeft);
    		for (int i = 0; i < numChars; ++i) {
    			if (heap.isEmpty()) {
    				return "";
    			}
    			usedPairs.offer(heap.poll());
    			numLeft--;
    		}
    		while(!usedPairs.isEmpty()) {
    			Pair pair = usedPairs.poll();
    			result.append(pair.c);
    			if (--pair.count > 0) {
    				heap.offer(pair);	
    			}
    		}
    	}
    	return result.toString();
	}

	//Another implementation without using heap. O(nm) time
	//https://discuss.leetcode.com/topic/48260/java-15ms-solution-with-two-auxiliary-array-o-n-time
	
    public static void main(String[] args) {
    	String[] tests = new String[] {
    		"aabbcc",
    		"aaabc",
    		"aaadbbcc",
    		"aa",
    		"aa",
    		"a"
    	};
    	int[] ks = new int[] {
    		3, 3, 2, 4, 1, 2
    	};
    	for (int i = 0; i < tests.length; ++i) {
    		String test = tests[i];
    		int k = ks[i];
    		System.out.println(test + ", k = " + k);
    		System.out.println("Result = " + rearrangeString(test, k));
    	}
    }
}