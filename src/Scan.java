import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Scan{
	public List<String> symboles = new ArrayList<String>();
    public List<String> vocabulairet = new ArrayList<String>();
    public List<String> vocabulairent = new ArrayList<String>();

	public void scan(){
		System.out.println("Entrez la règle à scanner");
		Scanner in = new Scanner(System.in);
		String rule; 
		//while(in.hasNextLine()){
			rule = in.nextLine();
			scan_rec(rule,"");
		//}
		in.close();		
	}
	
	private void scan_rec(String rule, String acc){
		if(!rule.isEmpty()){
			char first = rule.charAt(0);
			if(first == '='){
				if(acc.equals("::")){
					acc = "";
					symboles.add("::=");
					scan_rec(rule.substring(1), acc);
				}else{
					System.out.println("Symbole non reconnu");
                    scan_rec(rule.substring(1), acc);
				}
			}
			else if(first == '+' || first == '[' || first == ']' || first == '.' || first == '(' || first == ')'){
				if(!acc.isEmpty())
					vocabulairent.add(acc);
				acc = "";
				symboles.add(""+first);
				scan_rec(rule.substring(1), acc);
			}
			else if(first == ':'){
                if(!acc.isEmpty() && !acc.equals(":")) {
                    vocabulairent.add(acc);
                    acc="";
                }
				acc += first;
				scan_rec(rule.substring(1), acc);
			}
			//Si c'est pas un symbole
            else if(first =='"') {
                if(acc.isEmpty())
                    scan_rec(rule.substring(1), acc);
                else {
                    vocabulairet.add(acc);
                    acc="";
                    scan_rec(rule.substring(1), acc);
                }

            }
			else {
				acc += first;
				scan_rec(rule.substring(1), acc);
			}
		}
		else {
			vocabulairent.add(acc);
		}
		
	}
	
	public static void main(String[] args){
		Scan sc = new Scan();
		sc.scan();
		
		System.out.println("Symboles : ");
		for(String s : sc.symboles)
			System.out.println(s);
		System.out.println("Vocabulaire non terminal : ");
		for(String st : sc.vocabulairent)
			System.out.println(st);
        System.out.println("Vocabulaire terminal : ");
        for(String snt : sc.vocabulairet)
            System.out.println(snt);
	}
}
