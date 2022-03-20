import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DirectedGraph {
    public int nodeCount;
    public HashMap<Integer, HashMap<Integer, Integer>> graph = new HashMap<>();
    private final NodeState[] nodeStates;

    public DirectedGraph(int nodeCount, String[] precedings) {
        this.nodeCount = nodeCount;

        nodeStates = new NodeState[nodeCount];
        for (var i = 0; i < nodeCount; i++)
            nodeStates[i] = NodeState.NOT_VISITED;

        makeGraph(precedings);
    }

    public String[] run(int start, int end) {
        return new String[0];
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
        var precedingsList = new HashMap<Integer, Integer>();

        if (precedings.size() > 1)
            for (var i = 0; i < precedings.size(); i += 2)
                precedingsList.put(Integer.parseInt(precedings.get(i)) - 1, Integer.parseInt(precedings.get(i + 1)));

        graph.put(nodeNumber, precedingsList);
    }
}
