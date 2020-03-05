import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) throws Exception {
		String s1= " this        is javatpoint  ";
		
	/*	String capitalizeWord = "";
		String word[]= s1.split("\\s");
		
		for(String Word:word) {
			String s= Word.substring(0, 1).toLowerCase();
			capitalizeWord+= s+Word.substring(1).toUpperCase()+" ";
		
			
			
		}*/
		//System.out.println(capitalizeWord);
		
		Integer i  = Integer.parseInt("176");
		System.out.println(String.valueOf(i));
		
		String st= "3/12/1998";
		Date d = new SimpleDateFormat("DD/MM/YYYY").parse(st);
		
		//System.out.println(d);
		
		
		//System.out.println(s1.substring(0, 8)+s1.substring(8+1));
		
		String noSpaceStr = s1.replaceAll("\\s", ""); // using built in method  
        System.out.println(noSpaceStr);  
        
        String noSpaceStr1 = s1.trim(); // using built in method  
        System.out.println(String.join("-", "vvvvvv","cccc","yutyu")); 
        
		
	}
	
}
