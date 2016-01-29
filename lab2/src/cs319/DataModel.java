package cs319;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings({ "rawtypes", "serial" })
public class DataModel extends javax.swing.AbstractListModel {

	ArrayList<String> companies;

	public DataModel() throws FileNotFoundException {
		companies = new ArrayList<String>();
		File f = new File("companies.txt");
		Scanner s = new Scanner(f);
		while (s.hasNextLine()) {
			companies.add(s.nextLine());
		}
		s.close();
	}

	public ArrayList<String> getListData() {
		return companies;
	}

	@Override
	public Object getElementAt(int index) {
		return companies.get(index);
	}

	public void addElement(String s) throws IOException {
		FileWriter fw = new FileWriter("companies.txt", true);
		try {
			fw.write("\n" + s);
		} catch (IOException ioe) {
			System.err.println("IOException: " + ioe.getMessage());
		}
		companies.add(s);
		fw.close();
		fireIntervalAdded(this, getSize(), getSize() + 1);

	}

	public void removeElement(Object o) throws IOException {
		int index = companies.indexOf((String) o);
		String lineToRemove = (String) o;
		
		File inFile = new File("companies.txt");

		// Construct the new file that will later be renamed to the original
		// filename.
		File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

		BufferedReader br = new BufferedReader(new FileReader(inFile));
		PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

		String line = null;

		// Read from the original file and write to the new
		// unless content matches data to be removed.
		while ((line = br.readLine()) != null) {

			if (!line.trim().equals(lineToRemove)) {

				pw.println(line);
				pw.flush();
			}
		}
		pw.close();
		br.close();

		// Delete the original file
		if (!inFile.delete()) {
			System.out.println("Could not delete file");
			return;
		}

		// Rename the new file to the filename the original file had.
		if (!tempFile.renameTo(inFile))
			System.out.println("Could not rename file");

		companies.remove(index);
		fireIntervalRemoved(this, index, index);

	}

	@Override
	public int getSize() {
		return companies.size();
	}

}
