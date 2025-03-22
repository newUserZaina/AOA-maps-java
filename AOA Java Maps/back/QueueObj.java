// QueueObj class for the priority queue
public class QueueObj implements Comparable<QueueObj>
{
    public Vertex vertex;
    public int priority;

    public QueueObj(Vertex v, int p)
    {
        this.vertex = v;
        this.priority = p;
    }

    @Override //checking if the current edge are less than prev edge
    public int compareTo(QueueObj o)
    {
        if (this.priority == o.priority)
        {
            return 0;
        }
        else if (this.priority > o.priority)
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
}