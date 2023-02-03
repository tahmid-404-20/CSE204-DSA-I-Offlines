import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

enum COLOR {WHITE,GREY,BLACK}
class Node {
    public int nodeNo;
    public COLOR color;
    public Node parent;
    public int distance;
    public boolean isLadderStart;
    public boolean isLadderEnd;
    public boolean isSnakeStart;
    public boolean isSnakeEnd;
    public Node pseudoParent;
    public List<Integer> Adj;

    public Node(int nodeNo) {
        this.nodeNo = nodeNo;
        this.color = COLOR.WHITE;
        this.parent = null;
        this.distance = -1;
        isLadderStart = false;
        isLadderEnd = false;
        isSnakeStart = false;
        isSnakeEnd = false;
        pseudoParent = null;
        Adj = new ArrayList<>();
    }

    public void setColor(COLOR color) {
        this.color = color;
    }
}

public class Graph {

    Node[] nodes;
    int nNodes;

    public Graph(int nNodes, int nDice, Point[] snakes, Point[] ladders) {
        this.nNodes = nNodes;

        nodes = new Node[nNodes+1];
        for(int i=1;i<=nNodes;i++)
        {
            nodes[i] = new Node(i);
        }

        buildConnection(nDice,snakes,ladders);
    }

    private void resetColors()
    {
        for(int i=1;i<=nNodes;i++)
            nodes[i].color = COLOR.WHITE;
    }

    private void buildConnection(int nDice, Point[] snakes, Point[] ladders) {

        for (Point p:ladders)
        {
            nodes[p.x].Adj.add(p.y);
            nodes[p.x].isLadderStart = true;
        }

        for (Point p:snakes){
            nodes[p.x].Adj.add(p.y);
            nodes[p.x].isSnakeStart = true;
        }

        for(int i=1;i<=nNodes;i++){
            if(nodes[i].isSnakeStart || nodes[i].isLadderStart)
            {
                continue;
            }

            for(int j=1;j<=nDice;j++){
                if(i+j>nNodes)
                    break;

                nodes[i].Adj.add(i+j);
            }
        }
    }

    private void BFS() {
        resetColors();

        nodes[1].color = COLOR.WHITE;
        nodes[1].parent = null;
        nodes[1].distance = 0;

        Queue<Node> queue = new Queue<>();
        queue.enqueue(nodes[1]);

        while(!queue.isEmpty()){
            Node u = queue.dequeue();

            for(Integer i:u.Adj){
                if(nodes[i].color == COLOR.WHITE) {
                    nodes[i].color = COLOR.GREY;
                    nodes[i].distance = u.distance + 1;
                    nodes[i].parent = u;
                    queue.enqueue(nodes[i]);
                }
            }
            u.color = COLOR.BLACK;
        }
    }

    private int numberOfRolls(Node node, int count) {
        if(node.nodeNo == 1)
        {
            return count;
        }
        if(node.parent == null)
        {
            return -1;
        }

        else if(node.isLadderStart || node.isSnakeStart) {
            return numberOfRolls(node.parent,count);
        }
        else
        {
            return numberOfRolls(node.parent,count + 1);
        }
    }

    private void findUnReachableNodes()
    {
        boolean foundAny = false;
        for(int i=2;i<=nNodes;i++)
        {
            if(nodes[i].parent == null){
                System.out.print(i+ " ");

                if (foundAny) {
                    continue;
                }
                foundAny = true;
            }
        }

        if(!foundAny){
            System.out.print("All reachable");
        }
        System.out.println();
    }

    private void printShortestPath(Node node){
        if(node.nodeNo == 1)
        {
            System.out.print("1 -> ");
            return;
        }


        if(node.parent == null){
            System.out.println("No solution");
        }
        else {
            printShortestPath(node.parent);

            if(node.nodeNo == nNodes)
                System.out.println(node.nodeNo);
            else
                System.out.print(node.nodeNo + " -> ");
        }
    }



    public void Simulate() {
        BFS();
        System.out.println(numberOfRolls(nodes[nNodes],0));
        printShortestPath(nodes[nNodes]);
        findUnReachableNodes();

    }


}
