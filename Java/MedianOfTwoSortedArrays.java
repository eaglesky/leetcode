public class MedianOfTwoSortedArrays {
    
    //Recursive solution, assuming k is always within the range of two arrays.
    //O(logk) time and O(logk) space

    //Algorithm: 
    //Since the total length of two arrays could be even, we just need
    //to implement findKthSortedArrays and call it twice with (n+m)/2 and (n+m)/2 + 1
    //when the total length is even. 

    //Let the two arrays be nums1 and nums2. Divide the two arrays like this:
    //nums1[0]..nums1[l1-1], nums1[l1]..nums1[len1-1]  (represented as [L1, R1])
    //nums2[0]..nums2[l2-1], nums2[l1]..nums2[len2-1]  (represented as [L2, R2])
    //(l1 + l2 = k, and l1 = k/2)

    //if nums1[l1-1] == nums2[l2-1], the kth element should be nums1[l1-1].

    //if nums1[l1-1] > nums2[l2-1], then nums2[l2-1] must be smaller than elements in 
    //R1 and R2. It is at most larger than all the other elements in L2 and L1 except
    //nums1[l1-1], so nums2[l2-1] is at most the (k-1)th element. And since nums1[l1-1]
    //is at least the kth element, the kth element must not be in R1 either. Therefore
    //the kth element must be in either L1 or R2. And the kth element in {L1, R1, L2, R2}
    //must be the (k-l2)th element in {L1, R2}, since the removed l2 elements rank less than
    //k in all of the elements. So the problem now has become to find the (k-l2)th element
    //in L1 and R2. And this can be solved recursively until k is reduced to 1, when we
    //simply return the minimum of the two arrays.

    //if nums1[l1-1] < nums2[l2-1], same approach as above.

    //It is possible that the length of a certain array is 0. In that case we simply return
    //the kth element in the other array.

    //Also l1 and l2 need to be handled carefully. Typically we want them to be 
    //min(length of array, k/2). It is easier to write the code if we fix the shorter array
    //to be nums1. 

    private double findKthSortedArrays(int[] nums1, int low1, int high1,
                                          int[] nums2, int low2, int high2,
                                          int k) {
        if (high1 - low1 > high2 - low2) {
            return findKthSortedArrays(nums2, low2, high2, nums1, low1, high1, k);
        }
        int len1 = high1 - low1 + 1;
        int len2 = high2 - low2 + 1;
        if (len1 <= 0) {
            return nums2[low2 + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[low1], nums2[low2]);
        }
        int l1 = Math.min(len1, k / 2);
        int l2 = k - l1;
        int mid1 = low1 + l1 - 1;
        int mid2 = low2 + l2 - 1;

        if (nums1[mid1] == nums2[mid2]) {
            return nums1[mid1];
        } else if (nums1[mid1] < nums2[mid2]) {
            return findKthSortedArrays(nums1, mid1+1, high1, nums2, low2, mid2, k - l1);
        } else {
            return findKthSortedArrays(nums1, low1, mid1, nums2, mid2+1, high2, k - l2);
        }
    }
    
    //Iterative solution, assuming k is always within the range of two arrays.
    //O(logk) time and O(1) space
    //Same algorithm as above
    private double findKthSortedArrays(int[] nums1, int[] nums2, int k) {
        int low1 = 0, low2 = 0;
        int high1 = nums1.length - 1, high2 = nums2.length - 1;
        while (k > 0) {
            int len1 = high1 - low1 + 1;
            int len2 = high2 - low2 + 1;
            if (len1 <= 0) {
                return nums2[low2 + k - 1];
            } else if (len2 <= 0) {
                return nums1[low1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[low1], nums2[low2]);
            }
            int l1 = k / 2;
            int l2 = k - l1;
            if (l1 > len1) {
                l1 = len1;
                l2 = k - l1;
            }
            if (l2 > len2) {
                l2 = len2;
                l1 = k - l2;
            }
            int mid1 = low1 + l1 - 1;
            int mid2 = low2 + l2 - 1;
            if (nums1[mid1] == nums2[mid2]) {
                return nums1[mid1];
            } else if (nums1[mid1] > nums2[mid2]) {
                high1 = mid1;
                low2 = mid2 + 1;
                k -= l2;
            } else {
                low1 = mid1 + 1;
                high2 = mid2;
                k -= l1;
            }
        }
        return Double.MIN_VALUE;
    }
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        if (totalLength % 2 == 0) {
            return (findKthSortedArrays(nums1, nums2,  totalLength / 2)
                  + findKthSortedArrays(nums1, nums2, totalLength / 2 + 1))
                  / 2.0;
        } else {
            return findKthSortedArrays(nums1, nums2, totalLength / 2 + 1);
        }
    }
}