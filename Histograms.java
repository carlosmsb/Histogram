package week4;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import acm.program.ConsoleProgram;
import acm.util.ErrorException;

public class Histograms extends ConsoleProgram {
	
	public void run() {
	
		initHistogram();
		readScoresIntoHistogram();
		printHistogram();
	}

	public void initHistogram() {
		histogramArray = new int[11]; 
		for (int i = 0; i <= 10; i++) {
			histogramArray[i] = 0;
		}
	}


	public void readScoresIntoHistogram() {
		try {
			try (BufferedReader rd = new BufferedReader(new FileReader(MidtermScores))) {
				while (true) {
					String line = rd.readLine();
					if (line == null) break;
					int score = Integer.parseInt(line); 
					if (score < 0 || score > 100) {
						throw new ErrorException("That score is out of range"); 
					} else {
						int range = score / 10;
						histogramArray[range]++;
					}
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException ex) {

		}
	}


	public void printHistogram() {
		for (int range = 0; range <= 10; range++) {
			String label;
			switch (range) {
			case  0: 
				label = "00-09"; 
				break;
			case 10: 
				label = "  100"; 
				break;
			default:
				label = (10 * range) + "-" + (10 * range + 9);
				break; 
			}
			String stars = createStars(histogramArray[range]);
			println(label + ": " + stars);
		}
	}

	 
	public String createStars(int n) {
		String stars = "";
		for (int i = 0; i < n; i++) {
			stars += "*";
		}
		return stars;
	}

	public int[] histogramArray;
	

	public static final String MidtermScores = "MidtermScores.txt";
		
		
	
}