package cmsc256;
import java.io.*;
import java.util.*;

/**
 * Tara Ram Mohan
 * Project 6: MyGraph Class
 * 25 April 2020
 */

public class MyGraph<V> extends UnweightedGraph<V> {

    /** Construct an empty graph */
    public MyGraph() {
        super();
    }

    /** Construct a graph from integer vertices 0, 1, and edge array */
    public MyGraph(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    /** Construct a graph for integer vertices 0, 1, 2 and edge list */
    public MyGraph(List<Edge> edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }
    /** Construct a graph from vertices and edges stored in List */
    public MyGraph(List<V> vertices, List<Edge> edges) {
        super(vertices, edges);
    }

    /** Construct a graph from vertices and edges stored in arrays */
    public MyGraph(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }

    /**
     * Private method that reads file and creates MyGraph object (for main method)
     * @return MyGraph object
     */
    public MyGraph<V> readFile(String fileName) throws FileNotFoundException {

        vertices.clear();
        neighbors.clear();

        //Create file
        boolean isConnected = false;

        int rootVertex = 0;
        int numberOfVertices = 0;

        List<Edge> edgesList = new ArrayList<>();
        int[][] edgesArray = new int[numberOfVertices][numberOfVertices];

        if(fileName == null) {
            vertices.clear();
            return new MyGraph<V>(edgesList,0);
        }

        File inputFile = new File(fileName);
        Scanner sc = new Scanner(inputFile);

        if (!inputFile.isFile()) {
            throw new FileNotFoundException();
        }

        //Read file given it exists
        int i = 0;

        while (sc.hasNextLine()) {
            List<Edge> tempEdges = new ArrayList();

            // If not first line of file
            if (i != 0) {

                String line = sc.nextLine();
                String[] temp = line.split(" ");

                if (i == 1) {
                    rootVertex = Integer.parseInt(temp[0]);
                }

                vertices.add((V) temp[0]);

                int origin = Integer.parseInt(temp[0]);

                //Add edges of each vertex to edges array
                for (int k = 1; k < temp.length; k++, i++) { //when does it clear edges

                    int terminating = Integer.parseInt(temp[k]);

                    Edge e = new Edge(origin,terminating);
                    edgesList.add(i-1,e);
                }

            } else { //Store number of vertices from first line of file
                String stringVertices = sc.nextLine();
                numberOfVertices = Integer.parseInt(stringVertices);
                i++;
            }
        }

        MyGraph<V> mygraph = new MyGraph(edgesList, numberOfVertices);
        UnweightedGraph<V> g = new UnweightedGraph(vertices, edgesList);

        return mygraph;
    }

    /**
     * Reads a graph from a file and determines whether the graph is connected.
     * @return true if graph is connected
     * */
    public boolean isGraphConnected(String fileName) throws FileNotFoundException {

        vertices.clear();
        neighbors.clear();

        //Create file
        boolean isConnected = false;

        int rootVertex = 0;
        int numberOfVertices = 0;

        List<Edge> edgesList = new ArrayList<>();
        int[][] edgesArray = new int[numberOfVertices][numberOfVertices];

        if(fileName == null) {
            vertices.clear();
            MyGraph mygraph = new MyGraph<V>(edgesList,0);
            return false;
        }

        File inputFile = new File(fileName);
        Scanner sc = new Scanner(inputFile);

        if (!inputFile.isFile()) {
            throw new FileNotFoundException();
        }

        //Read file given it exists
        int i = 0;

        while (sc.hasNextLine()) {
            List<Edge> tempEdges = new ArrayList();

            // If not first line of file
            if (i != 0) {

                String line = sc.nextLine();
                String[] temp = line.split(" ");

                if (i == 1) {
                    rootVertex = Integer.parseInt(temp[0]);
                }

                vertices.add((V) temp[0]);

                int origin = Integer.parseInt(temp[0]);

                //Add edges of each vertex to edges array
                for (int k = 1; k < temp.length; k++, i++) { //when does it clear edges

                    int terminating = Integer.parseInt(temp[k]);

                    Edge e = new Edge(origin,terminating);
                    edgesList.add(e);
                }

            } else { //Store number of vertices from first line of file
                String stringVertices = sc.nextLine();
                numberOfVertices = Integer.parseInt(stringVertices);
                i++;
            }
        }


        MyGraph<V> mygraph = new MyGraph(edgesList, numberOfVertices);
        UnweightedGraph<V> g = new UnweightedGraph(vertices, edgesList);

        //g.printEdges();
        SearchTree tree = g.dfs(rootVertex);


        if (tree.getNumberOfVerticesFound() == numberOfVertices && (vertices.size() != 1))  {
            isConnected = true;
        } return isConnected;
    }

    /** DONE
     * Find all connected components in this graph instance
     * @return List<List<Integer>> of all the vertices in a connected component
     */
    public List<List<Integer>> getConnectedComponents() {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<V> temp = new ArrayList<V>(vertices);

        int i = 0;
        while (!temp.isEmpty()) {

            while (temp.contains(i)) {

                List<Integer> addList = new ArrayList<>();
                addList.clear();

                SearchTree tree = dfs(i);
                addList = new ArrayList<>(tree.getSearchOrder());

                result.add(addList);
                temp.removeAll(addList);

            } i++;
        } return result;
    }

    /** DONE
     * Find a path between two vertices
     * @return List<Integer> that contains all the vertices in a path from u to v in order
     */
    public List<Integer> getPath(int origin, int destination) {

        //Check if index origin and destination are within the size of the vertices list
        if (origin > vertices.size() || destination > vertices.size() ||
            origin < 0 || destination < 0) {
            return null;
        }

        if (vertices.isEmpty()) {
            return null;
        }

        List<Integer> path = new ArrayList<>();
        SearchTree temp = bfs(origin);

        if (!temp.getSearchOrder().contains(destination)) { //destination is not in tree
            return null;
        }

        path = (List<Integer>) temp.getPath(destination);
        List<Integer> reverse = new ArrayList<>(path);
        Collections.reverse(reverse);

        return reverse;
    }

    /** DONE
     * Determines if there is a cycle in the graph
     * @return true if there is a cycle in this instance of MyGraph.
     */
    public boolean isCyclic() {

        if (vertices.size() == 0 || vertices.size() == 1 || vertices.size() == 2) {
            return false;
        }

        List<Integer> searchOrder = new ArrayList<Integer>();

        int[] parent = new int[vertices.size()];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1; // Initialize parent[i] to -1
        }
        // Mark visited vertices
        boolean[] isVisited = new boolean[vertices.size()];

        // Recursively search
        int u = 0; //index of 0

        return isCyclic(u,parent,searchOrder,isVisited);
    }

    /** DONE
     * Private method that recursively search each vertex until a cycle is found
     * @return true if a cycle is found
     */
    private boolean isCyclic(int u, int[] parent, List<Integer> searchOrder, boolean[] isVisited) {

        searchOrder.add(u);
        isVisited[u] = true;

        for (Edge e : neighbors.get(u)) { // Note that e.u is v

            if (!isVisited[e.u]) { // e.v is w in Listing 28.8
                parent[e.u] = u; // The parent of w is v
                isCyclic(e.u, parent, searchOrder, isVisited);
            } else if (isVisited[e.u] && parent[u] != e.u) {
                return true;
            }
        }
        return false;
    }

    /**
     * Outputs a cycle starting from u in the graph instance (if cycle exists)
     * @return returns a List that contains all the vertices in a cycle starting from u
     */
    public List<Integer> findCycle(int u) {

        //If there is not a cycle in this instance of MyGraph, method returns null
        if (!isCyclic()) {
            return null;
        }

        //Input u must be within the bounds of vertices list size
        if (u > vertices.size() || u < 0) {
            return null;
        }

        //Initialize the List of vertices, parent array, and isVisited array as done in DFS method
        List<Integer> verticesTemp = (List<Integer>) vertices;

        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1; // Initialize parent[i] to -1
        }

        boolean[] isVisited = new boolean[vertices.size()];

        //Initialize a List for the path to be returned
        List<Integer> pathReturned = new ArrayList<Integer>();

        //Make a copy of all the edges in the adjacency matrix
        List<List<Edge>> adjCopy = new ArrayList<>(neighbors);

        //work the same way as a iterative BFS; push them onto stack
        Stack<Integer> stack = new Stack<Integer>();

        stack.push(u);
        isVisited[u] = true;

        //while there are unvisited vertices
        while (!isVisited[u]) {

            stack.push(u);
            pathReturned.add(u);
            verticesTemp.remove(u);

            //while the Stack is not empty
            while (!stack.empty()) {

                int top = stack.peek();
                pathReturned.add(top);

                for (Edge e : adjCopy.get(top)) { // Note that e.u is v

                    if (!isVisited[e.u]) { // e.v is w in Listing 28.8

                        parent[e.u] = top; // The parent of w is v
                        stack.add(e.u);
                        isVisited[e.u] = true;
                        //isCyclic(e.u, parent, verticesTemp, isVisited);
                    } else if (isVisited[e.u] && parent[e.u] != u ) {
                        pathReturned.add(u);
                    }
                }
            }
        }
        return pathReturned;
    }

    public static void main(String[] args) {
        MyGraph<Integer> myGraph = new MyGraph<>();

        try {
            System.out.println("null MyGraph test of is Connected => " + myGraph.isGraphConnected(null));
            System.out.println("Empty MyGraph test of getConnectedComponents => " + myGraph.getConnectedComponents());
            MyGraph<Integer> myGraph0 = myGraph.readFile("test0.txt");
            System.out.println("Single vertex MyGraph test of is Connected => " + myGraph.isGraphConnected("test0.txt"));
            System.out.println("Single vertex MyGraph test of getConnectedComponents => " + myGraph0.getConnectedComponents());
            System.out.println("Single vertex MyGraph test of findCycle => " + myGraph0.findCycle(0));
            System.out.println();

            MyGraph<Integer> myGraph1 = myGraph.readFile("test1.txt");
            List<List<Integer>> components1 = myGraph1.getConnectedComponents();
            System.out.println("Graph of text1.txt");
            System.out.println("is Connected => " + myGraph.isGraphConnected("test1.txt"));
            System.out.println("Connected components: " + components1);
            System.out.println("A path from 0 to 0 is " + myGraph1.getPath(0, 0));
            System.out.println("A path from 0 to 1 is " + myGraph1.getPath(0, 1));
            System.out.println("isCyclic() returns " + myGraph1.isCyclic());
            System.out.println("findCycle(0) returns " + myGraph1.findCycle(0));
            myGraph1.printEdges();

            System.out.println();
            MyGraph<Integer> myGraph2 = myGraph.readFile("test2.txt");
            List<List<Integer>> components2 = myGraph2.getConnectedComponents();
            System.out.println("Graph of text2.txt");
            System.out.println("is Connected => " + myGraph.isGraphConnected("test2.txt"));
            System.out.println("Connected components: " + components2);
            System.out.println("A path from 0 to 4 is " + myGraph2.getPath(0, 4));
            System.out.println("isCyclic() returns " + myGraph2.isCyclic());
            System.out.println("findCycle(0) returns " + myGraph2.findCycle(0));
            System.out.println("printEdges() displays: ");
            myGraph2.printEdges();


            System.out.println();
            MyGraph<Integer> myGraph3 = myGraph.readFile("test3.txt");
            List<List<Integer>> components3 = myGraph3.getConnectedComponents();
            System.out.println("Graph of text3.txt");
            System.out.println("is Connected => " + myGraph.isGraphConnected("test3.txt"));
            System.out.println("Connected components: " + components3);
            System.out.println("A path from 0 to 4 is " + myGraph3.getPath(0, 4));
            System.out.println("A path from 5 to 4 is " + myGraph3.getPath(5, 4));
            System.out.println("isCyclic() returns " + myGraph3.isCyclic());
            System.out.println("findCycle(0) returns " + myGraph3.findCycle(0));
        }
        catch(Exception e) {
            System.out.println("Oops, something went wrong.");
            e.printStackTrace();
        }
    }
}

