import java.util.*;

public class DirectedGraph {
    public int nodeCount;
    public ArrayList<Edge> graph = new ArrayList<>();
    private final NodeState[] nodeStates;

    public DirectedGraph(int nodeCount, String[] precedings) {
        this.nodeCount = nodeCount;

        nodeStates = new NodeState[nodeCount];
        for (var i = 0; i < nodeCount; i++)
            nodeStates[i] = NodeState.NOT_VISITED;

        makeGraph(precedings);
    }

    public String[] getMaxPath(int start, int end) {
        return new String[0];
    }

    public int getMaxPathCost(int start, int end) {
        var fromToArray = new ArrayList<Integer>();

        for (var edge : graph) {
            fromToArray.add(edge.from);
            fromToArray.add(edge.to);
        }
        fromToArray.add(start - 1);
        fromToArray.add(end - 1);

        var maxNodeIndex = Collections.max(fromToArray);

        var opt = new Integer[fromToArray.size()];
        opt[0] = 1;
        for (var i = 1; i < opt.length; i++)
            opt[i] = Integer.MIN_VALUE;

        for (var pathSize = 1; pathSize <= maxNodeIndex; pathSize++)
            for (var edge : graph)
                if (opt[edge.from] != Integer.MIN_VALUE)
                    opt[edge.to] = Math.max(opt[edge.to], opt[edge.from] * edge.cost);

        return opt[end - 1];
    }

    private void makeGraph(String[] precedingsArray) {
        for (var i = 0; i < precedingsArray.length; i++) {
            var splited = precedingsArray[i].split(" ");
            var correctPrecedingsList =
                    Arrays.asList(splited).subList(0, splited.length - 1);

            addNodePrecedings(i, correctPrecedingsList);
        }
    }

    public void addNodePrecedings(int nodeNumber, List<String> precedings) {
        if (precedings.size() > 1)
            for (var i = 0; i < precedings.size(); i += 2)
                graph.add(new Edge(
                        Integer.parseInt(precedings.get(i)) - 1,
                        nodeNumber,
                        Integer.parseInt(precedings.get(i + 1)))
                );
    }
}
