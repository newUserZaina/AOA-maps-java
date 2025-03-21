import java.util.ArrayList;

public class Graph 
{
    private ArrayList<Vertex> vertices;
    private boolean isWeighted; //sets weight
    private boolean isDirected; //means it has an linked vertex

    public Graph(boolean inputIsWeighted, boolean inputIsDirected)
    {
        this.vertices = new ArrayList<>();
        this.isWeighted = inputIsWeighted;
        this.isDirected = inputIsDirected;
    }

    public Graph() {
    }

    public Vertex addVertex(String data)
    {
        Vertex newVertex = new Vertex(data);
        this.vertices.add(newVertex);
        return newVertex;
    }

    //to link a location to another
    public void addEdge(Vertex vertex1, Vertex vertex2, Double distWeight, Integer durWeight)
    {
        if (!this.isWeighted)
        {
            distWeight = null;
            durWeight = null;
        }

        vertex1.addEdge(vertex2, distWeight, durWeight);
        if(!this.isDirected)
        {
            vertex2.addEdge(vertex2, distWeight, durWeight);
        }
    }

    public void removeEdge(Vertex vertex1, Vertex vertex2)
    {
        vertex1.removeEdge(vertex2);
        if(!this.isDirected)
        {
            vertex2.removeEdge(vertex1);
        }
    }

    public void removeVertex(Vertex vertex)
    {
        this.vertices.remove(vertex);
    }

    public ArrayList<Vertex> getVertices() {
		return this.vertices;
	}

	public boolean isWeighted() {
		return this.isWeighted;
	}

	public boolean isDirected() {
		return this.isDirected;
	}

	public Vertex getVertexByValue(String value) {
		for(Vertex v: this.vertices) { 
			if (v.getData() == null ? value == null : v.getData().equals(value)) {
				return v;
			}
		}

		return null;
	}
	
	public void print() {
		for(Vertex v: this.vertices) {
			v.print(isWeighted);
		}
	}
    
}