import java.util.ArrayDeque;
import java.util.Deque;

public class GPLBuilder {
    private Deque<Node> pile;
    private Grammar grammar;

    public GPLBuilder(){
        pile = new ArrayDeque<Node>();
        grammar = new Grammar();
    }

    public void goAction(int action, Node sc){
        Node p1,p2;
        switch (action){
            case 1 : p1 = pile.pop();
                     p2 = pile.pop();
                     grammar.rules.add(((Atom)p2).getCode(),p1);
                     break;
            case 2 : pile.push(sc);
                     break;
            case 3 : p1 = pile.pop();
                     p2 = pile.pop();
                     pile.push(new Union(p1,p2));
                     break;
            case 4 : p1 = pile.pop();
                     p2 = pile.pop();
                     pile.push(new Conc(p1,p2));
                     break;
            case 5 : pile.push(sc);
                     break;
            case 6 : p1 = pile.pop();
                     pile.push(new Star(p1));
                     break;
            case 7 : p1 = pile.pop();
                     pile.push(new Un(p1));
                     break;
        }
    }

    public Grammar getGrammar(){
        return grammar;
    }
}
