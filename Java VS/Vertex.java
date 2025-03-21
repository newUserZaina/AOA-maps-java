
import java.util.ArrayList;

public class Vertex 
{
    private final String data;
    private final ArrayList<Edge> edges;

    public Vertex(String inputData)
    {
        this.data = inputData;
        this.edges = new ArrayList<>();
    }

    public void addEdge(Vertex endVertex, Double distWeight, Integer durWeight)
    {
        this.edges.add(new Edge(this, endVertex, distWeight, durWeight));
    }

    public void removeEdge(Vertex endVertex)
    {
        this.edges.removeIf(edge -> edge.getEnd().equals(endVertex));
    }

    public String getData() {
        return data;
    }

    // **Fixed this method**
    public ArrayList<Edge> getEdges() {
        return new ArrayList<>(edges);  // Ensure a new list is returned
    }

    void print(boolean weighted) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
