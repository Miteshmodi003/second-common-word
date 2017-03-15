package macbeth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MacBeth {

	public static void main(String[] args) throws FileNotFoundException {

		File file = new File("macbeth.txt");
		BufferedReader bR = null;
		bR = new BufferedReader(new FileReader(file));
		String inputLn = null;
		Map<String, Integer> mMapping = new HashMap<>();

		try {
			while ((inputLn = bR.readLine()) != null) {
				String[] words = inputLn.split("[ \n\t\r.,;:!?(){}]");

				for (int counter = 0; counter < words.length; counter++) {
					if (words[counter].length() > 4) {
						String k = words[counter].toLowerCase();

						if (mMapping.get(k) == null) {
							mMapping.put(k, 1);

						} else {
							int val = mMapping.get(k).intValue();
							val++;
							mMapping.put(k, val);
						}
					}
				}
			}

			Set<Map.Entry<String, Integer>> entrySet = mMapping.entrySet();
			System.out.println("First Two Most Common Words" + "\t\t" + "Num of their Occurances");
			for (Map.Entry<String, Integer> entry : entrySet) {
				if (entry.getValue() < 278 && entry.getValue() > 100) {
					System.out.println(entry.getKey() + "\t\t\t\t\t\t" + entry.getValue());
				}
			}
		}
		catch (IOException error) {
			System.out.println("File Not Found");
		}
	}

	public static List<String> findMaxOccurance(Map<String, Integer> map, int n) {
		List<MyComparable> l = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : map.entrySet())

			l.add(new MyComparable(entry.getKey(), entry.getValue()));

		Collections.sort(l);
		List<String> list = new ArrayList<>();
		for (MyComparable wrd : l.subList(0, n))
			list.add(wrd.strInFile + " : " + wrd.numOccurrance);
		return list;
	}
}

class MyComparable implements Comparable<MyComparable> {
	public String strInFile;
	public int numOccurrance;

	public MyComparable(String strInFile, int numOccurrance) {
		super();
		this.strInFile = strInFile;
		this.numOccurrance = numOccurrance;
	}

	public int compare(MyComparable m, MyComparable m1) {
		return m1.numOccurrance - m.numOccurrance;
	}

	@Override
	public boolean equals(Object objVar) {
		if (this == objVar)
			return true;
		if (objVar == null)
			return false;
		if (getClass() != objVar.getClass())
			return false;
		MyComparable other = (MyComparable) objVar;
		if (numOccurrance != other.numOccurrance)
			return false;
		if (strInFile == null) {
			if (other.strInFile != null)
				return false;
		} else if (!strInFile.equals(other.strInFile))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int uInt = 19;
		int filterRes = 9;
		filterRes = uInt * filterRes + numOccurrance;
		filterRes = uInt * filterRes + ((strInFile == null) ? 0 : strInFile.hashCode());
		return filterRes;
	}

	@Override
	public int compareTo(MyComparable par) {
		int leverageCompare = Integer.compare(par.numOccurrance, this.numOccurrance);
		return leverageCompare != 0 ? leverageCompare : strInFile.compareTo(par.strInFile);
	}

}