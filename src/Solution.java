import java.io.*;
import java.util.*;
import java.util.stream.*;

class Solution {

	static String decrypt(String word) {
		Integer wLen = word.length();
		if (wLen == 0) {
			return "";
		}
		int c = word.charAt(0);
		int acum = 0;
		int[] dcs = new int[wLen];
		c = 'a' + ((c - 'a') + 25) % 26;
		dcs[0] = c;
		if (wLen == 1) {
			return intArrayToString(dcs);
		}
		acum = dcs[0] + 1;
		c = word.charAt(1);
		dcs[1] = 'a' + ((c - 'a') + (26 - acum % 26)) % 26;
		if (wLen == 2) {
			return intArrayToString(dcs);
		}
		acum += dcs[1];
		for (int i = 2; i < wLen; i++) {
			c = word.charAt(i);
			dcs[i] = 'a' + ((c - 'a') + (26 - acum % 26)) % 26;
			acum += dcs[i];
		}
		return intArrayToString(dcs);
	}

	public static void main(String[] args) {
		String[][] tcs = { { "dnotq", "crime" }, { "flgxswdliefy", "encyclopedia" }, { "", "" }, { "a", "z" },
				{ "z", "y" } };
		for (String[] tc : tcs) {
			String eV = tc[1];
			String r = decrypt(tc[0]);
			if (!eV.equals(r)) {
				System.out.println("fallo, experado '" + eV + "' actual '" + r + "'");
			}
		}
	}

	static String intArrayToString(int asciiValues[]) {
		char[] chars = new char[asciiValues.length];
		for (int i = 0; i < asciiValues.length; i++) {
			chars[i] = (char) asciiValues[i];
		}
		return new String(chars);
	}

}

// a b c .. y z
// b c d .. z *
// 

// dc1 
// c2 = (dc1+1)+dc2-26-26 .. ascii('a')<=c2<=ascii('z')
// c2 = (dc1+1)+dc2-26*k
// 26*k+c2= (dc1+1)+dc2
// (dc1+1)+dc2=c2%26
// dc2=c2-(dc1+1)%26

// c3 = dc3+dc2-26*k
// 26*k+c3=dc3+dc2 => dc3+dc2=c3 mod(26)
// dc3=c3-dc2 mod(26)

// dc[i]=(c[i]-(dc[i-1]%26) - 26m  

// (dc1+1)+dc2//26=k
// (dc1+1)+dc2%26=c2