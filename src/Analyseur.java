import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class Analyseur {

	private List<Object> lexique;
	
	public Analyseur(){
		lexique = new ArrayList<Object>();
	}
	
	public String scan(){
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		in.close();
		return line;
	}
	
	public void analyse_rec(String acc, String line){		
		
	}
}
