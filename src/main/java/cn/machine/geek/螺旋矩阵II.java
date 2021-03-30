package cn.machine.geek;

/**
 * 螺旋矩阵 II
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：[[1]]
 *
 *
 * 提示：
 *
 * 1 <= n <= 20
 */
public class 螺旋矩阵II {
    private int[][] direct = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int index = 0;
        int count = n * n;
        int row = 0;
        int column = 0;
        for (int i = 1; i <= count; i++){
            matrix[row][column] = i;
            int nextRow = row + direct[index][0];
            int nextCol = column + direct[index][1];
            if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n || matrix[nextRow][nextCol] != 0){
                index = (index + 1) % direct.length;
            }
            row += direct[index][0];
            column += direct[index][1];
        }
        return matrix;
    }
    /**
     * 方法一：模拟
     * 模拟矩阵的生成。按照要求，初始位置设为矩阵的左上角，初始方向设为向右。若下一步的位置超出矩阵边界，或者是之前访问过的位置，则顺时针旋转，进入下一个方向。如此反复直至填入 n^2n
     * 2
     *   个元素。
     *
     * 记 \textit{matrix}matrix 为生成的矩阵，其初始元素设为 00。由于填入的元素均为正数，我们可以判断当前位置的元素值，若不为 00，则说明已经访问过此位置。
     *
     * JavaJavaScriptGolangPython3C++C
     *
     * class Solution {
     *     public int[][] generateMatrix(int n) {
     *         int maxNum = n * n;
     *         int curNum = 1;
     *         int[][] matrix = new int[n][n];
     *         int row = 0, column = 0;
     *         int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 右下左上
     *         int directionIndex = 0;
     *         while (curNum <= maxNum) {
     *             matrix[row][column] = curNum;
     *             curNum++;
     *             int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
     *             if (nextRow < 0 || nextRow >= n || nextColumn < 0 || nextColumn >= n || matrix[nextRow][nextColumn] != 0) {
     *                 directionIndex = (directionIndex + 1) % 4; // 顺时针旋转至下一个方向
     *             }
     *             row = row + directions[directionIndex][0];
     *             column = column + directions[directionIndex][1];
     *         }
     *         return matrix;
     *     }
     * }
     * 复杂度分析
     *
     * 时间复杂度：O(n^2)O(n
     * 2
     *  )，其中 nn 是给定的正整数。矩阵的大小是 n \times nn×n，需要填入矩阵中的每个元素。
     *
     * 空间复杂度：O(1)O(1)。除了返回的矩阵以外，空间复杂度是常数。
     *
     * 方法二：按层模拟
     * 可以将矩阵看成若干层，首先填入矩阵最外层的元素，其次填入矩阵次外层的元素，直到填入矩阵最内层的元素。
     *
     * 定义矩阵的第 kk 层是到最近边界距离为 kk 的所有顶点。例如，下图矩阵最外层元素都是第 11 层，次外层元素都是第 22 层，最内层元素都是第 33 层。
     *
     *
     * [[1, 1, 1, 1, 1, 1],
     *  [1, 2, 2, 2, 2, 1],
     *  [1, 2, 3, 3, 2, 1],
     *  [1, 2, 3, 3, 2, 1],
     *  [1, 2, 2, 2, 2, 1],
     *  [1, 1, 1, 1, 1, 1]]
     * 对于每层，从左上方开始以顺时针的顺序填入所有元素。假设当前层的左上角位于 (\textit{top}, \textit{left})(top,left)，右下角位于 (\textit{bottom}, \textit{right})(bottom,right)，按照如下顺序填入当前层的元素。
     *
     * 从左到右填入上侧元素，依次为 (\textit{top}, \textit{left})(top,left) 到 (\textit{top}, \textit{right})(top,right)。
     *
     * 从上到下填入右侧元素，依次为 (\textit{top} + 1, \textit{right})(top+1,right) 到 (\textit{bottom}, \textit{right})(bottom,right)。
     *
     * 如果 \textit{left} < \textit{right}left<right 且 \textit{top} < \textit{bottom}top<bottom，则从右到左填入下侧元素，依次为 (\textit{bottom}, \textit{right} - 1)(bottom,right−1) 到 (\textit{bottom}, \textit{left} + 1)(bottom,left+1)，以及从下到上填入左侧元素，依次为 (\textit{bottom}, \textit{left})(bottom,left) 到 (\textit{top} + 1, \textit{left})(top+1,left)。
     *
     * 填完当前层的元素之后，将 \textit{left}left 和 \textit{top}top 分别增加 11，将 \textit{right}right 和 \textit{bottom}bottom 分别减少 11，进入下一层继续填入元素，直到填完所有元素为止。
     *
     *
     * 1 / 15
     *
     * JavaJavaScriptGolangPython3C++C
     *
     * class Solution {
     *     public int[][] generateMatrix(int n) {
     *         int num = 1;
     *         int[][] matrix = new int[n][n];
     *         int left = 0, right = n - 1, top = 0, bottom = n - 1;
     *         while (left <= right && top <= bottom) {
     *             for (int column = left; column <= right; column++) {
     *                 matrix[top][column] = num;
     *                 num++;
     *             }
     *             for (int row = top + 1; row <= bottom; row++) {
     *                 matrix[row][right] = num;
     *                 num++;
     *             }
     *             if (left < right && top < bottom) {
     *                 for (int column = right - 1; column > left; column--) {
     *                     matrix[bottom][column] = num;
     *                     num++;
     *                 }
     *                 for (int row = bottom; row > top; row--) {
     *                     matrix[row][left] = num;
     *                     num++;
     *                 }
     *             }
     *             left++;
     *             right--;
     *             top++;
     *             bottom--;
     *         }
     *         return matrix;
     *     }
     * }
     * 复杂度分析
     *
     * 时间复杂度：O(n^2)O(n
     * 2
     *  )，其中 nn 是给定的正整数。矩阵的大小是 n \times nn×n，需要填入矩阵中的每个元素。
     *
     * 空间复杂度：O(1)O(1)。除了返回的矩阵以外，空间复杂度是常数。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/spiral-matrix-ii/solution/luo-xuan-ju-zhen-ii-by-leetcode-solution-f7fp/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
