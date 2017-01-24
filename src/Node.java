public abstract class Node{

    private Node left;
    private Node right;

    public Node(Node left, Node right){
        this.left = left;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public abstract String afficher(String tmp);
}
