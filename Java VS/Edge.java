public class Edge
{
    //takes start and end from Vertex to give it the distance and duration
    private final Vertex start;
    private final Vertex end;
    private final Double distWeight;
    private final Integer durWeight;

    public Edge(Vertex startV, Vertex endV, Double distWeightV, Integer durWeightV)
    {
        this.start = startV;
        this.end = endV;
        this.distWeight = distWeightV;
        this.durWeight = durWeightV;
    }

    public Vertex getStart ()
    {
        return this.start;
    }

    public Vertex getEnd ()
    {
        return this.end;
    }

    public Double getDistWeight ()
    {
        return this.distWeight;
    }

    public Integer getDurWeight ()
    {
        return this.durWeight;
    }
}