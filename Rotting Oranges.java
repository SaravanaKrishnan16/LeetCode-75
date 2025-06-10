You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

Example 1:

Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 10
grid[i][j] is 0, 1, or 2.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

class Solution {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        int fresh = 0;
        for(int r=0;r<rows;r++){
            for(int c=0;c<cols;c++){
                if(grid[r][c]==2){
                    q.add(new int[]{r,c});
                }
                else if(grid[r][c]==1){
                    fresh++;
                }
            }
        }
        if(fresh==0) return 0;
        int minutes = -1;
        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
        while(!q.isEmpty()){
            int size = q.size();
            minutes++;
            for(int i=0;i<size;i++){
                int[] pos = q.poll();
                int r = pos[0] , c = pos[1];
                for(int[] d : directions){
                    int nr = r+d[0];
                    int nc = c+d[1];
                    if(nr>=0 && nc>=0 && nr<rows && nc<cols && grid[nr][nc]==1){
                        grid[nr][nc]=2;
                        fresh--;
                        q.add(new int[]{nr,nc});
                    }
                }
            }
        }
        return fresh==0 ? minutes:-1;
    }
}
