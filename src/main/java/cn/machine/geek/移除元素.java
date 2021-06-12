package cn.machine.geek;

/**
 * 27. 移除元素
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * <p>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 给定 nums = [3,2,2,3], val = 3,
 * <p>
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * <p>
 * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
 * <p>
 * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 * <p>
 * 注意这五个元素可为任意顺序。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * <p>
 * 说明:
 * <p>
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * <p>
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * <p>
 * 你可以想象内部操作如下:
 * <p>
 * // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
 * int len = removeElement(nums, val);
 * <p>
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 */
public class 移除元素 {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        int n = nums.length;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                n--;
            } else {
                nums[index++] = nums[i];
            }
        }
        return n;
    }
    /**
     * 概要
     * 这是一个相当简单的问题，但人们可能会对“就地”一词感到困惑，并认为在不复制数组的情况下从数组中删除元素是不可能的。
     *
     * 提示
     * 尝试双指针法。
     * 你是否使用“元素顺序可以更改”这一属性？
     * 当要删除的元素很少时会发生什么？
     * 解决方案
     * 方法一：双指针
     * 思路
     *
     * 既然问题要求我们就地删除给定值的所有元素，我们就必须用 O(1)O(1) 的额外空间来处理它。如何解决？我们可以保留两个指针 ii 和 jj，其中 ii 是慢指针，jj 是快指针。
     *
     * 算法
     *
     * 当 nums[j]nums[j] 与给定的值相等时，递增 jj 以跳过该元素。只要 nums[j] \neq valnums[j]
     * 
     * ​
     *  =val，我们就复制 nums[j]nums[j] 到 nums[i]nums[i] 并同时递增两个索引。重复这一过程，直到 jj 到达数组的末尾，该数组的新长度为 ii。
     *
     * 该解法与 删除排序数组中的重复项 的解法十分相似。
     *
     * Java
     *
     * public int removeElement(int[] nums, int val) {
     *     int i = 0;
     *     for (int j = 0; j < nums.length; j++) {
     *         if (nums[j] != val) {
     *             nums[i] = nums[j];
     *             i++;
     *         }
     *     }
     *     return i;
     * }
     * 复杂度分析
     *
     * 时间复杂度：O(n)O(n)，
     * 假设数组总共有 nn 个元素，ii 和 jj 至少遍历 2n2n 步。
     *
     * 空间复杂度：O(1)O(1)。
     *
     * 方法二：双指针 —— 当要删除的元素很少时
     * 思路
     *
     * 现在考虑数组包含很少的要删除的元素的情况。例如，num=[1，2，3，5，4]，Val=4num=[1，2，3，5，4]，Val=4。之前的算法会对前四个元素做不必要的复制操作。另一个例子是 num=[4，1，2，3，5]，Val=4num=[4，1，2，3，5]，Val=4。似乎没有必要将 [1，2，3，5][1，2，3，5] 这几个元素左移一步，因为问题描述中提到元素的顺序可以更改。
     *
     * 算法
     *
     * 当我们遇到 nums[i] = valnums[i]=val 时，我们可以将当前元素与最后一个元素进行交换，并释放最后一个元素。这实际上使数组的大小减少了 1。
     *
     * 请注意，被交换的最后一个元素可能是您想要移除的值。但是不要担心，在下一次迭代中，我们仍然会检查这个元素。
     *
     * Java
     *
     * public int removeElement(int[] nums, int val) {
     *     int i = 0;
     *     int n = nums.length;
     *     while (i < n) {
     *         if (nums[i] == val) {
     *             nums[i] = nums[n - 1];
     *             // reduce array size by one
     *             n--;
     *         } else {
     *             i++;
     *         }
     *     }
     *     return n;
     * }
     * 复杂度分析
     *
     * 时间复杂度：O(n)O(n)，ii 和 nn 最多遍历 nn 步。在这个方法中，赋值操作的次数等于要删除的元素的数量。因此，如果要移除的元素很少，效率会更高。
     *
     * 空间复杂度：O(1)O(1)。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/remove-element/solution/yi-chu-yuan-su-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
