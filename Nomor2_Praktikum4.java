import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
/**
* Graph represented by an adjacency list.
*
* Reference: Introduction to Algorithms - CLRS.
*
* @author
* @since: 26/02/2011
*/
public class AdjacencyList
{
 public enum NodeColour { WHITE, GRAY, BLACK }

 public static class Node
 {
 int data;
 int distance;
 NodeColour colour;

 public Node(int data)
         
{
 this.data = data;
 }

     /**
      *
      * @return
      */
     @Override
 public String toString()
 {
 return "(" + data + ",d=" + distance + ")";
 }
 }

 Map<Node, List<Node>> nodes;
 public AdjacencyList()
 {
 nodes = new HashMap<>();
 }

 public void addEdge(Node n1, Node n2)
 {
 if (nodes.containsKey(n1)) {
 nodes.get(n1).add(n2);
 } else {
 ArrayList<Node> list = new ArrayList<>();
 list.add(n2);
 nodes.put(n1, list);
 }
 }

 public void bfs(Node s)
 {
 Set<Node> keys = nodes.keySet();
 for (Node u : keys) {
 if (u != s) {
 u.colour = NodeColour.WHITE;
 u.distance = Integer.MAX_VALUE;
 }
 }
 s.colour = NodeColour.GRAY;
 s.distance = 0;
 Queue<Node> q = new ArrayDeque<>();
 q.add(s);
 while (!q.isEmpty()) {
 Node u = q.remove();
 List<Node> adj_u = nodes.get(u);
 if (adj_u != null) {
 for (Node v : adj_u) {
 if(v.colour == NodeColour.WHITE) {
 v.colour = NodeColour.GRAY;
v.distance = u.distance + 1;
 q.add(v);
 }
 }
 }
 u.colour = NodeColour.BLACK;
 System.out.print(u + " ");
 }
 }
 public static void main(String[] args)
         
 {
 AdjacencyList graph = new AdjacencyList();
 Node n0 = new Node(0);
 Node n1 = new Node(1);
 Node n2 = new Node(2);
 Node n3 = new Node(3);
 Node n4 = new Node(4);
 Node n5 = new Node(5);
 Node n6 = new Node(6);

 graph.addEdge(n1, n2);

  graph.addEdge(n2, n5);
 graph.addEdge(n2, n6);
 graph.addEdge(n2, n0);

 graph.addEdge(n0, n1);
 graph.addEdge(n0, n2);

 graph.addEdge(n1, n0);
 graph.addEdge(n1, n3);
 graph.addEdge(n1, n4);

 graph.addEdge(n1, n0);
 graph.addEdge(n1, n3);
 graph.addEdge(n1, n4);
 
 graph.addEdge(n3, n1);
 graph.addEdge(n3, n4);

 graph.addEdge(n4, n1);
 graph.addEdge(n4, n3);
 
 graph.addEdge(n5, n2);
 graph.addEdge(n5, n6);
 
 graph.addEdge(n6, n2);
 graph.addEdge(n6, n5);
 
 graph.bfs(n0);
 }
}
