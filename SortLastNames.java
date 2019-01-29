import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SortLastNames {
	/**
	 * Sort names by last name.
	 * @param  al ArrayList of names.
	 * @return Sorted ArrayList of names.
	 */
	public ArrayList<String> sortLast(ArrayList<String> al) {
		Collections.sort(al, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				String[] split1 = o1.split(" ");
				String[] split2 = o2.split(" ");
				String lastName1 = split1[1];
				String lastName2 = split2[1];
				if (lastName1.compareTo(lastName2) > 0) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		return al;
	}

	/**
	 * Write sorted ArrayList of names to file.
	 * @param  al  Sorted ArrayList of names.
	 * @return void
	 */
	public static void writeFile(ArrayList<String> al) throws IOException
	{
		String path = System.getProperty("user.dir");
		String SEPARATOR = System.getProperty("file.separator");
		FileWriter fileWriter = new FileWriter(path + SEPARATOR + "sorted-names-list.txt");
		PrintWriter printWriter = new PrintWriter(fileWriter);
		for (String str: al) {
			printWriter.println(str);
		}
		printWriter.close();
	}

	/**
	 * Read file that contains unsorted names.
	 * @param  args String of filename.
	 * @return ArrayList of names (unsorted).
	 */
	public ArrayList<String> readFile(String args)
	{
		ArrayList<String> listName = new ArrayList<String>();
		try {
			String path = System.getProperty("user.dir");
			String SEPARATOR = System.getProperty("file.separator");

			Scanner scanner = new Scanner(new File(path + SEPARATOR + args));

			while (scanner.hasNextLine()) {
                listName.add(scanner.nextLine());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return listName;
	}

	public static void main(String[] args) {
		ArrayList<String> unsortedNames;
		ArrayList<String> sortedNames;

		SortLastNames obj = new SortLastNames();

		// call readfile method and pass the filename from arguments.
		unsortedNames = obj.readFile(args[0]);
		System.out.println("Sorted using Last Name");

		// Call sortLast method to sort the names in that file.
		sortedNames = obj.sortLast(unsortedNames);

		// Write sorted names to the file
		try {
			SortLastNames.writeFile(sortedNames);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Print out sorted names to the screen.
		for (int i = 0; i < sortedNames.size(); i++) {
			System.out.println(sortedNames.get(i));
		}
	}
}
