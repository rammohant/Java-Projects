/* Tara Ram Mohan
CMSC 401
Programming Assignment 3
November  2020
 */

import java.util.*;
//only add the node to the PQ if its the shortest path to the node
public class cmsc401 {

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        // 1 - 1
        int source = 0;

        // Number of cities
        int numCities = sc.nextInt();
        int numHighways = sc.nextInt();

        // adjacency list representation of graph
        List<List<Node>> adjList = new ArrayList<List<Node>>();

        // Initialize adjacency list for every node in the graph
        for (int i = 0; i < numCities; i++) {
            List<Node> item = new ArrayList<Node>();
            adjList.add(item);
        }

        int cityNum[] = new int[numCities];
        cityNum[0] = 1;
        cityNum[1] = 2;

        int motelCost[] = new int[numCities];
        for (int i = 0; i < numCities; i++) {
            if (i < 2) {
                motelCost[i] = 0;
            } else {
                cityNum[i] = sc.nextInt();
                motelCost[i] = sc.nextInt();
            }
        }

        for (int i = 0; i < numHighways; i++) {
            int city1 = sc.nextInt() - 1;
            int city2 = sc.nextInt() - 1;
            int costGas = sc.nextInt();
            int costMotel1 = motelCost[city1];
            int costMotel2 = motelCost[city2];
            adjList.get(city1).add(new Node(city2, costGas, costMotel2));
            adjList.get(city2).add(new Node(city1, costGas, costMotel1));
        }

        Graph DPQ = new Graph(numCities);
        DPQ.Dijkstra(adjList, source);

        // Print the shortest path from Node 1 (Richmond) and Node 2 (LA)
        System.out.println(DPQ.cost[1]);
    }

    static class Graph {

        int cost[];
        Set<Integer> visited;
        PriorityQueue<Node> PQ;
        int numCities; // Number of vertices
        List<List<Node>> adjList;

        public Graph(int numCities) {
            this.numCities = numCities;
            cost = new int[numCities];
            visited = new HashSet<Integer>();
            PQ = new PriorityQueue<Node>(numCities, new Node());
        }

        public void Dijkstra(List<List<Node>> adjList, int origin) {
            this.adjList = adjList;

            for (int i = 0; i < numCities; i++) {
                cost[i] = Integer.MAX_VALUE;
            }

            // Add origin vertex (Richmond) to PriorityQueue
            PQ.add(new Node(origin, 0, 0));

            // Distance of vertex 1 to vertex 2
            cost[origin] = 0;
            while (visited.size() != numCities) {

                // Remove u from PriorityQueue
                int u = PQ.remove().node;

                // Add node to visited set
                visited.add(u);
                neighbours(u);
            }
        }

        // Look through all neighbours of the current node
        private void neighbours(int u) {
            int edgeDistance = -1;
            int newDistance = -1;
            int gas = 0;
            int motel = 0;

            // For loop through all neighbours of u
            for (int i = 0; i < adjList.get(u).size(); i++) {
                Node v = adjList.get(u).get(i);

                //  If current node is not in 'visited'
                if (!visited.contains(v.node)) {
                    gas = v.costGas;
                    motel = v.costMotel;
                    edgeDistance = gas + motel;
                    newDistance = cost[u] + edgeDistance;


                    // compare distances
                    if (newDistance < cost[v.node]) {
                        cost[v.node] = newDistance;

                        // Add the current vertex to the PriorityQueue
                        PQ.add(new Node(v.node, cost[v.node], v.costMotel));
                    }

                }
            }
        }
    }

    // Node class for each vertex
    static class Node implements Comparator<Node> {
        public int node;
        public int costGas;
        public int costMotel;

        public Node() {
        }

        public Node(int node, int costGas, int costMotel) {
            this.node = node;
            this.costGas = costGas;
            this.costMotel = costMotel;
        }

        @Override
        public int compare(Node node1, Node node2) {
            if ((node1.costGas + node1.costMotel) < (node2.costGas + node1.costMotel)) {
                return -1;
            }
            if ((node1.costGas + node1.costMotel) > (node2.costGas + node1.costMotel)) {
                return 1;
            }
            return 0;
        }
    }

}


