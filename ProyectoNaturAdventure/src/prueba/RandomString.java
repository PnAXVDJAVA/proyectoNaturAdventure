package prueba;

import java.util.Random;

public class RandomString {
	
	public static void main(String[] args) {
		
		String chars = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for( int i = 0; i < 50; i++ ) {			
			String randomPwd = randomString( chars, 10 );
			System.out.println( randomPwd );
		}
		
	}
	
	public static String randomString(String chars, int length) {
		  Random rand = new Random();
		  StringBuilder buf = new StringBuilder();
		  for (int i=0; i<length; i++) {
		    buf.append(chars.charAt(rand.nextInt(chars.length())));
		  }
		  return buf.toString();
	}

}
