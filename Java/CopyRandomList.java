/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class CopyRandomList {

	// O(n) time and O(n) space
    public RandomListNode copyRandomList0(RandomListNode head) {
        RandomListNode newDummy = new RandomListNode(-1);
        RandomListNode pre = newDummy;
        Map<RandomListNode, RandomListNode> nodeMap = new HashMap<>();
        for (RandomListNode cur = head; cur != null; cur = cur.next) {
            RandomListNode newCur = new RandomListNode(cur.label);
            nodeMap.put(cur, newCur);
            pre.next = newCur;
            pre = newCur;
        }
        RandomListNode cur = head;
        for (RandomListNode newCur = newDummy.next; newCur != null; newCur = newCur.next, cur = cur.next) {
            newCur.random = nodeMap.get(cur.random);
        }
        return newDummy.next;
    }
    
    // O(n) time and O(1) space
    public RandomListNode copyRandomList(RandomListNode head) {
        for (RandomListNode cur = head; cur != null; cur = cur.next.next) {
            RandomListNode curCopy = new RandomListNode(cur.label);
            curCopy.next = cur.next;
            cur.next = curCopy;
        }
        for (RandomListNode cur = head; cur != null; cur = cur.next.next) {
            cur.next.random = (cur.random == null) ? null : cur.random.next;
        }
        RandomListNode newDummy = new RandomListNode(-1);
        RandomListNode pre = newDummy;
        for (RandomListNode cur = head; cur != null; cur = cur.next) {
            pre.next = cur.next;
            cur.next = cur.next.next;
            pre = pre.next;
        }
        return newDummy.next;
    }
}