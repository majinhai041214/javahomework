public class Node {
    public String str;
    Node next;

    public Node() {
        str = null;
        next = null;
    }
    public Node(String word) {
        str = word;
        next = null;
    }

    public void Node(String str,Node nd){
        this.str=str;
        next=nd;
    }
    public String getStr(){
        return str;
    }
}
