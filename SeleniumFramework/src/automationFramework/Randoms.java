package automationFramework;

import java.util.Random;

public class Randoms {
	private final static String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
	private final static String nameLexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static Random rand = new Random();

	/**
	 * Returns a random string using letters and numbers with a random length
	 * between the start and stop size. If the boolean is set to false, only
	 * text will be included in the string.
	 * 
	 * @param start
	 *            the min length the string can be
	 * @param stop
	 *            the max length the string should be
	 * @param num
	 *            true if numbers should be included in the string, false if the
	 *            string should only be letters
	 * @return the random string created
	 */
	public static String randomString(int start, int stop, boolean num) {
		StringBuilder builder = new StringBuilder();
		String lexi = lexicon;
		if (!num) {
			lexi = nameLexicon;
		}
		// builder.append(lexi.charAt(rand.nextInt(lexi.length())));
		int length = rand.nextInt(stop - start) + start;
		for (int i = 0; i <= length; i++) {
			builder.append(Character.toLowerCase(lexi.charAt(rand.nextInt(lexi.length()))));
		}
		return builder.toString();
	}

	/**
	 * Returns a random name of letters between 3 and 6 letters.
	 * 
	 * @return a camel case formatted string
	 */
	public static String randomName() {
		String temp = randomString(3, 6, false).toLowerCase();
		return temp.substring(0, 1).toUpperCase() + temp.substring(1);
	}
}
