import java.util.*;

public class ExpressionAddOperators {
    
    private static void backTrack(String num, int target, int start, long sum, long subSum, char prevSign,
            StringBuilder sb, List<String> result) {
        int prevSbLength = sb.length();
        if (start >= num.length()) {
            if (prevSign == '+' && (sum + subSum) == target) {
                result.add(sb.toString());
            }
            return;
        }
        if (start > 0) {
            sb.append(prevSign);
        }
        long curNum = 0;
        for (int i = start; i < num.length(); ++i) {
            curNum = 10 * curNum + (int)(num.charAt(i) - '0');
            sb.append(num.charAt(i));
            long tempSum = sum;
            long tempSubSum = subSum;
            switch (prevSign) {
                case '+' :  tempSum += tempSubSum;
                            tempSubSum = curNum;
                            break;
                case '-' :  tempSum += tempSubSum;
                            tempSubSum = -curNum;
                            break;
                case '*' :  tempSubSum *= curNum;
            }
            backTrack(num, target, i+1, tempSum, tempSubSum, '+', sb, result);
            backTrack(num, target, i+1, tempSum, tempSubSum, '-', sb, result);
            backTrack(num, target, i+1, tempSum, tempSubSum, '*', sb, result);
            if (i == start && num.charAt(i) == '0') {
                break;
            }
        }
        sb.setLength(prevSbLength);
    }
    
    public List<String> addOperators0(String num, int target) {
        List<String> result = new ArrayList<>();
        backTrack(num, target, 0, 0, 0, '+', new StringBuilder(), result);
        return result;
    }

    //Improved backtrack solution:
    private static void backTrack(String num, long target, int start, long sum, long subSum,
            StringBuilder sb, List<String> result) {
        if (start >= num.length()) {
            if ((sum + subSum) == target) {
                result.add(sb.toString());
            }
            return;
        }
        long curNum = 0;
        StringBuilder curSb = new StringBuilder();
        int originalSbLength = sb.length();
        for (int i = start; i < num.length(); ++i) {
            curNum = 10 * curNum + (int)(num.charAt(i) - '0');
            curSb.append(num.charAt(i));
            if (start == 0) {
                backTrack(num, target, i+1, sum + subSum, curNum, sb.append(curSb), result);
            } else {
                int signId = sb.length();
                backTrack(num, target, i+1, sum + subSum, curNum, sb.append('+').append(curSb), result);
                sb.setCharAt(signId, '-');
                backTrack(num, target, i+1, sum + subSum, -curNum, sb, result);
                sb.setCharAt(signId, '*');
                backTrack(num, target, i+1, sum, subSum * curNum, sb, result);
            }
            sb.setLength(originalSbLength);
            if (i == start && num.charAt(i) == '0') {
                break;
            }
        }
    }
    
    //Slightly improved from above
    private static void backTrack(String num, int target, int start, long sum, long subSum,
            StringBuilder sb, List<String> result) {
        if (start >= num.length() || sum > Integer.MAX_VALUE || sum < Integer.MIN_VALUE
           || subSum > Integer.MAX_VALUE) {
            if ((sum + subSum) == target) {
                result.add(sb.toString());
            }
            return;
        }
        long curNum = 0;
        StringBuilder curSb = new StringBuilder();
        int originalSbLength = sb.length();
        for (int i = start; i < num.length(); ++i) {
            curNum = 10 * curNum + (int)(num.charAt(i) - '0');
            curSb.append(num.charAt(i));
            if (start == 0) {
                backTrack(num, target, i+1, sum + subSum, curNum, sb.append(curSb), result);
            } else {
                int signId = sb.length();
                backTrack(num, target, i+1, sum + subSum, curNum, sb.append('+').append(curSb), result);
                sb.setCharAt(signId, '-');
                backTrack(num, target, i+1, sum + subSum, -curNum, sb, result);
                sb.setCharAt(signId, '*');
                backTrack(num, target, i+1, sum, subSum * curNum, sb, result);
            }
            sb.setLength(originalSbLength);
            if (curNum == 0) {
                break;
            }
        }
    }

    //Same logic as above, but use char[] instead of StringBuilder.
    //Much faster on OJ. 
    private static void backTrack(String num, long target, int start, long sum, long subSum,
            char[] exp, int expLen, List<String> result) {
        if (start >= num.length()) {
            if ((sum + subSum) == target) {
                result.add(new String(exp, 0, expLen));
            }
            return;
        }
        long curNum = 0;
        int expId = (start == 0) ? expLen : expLen + 1;
        for (int i = start; i < num.length(); ++i) {
            curNum = 10 * curNum + (int)(num.charAt(i) - '0');
            exp[expId++] = num.charAt(i);
            if (start == 0) {
                backTrack(num, target, i+1, sum + subSum, curNum, exp, expId, result);
            } else {
                exp[expLen] = '+';
                backTrack(num, target, i+1, sum + subSum, curNum, exp, expId, result);
                exp[expLen] = '-';
                backTrack(num, target, i+1, sum + subSum, -curNum, exp, expId, result);
                exp[expLen] = '*';
                backTrack(num, target, i+1, sum, subSum * curNum, exp, expId, result);
            }
            if (i == start && num.charAt(i) == '0') {
                break;
            }
        }
    }

    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        backTrack(num, target, 0, 0, 0, new StringBuilder(), result);
        return result;
    }


    private static boolean hasDuplicates(List<String> result) {
    	Set<String> uniqueStrs = new HashSet<>();
    	for (String str : result) {
    		if (uniqueStrs.contains(str)) {
    			System.out.println("Duplicate: " + str);
    		}
    		uniqueStrs.add(str);
    	}
    	System.out.println("Number of results = " + result.size());
    	return uniqueStrs.size() != result.size();
    }

    public static void main(String[] args) {
    	String[] tests = new String[] {
    		"1000000009"
    	};
    	int[] targets = new int[] {
    		9
    	};
    	ExpressionAddOperators solution = new ExpressionAddOperators();
    	for (int i = 0; i < tests.length; ++i) {
    		String test = tests[i];
    		int target = targets[i];
    		System.out.println(test + ", " + target);
    		List<String> result = solution.addOperators(test, target);
    		boolean foundDuplicates = hasDuplicates(result);
    		System.out.println("Found duplicate = " + foundDuplicates);
    	}
    }
}