package br.bemobi.task.util;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class ToConvertUrlIn {

	private static int toBeConvertedId;
	private static String convertedString = "";
	private static long mod = 0;
	private static long multiplier = 0;
	private static Boolean determinedTheLength = Boolean.FALSE;

	public static String convertedToString(String prUrlOriginla) {
		String[] elements = {
				"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o",
				"p","q","r","s","t","u","v","w","x","y","z","1","2","3","4",
				"5","6","7","8","9","0","A","B","C","D","E","F","G","H","I",
				"J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X",
				"Y","Z"
		};

		int numOfChar= elements.length;

		if(prUrlOriginla != null){	
			toBeConvertedId = ((Hashing.murmur3_32().hashString(prUrlOriginla, StandardCharsets.UTF_8).asInt())*(-1));

			if(toBeConvertedId < numOfChar + 1 && toBeConvertedId > 0){
				convertedString = elements[(toBeConvertedId-1)];
			}else
				if(toBeConvertedId > numOfChar){
					for(int j = 6; j >= 0; j--){
						multiplier = (long) (toBeConvertedId/Math.pow(numOfChar, j));

						if(multiplier > 0 && toBeConvertedId >= numOfChar){
							convertedString += elements[(int) multiplier];
							determinedTheLength = Boolean.TRUE;
						}

						else
							if(determinedTheLength && multiplier == 0){
								convertedString += elements[0];
							}
							else
								if(toBeConvertedId < numOfChar){                    
									convertedString += elements[(int) mod];
								}

						mod= (int) (toBeConvertedId % Math.pow(numOfChar, j));
						toBeConvertedId = (int) mod;                
					}
				}
		}
		return convertedString;
	}
	
}