/*
Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs.
The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104. Reconstruction
means building a shortest common supersequence of the sequences in seqs (i.e., a shortest
sequence so that all sequences in seqs are subsequences of it). Determine whether there is only
one sequence that can be reconstructed from seqs and it is the org sequence.

Example 1:

Input:
org: [1,2,3], seqs: [[1,2],[1,3]]

Output:
false

Explanation:
[1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a 
valid sequence that can be reconstructed.

Example 2:

Input:
org: [1,2,3], seqs: [[1,2]]

Output:
false

Explanation:
The reconstructed sequence can only be [1,2].

Example 3:

Input:
org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]

Output:
true

Explanation:
The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].

Example 4:

Input:
org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]

Output:
true

UPDATE (2017/1/8):
The seqs parameter had been changed to a list of list of strings (instead of a 2d array of 
strings). Please reload the code definition to get the latest changes.
*/

class SequenceReconstruction {
    
    //O(n_org + n_seq_elements) time and O(n_org) space
    //Pay attention to the edge case when there is no element in seqs.
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        if (org == null || seqs == null) {
            return false;
        }
        Map<Integer, Integer> numPos = new HashMap<>();
        for (int i = 0; i < org.length; ++i) {
            numPos.put(org[i], i);
        }
        Set<Integer> marked = new HashSet<>();
        boolean isEmptySeq = true;
        for (List<Integer> seq : seqs) {
            for (int i = 0; i < seq.size(); ++i) {
                isEmptySeq = false;
                Integer curPos = numPos.get(seq.get(i));
                if (curPos == null) {
                    return false;
                }
                if (i > 0) {
                    Integer prevPos = numPos.get(seq.get(i-1));
                    if (prevPos >= curPos) {
                        return false;
                    }
                    if (prevPos == curPos - 1) {
                        marked.add(prevPos);
                    }
                }
            }
        }
        return isEmptySeq ? org.length == 0 : marked.size() == numPos.size() - 1;
    }
}