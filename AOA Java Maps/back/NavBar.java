import java.util.*;

public class NavBar {
    public static Dictionary[] navigation(Graph g, Vertex origin, boolean byDistance) {
        Dictionary<String, Double> distances = new Hashtable<>();
        Dictionary<String, Integer> durations = new Hashtable<>();
        Dictionary<String, Vertex> previous = new Hashtable<>();
        PriorityQueue<QueueObj> queue = new PriorityQueue<>();

        queue.add(new QueueObj(origin, 0)); //automatically adds the starting location to queue

        for (Vertex v : g.getVertices()) { //sets the weights of the edges to infinity for every vertex
            if (v != origin) {
                distances.put(v.getData(), Double.MAX_VALUE);
                durations.put(v.getData(), Integer.MAX_VALUE);
            }
            previous.put(v.getData(), new Vertex("NULL"));
        }

        distances.put(origin.getData(), 0.0);
        durations.put(origin.getData(), 0);

        while (!queue.isEmpty()) { //finding the shortest path
            Vertex curr = queue.poll().vertex;
            for (Edge e : curr.getEdges()) {
                Double alternativeDist = distances.get(curr.getData()) + e.getDistWeight();
                Integer alternativeDur = durations.get(curr.getData()) + e.getDurWeight();
                String neighborValue = e.getEnd().getData();
                
                if (byDistance && alternativeDist < distances.get(neighborValue)) { //finding the edge with the smallest weight
                    distances.put(neighborValue, alternativeDist); 
                    previous.put(neighborValue, curr);
                    queue.add(new QueueObj(e.getEnd(), distances.get(neighborValue).intValue())); //adds Vertex to queue
                } else if (!byDistance && alternativeDur < durations.get(neighborValue)) {
                    durations.put(neighborValue, alternativeDur);
                    previous.put(neighborValue, curr);
                    queue.add(new QueueObj(e.getEnd(), durations.get(neighborValue)));
                }
            }
        }

        return new Dictionary[]{distances, durations, previous};
    }

    public static void shortestPath(Graph g, Vertex originVertex, Vertex targetVertex, boolean byDistance) {
        Dictionary[] navDictionarys = navigation(g, originVertex, byDistance);
        Dictionary distances = navDictionarys[0];
        Dictionary durations = navDictionarys[1];
        Dictionary previous = navDictionarys[2];

        if (byDistance && (Double) distances.get(targetVertex.getData()) == Double.MAX_VALUE) 
        {
            System.out.println("No path exists between " + originVertex.getData() + " and " + targetVertex.getData());
            return;
        } else if (!byDistance && (Integer) durations.get(targetVertex.getData()) == Integer.MAX_VALUE) {
            System.out.println("No path exists between " + originVertex.getData() + " and " + targetVertex.getData());
            return;
        }

        ArrayList<Vertex> path = new ArrayList<>();
        Vertex v = targetVertex;

        while (!"NULL".equals(v.getData())) {
            path.add(0, v);
            v = (Vertex) previous.get(v.getData());
        }

        if (byDistance) {
            System.out.println("Shortest Path from " + originVertex.getData() + " to " + targetVertex.getData() + " is " + distances.get(targetVertex.getData()) + " km");
        } else {
            System.out.println("Fastest Path from " + originVertex.getData() + " to " + targetVertex.getData() + " is " + durations.get(targetVertex.getData()) + " mins");
        }

        System.out.println("Route:");
        for (Vertex pathVertex : path) {
            System.out.println(pathVertex.getData());
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Graph testGraph = new Graph(true, true);
            
            Vertex a = testGraph.addVertex("Montego Bay");
            Vertex b = testGraph.addVertex("Lucea");
            Vertex c = testGraph.addVertex("Savanna la Mar");
            Vertex d = testGraph.addVertex("Black River");
            Vertex e = testGraph.addVertex("Santa Cruz");
            Vertex f = testGraph.addVertex("Falmouth");
            Vertex g = testGraph.addVertex("St. Ann's Bay");
            Vertex h = testGraph.addVertex("Ocho Rios");
            Vertex j = testGraph.addVertex("Christiana");
            Vertex k = testGraph.addVertex("Mandeville");
            Vertex l = testGraph.addVertex("May Pen");
            Vertex m = testGraph.addVertex("Alley");
            Vertex n = testGraph.addVertex("Port Maria");
            Vertex o = testGraph.addVertex("Annotto Bay");
            Vertex p = testGraph.addVertex("Ewarton");
            Vertex q = testGraph.addVertex("Spanish Town");
            Vertex r = testGraph.addVertex("Port Royal");
            Vertex s = testGraph.addVertex("Kingston");
            Vertex t = testGraph.addVertex("Port Antonio");
            Vertex u = testGraph.addVertex("Morant Point");
            Vertex v = testGraph.addVertex("Negril");
            
            testGraph.addEdge(s, u, 87.4, 105);
            testGraph.addEdge(s, t, 92.2, 124);
            testGraph.addEdge(s, o, 46.8, 73);
            testGraph.addEdge(s, q, 20.2, 28);
            testGraph.addEdge(s, r, 26.3, 32);
            testGraph.addEdge(u, t, 67.7, 83);
            testGraph.addEdge(t, o, 45.5, 52);
            testGraph.addEdge(o, n, 25.2, 27);
            testGraph.addEdge(n, h, 31.4, 32);
            testGraph.addEdge(h, g, 12.1, 14);
            testGraph.addEdge(g, f, 57.1, 57);
            testGraph.addEdge(f, a, 34.9, 36);
            testGraph.addEdge(a, b, 36.5, 37);
            testGraph.addEdge(b, v, 39.6, 37);
            testGraph.addEdge(q, m, 54.7, 68);
            testGraph.addEdge(m, e, 93.1, 106);
            testGraph.addEdge(e, d, 28.9, 28);
            testGraph.addEdge(d, c, 47.3, 47);
            testGraph.addEdge(c, v, 28.1, 28);
            testGraph.addEdge(q, p, 30.5, 43);
            testGraph.addEdge(p, h, 46.8, 48);
            testGraph.addEdge(p, j, 77.6, 117);
            testGraph.addEdge(q, l, 34.6, 37);
            testGraph.addEdge(l, m, 24.5, 45);
            testGraph.addEdge(l, k, 39.4, 44);
            testGraph.addEdge(k, j, 20.9, 32);
            testGraph.addEdge(j, f, 70.2, 93);
            testGraph.addEdge(j, e, 51.2, 54);
            testGraph.addEdge(c, a, 49.5, 59);
            
            System.out.print("Enter origin: ");
            String originInput = scanner.nextLine();
            System.out.print("Enter destination: ");
            String destinationInput = scanner.nextLine();
            System.out.print("Optimize by distance (true) or time (false): ");
            boolean byDistance = scanner.nextBoolean();
            
            Vertex origin = testGraph.getVertexByValue(originInput);
            Vertex destination = testGraph.getVertexByValue(destinationInput);
            
            if (origin != null && destination != null) {
                shortestPath(testGraph, origin, destination, byDistance);
            } else {
                System.out.println("Invalid locations entered.");
            }

        }
    }
}
