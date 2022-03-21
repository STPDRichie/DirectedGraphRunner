import java.util.*;

public class DirectedGraph {
    public int nodeCount;

    public ArrayList<Edge> graph = new ArrayList<>();

    public DirectedGraph(int nodeCount, String[] precedings) {
        this.nodeCount = nodeCount;

        makeGraph(precedings);
    }

    public String[] getMaxPath(int start, int end) {
        var path = new HashMap<Integer, Integer>();

        var optimal = new int[nodeCount];
        Arrays.fill(optimal, Integer.MIN_VALUE);
        optimal[start - 1] = 1;

        fillOptimalAndPath(optimal, path);

        var correctResult = new ArrayList<String>();

        if (optimal[end - 1] != Integer.MIN_VALUE) {
            var pathBuilder = new StringBuilder();
            pathBuilder.append(" ").append(end);

            var currentLevel = path.get(end - 1);
            for (var i = 0; i < path.size(); i++) {
                pathBuilder.append(" ").append(currentLevel + 1);
                currentLevel = path.get(currentLevel);
                if (currentLevel == null)
                    break;
            }
            correctResult.add(pathBuilder.reverse().toString());
            correctResult.add(Integer.toString(optimal[end - 1]));
        }

        return correctResult.toArray(new String[0]);
    }

    private void fillOptimalAndPath(int[] optimal, HashMap<Integer, Integer> path) {
        for (var pathSize = 1; pathSize <= nodeCount - 1; pathSize++)
            for (var edge : graph)
                if (optimal[edge.from] != Integer.MIN_VALUE) {
                    if (optimal[edge.from] * edge.cost >= optimal[edge.to]) {
                        optimal[edge.to] = optimal[edge.from] * edge.cost;
                        path.put(edge.to, edge.from);
                    }
                }
    }

    private void makeGraph(String[] precedingsArray) {
        for (var i = 0; i < precedingsArray.length; i++) {
            var splited = precedingsArray[i].split(" ");
            var correctPrecedingsList =
                    Arrays.asList(splited).subList(0, splited.length - 1);

            addNodePrecedings(i, correctPrecedingsList);
        }
    }

    private void addNodePrecedings(int nodeNumber, List<String> precedings) {
        if (precedings.size() > 1)
            for (var i = 0; i < precedings.size(); i += 2)
                graph.add(new Edge(
                        Integer.parseInt(precedings.get(i)) - 1,
                        nodeNumber,
                        Integer.parseInt(precedings.get(i + 1))));
    }
}
