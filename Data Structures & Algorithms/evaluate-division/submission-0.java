class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values,
                                 List<List<String>> queries) {

        Map<String, Map<String, Double>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            double val = values[i];

            graph.putIfAbsent(a, new HashMap<>());
            graph.putIfAbsent(b, new HashMap<>());

            graph.get(a).put(b, val);
            graph.get(b).put(a, 1.0 / val);
        }

        double[] answer = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);

            if (!graph.containsKey(start) || !graph.containsKey(end)) {
                answer[i] = -1.0;
            } else {
                answer[i] = dfs(start, end, graph, new HashSet<>(), 1.0);
            }
        }

        return answer;
    }

    private double dfs(String current, String target,
                       Map<String, Map<String, Double>> graph,
                       Set<String> visited,
                       double product) {

        if (current.equals(target)) {
            return product;
        }

        visited.add(current);

        for (String next : graph.get(current).keySet()) {
            if (!visited.contains(next)) {
                double result = dfs(
                    next,
                    target,
                    graph,
                    visited,
                    product * graph.get(current).get(next)
                );

                if (result != -1.0) {
                    return result;
                }
            }
        }

        return -1.0;
    }
}

