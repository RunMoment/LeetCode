package cn.machine.geek;

/**
 * 206. 反转链表
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
public class 反转链表 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseList(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode node = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    /**
     * 方法一：迭代
     * 假设存在链表 1 \rightarrow 2 \rightarrow 3 \rightarrow \varnothing1→2→3→∅，我们想要把它改成 \varnothing \leftarrow 1 \leftarrow 2 \leftarrow 3∅←1←2←3。
     * <p>
     * 在遍历列表时，将当前节点的 \textit{next}next 指针改为指向前一个元素。由于节点没有引用其上一个节点，因此必须事先存储其前一个元素。在更改引用之前，还需要另一个指针来存储下一个节点。不要忘记在最后返回新的头引用！
     * <p>
     * Java
     * <p>
     * class Solution {
     * public ListNode reverseList(ListNode head) {
     * ListNode prev = null;
     * ListNode curr = head;
     * while (curr != null) {
     * ListNode nextTemp = curr.next;
     * curr.next = prev;
     * prev = curr;
     * curr = nextTemp;
     * }
     * return prev;
     * }
     * }
     * 复杂度分析
     * <p>
     * 时间复杂度：O(n)O(n)，假设 nn 是列表的长度，时间复杂度是 O(n)O(n)。
     * 空间复杂度：O(1)O(1)。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/reverse-linked-list/solution/fan-zhuan-lian-biao-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param head
     * @return
     */
    public ListNode reverseListOfficial1(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    /**
     * 方法二：递归
     * 递归版本稍微复杂一些，其关键在于反向工作。假设列表的其余部分已经被反转，现在我们应该如何反转它前面的部分？
     * <p>
     * 假设列表为：
     * <p>
     * n_{1}\rightarrow \ldots \rightarrow n_{k-1} \rightarrow n_{k} \rightarrow n_{k+1} \rightarrow \ldots \rightarrow n_{m} \rightarrow \varnothing
     * n
     * 1
     * ​
     * →…→n
     * k−1
     * ​
     * →n
     * k
     * ​
     * →n
     * k+1
     * ​
     * →…→n
     * m
     * ​
     * →∅
     * <p>
     * 若从节点 n_{k+1}n
     * k+1
     * ​
     * 到 n_{m}n
     * m
     * ​
     * 已经被反转，而我们正处于 n_{k}n
     * k
     * ​
     * 。
     * <p>
     * n_{1}\rightarrow \ldots \rightarrow n_{k-1} \rightarrow n_{k} \rightarrow n_{k+1} \leftarrow \ldots \leftarrow n_{m}
     * n
     * 1
     * ​
     * →…→n
     * k−1
     * ​
     * →n
     * k
     * ​
     * →n
     * k+1
     * ​
     * ←…←n
     * m
     * ​
     * <p>
     * <p>
     * 我们希望 n_{k+1}n
     * k+1
     * ​
     * 的下一个节点指向 n_{k}n
     * k
     * ​
     * 。
     * <p>
     * 所以，n_k.\textit{next}.\textit{next} = n_{k}n
     * k
     * ​
     * .next.next=n
     * k
     * ​
     * 。
     * <p>
     * 要小心的是 n_{1}n
     * 1
     * ​
     * 的下一个必须指向 \varnothing∅ 。如果你忽略了这一点，你的链表中可能会产生循环。如果使用大小为 22 的链表测试代码，则可能会捕获此错误。
     * <p>
     * Java
     * <p>
     * class Solution {
     * public ListNode reverseList(ListNode head) {
     * if (head == null || head.next == null) {
     * return head;
     * }
     * ListNode p = reverseList(head.next);
     * head.next.next = head;
     * head.next = null;
     * return p;
     * }
     * }
     * 复杂度分析
     * <p>
     * 时间复杂度：O(n)O(n)，假设 nn 是列表的长度，那么时间复杂度为 O(n)O(n)。
     * 空间复杂度：O(n)O(n)，由于使用递归，将会使用隐式栈空间。递归深度可能会达到 nn 层。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/reverse-linked-list/solution/fan-zhuan-lian-biao-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param head
     * @return
     */
    public ListNode reverseListOfficial2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}
