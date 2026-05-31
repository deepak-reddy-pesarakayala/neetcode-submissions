class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
         boolean[][] pre = new boolean[numCourses][numCourses];

        for (int[] p : prerequisites) {
            int a = p[0];
            int b = p[1];
            pre[a][b] = true;
        }

        for (int k = 0; k < numCourses; k++) {
            for (int i = 0; i < numCourses; i++) {
                for (int j = 0; j < numCourses; j++) {
                    if (pre[i][k] && pre[k][j]) {
                        pre[i][j] = true;
                    }
                }
            }
        }

        List<Boolean> answer = new ArrayList<>();

        for (int[] q : queries) {
            answer.add(pre[q[0]][q[1]]);
        }

        return answer;
    }
}
