public class Path {
    public final int node;
    public final Path previous;
    public final int length;

    public final int[] path;
    public final int weight;

    public Path(int node, Path previous, int weight) {
        this.node = node;
        this.previous = previous;
        this.length = (previous != null) ? previous.length + 1 : 1;
        this.weight = (previous != null) ? previous.weight * weight : 1;

        if (previous == null)
            path = new int[] {node + 1};
        else {
            path = new int[length];
            for (var i = 0; i < previous.length; i++)
                path[i] = previous.path[i];
            path[length - 1] = node + 1;
        }
    }
}
