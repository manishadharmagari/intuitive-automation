package automationFramework.utils;

import java.util.Random;

public class RandomUtils {

	private final static String LEXICON = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
	private final static String LEXICON_SPECIAL_CHARCATERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ !#$%&()*+-./:;<=>?@[\\]^_{|}~";
	private final static String NAME_LEXICON = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final static String NUMBER_LEXICON = "0123456789";
	private final static int DEFAULT_RANDOM_STRING_LENGTH = 5;

	/**
	 * returns a integer random value
	 *
	 * @param bound
	 *            the max value that random function can return
	 */
	public static int getRandomIntByBound(int bound) {
		return new Random().nextInt(bound);
	}

	/**
	 * Returns a random integer between start and stop.
	 *
	 * @param start
	 *            the min value to be returned
	 * @param end
	 *            the max value to be returned
	 * @return the random value generated
	 */
	public static int randBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}

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
		String lexi = LEXICON;
		if (!num) {
			lexi = NAME_LEXICON;
		}
		// builder.append(lexi.charAt(rand.nextInt(lexi.length())));
		int length = randBetween(start, stop);
		for (int i = 0; i <= length; i++) {
			builder.append(Character.toLowerCase(lexi.charAt(getRandomIntByBound(lexi.length()))));
		}
		return builder.toString();
	}

	/**
	 * returns a random string with only letters or letters and number or only
	 * numbers based on the parameters send to it
	 *
	 * @param length
	 *            length of the string to be returned
	 * @param onlyCharacters
	 *            set true if string should contain both numbers and letters
	 * @param onlyNumber
	 *            set true if string should only contain numbers
	 * @return the string of given length
	 */
	public static String getStringOfLength(int length, boolean onlyCharacters, boolean onlyNumber) {
		StringBuilder builder = new StringBuilder();
		String lexi = LEXICON;
		if (onlyCharacters) {
			lexi = NAME_LEXICON;
		}
		if (onlyNumber) {
			lexi = NUMBER_LEXICON;
		}

		for (int i = 0; i < length; i++) {
			builder.append(Character.toLowerCase(lexi.charAt(getRandomIntByBound(lexi.length()))));
		}
		return builder.toString();
	}

	public static String getStringWithSpecialCharacters(int length, boolean onlyCharacters, boolean onlyNumber,
			boolean includeSpecialCharacters) {
		if (!includeSpecialCharacters) {
			return getStringOfLength(length, onlyCharacters, onlyNumber);
		}
		StringBuilder builder = new StringBuilder();
		String lexi = LEXICON_SPECIAL_CHARCATERS;
		for (int i = 0; i <= length; i++) {
			builder.append(Character.toLowerCase(lexi.charAt(getRandomIntByBound(lexi.length()))));
		}
		return builder.toString();
	}

	/**
	 * Returns a random string using letters and numbers with a random length
	 * between the 0 and the integer provided. If the boolean is set to false,
	 * only text will be included in the string. This will return at least 1
	 * character even if a 0 is sent.
	 *
	 * @param size
	 *            the max length the string should be
	 * @param num
	 *            true if numbers should be included in the string, false if the
	 *            string should only be letters
	 * @return the random string created
	 */
	public static String randomString(int size, boolean num) {
		return randomString(0, size, num);
	}

	/**
	 * Returns a random string using letters and numbers with a random length
	 * between the 0 and 5. If the boolean is set to false, only text will be
	 * included in the string. This will return at least 1 character even if a 0
	 * is sent.
	 *
	 * @param num
	 *            true if numbers should be included in the string, false if the
	 *            string should only be letters
	 * @return the random string created
	 */
	public static String randomString(boolean num) {
		return randomString(DEFAULT_RANDOM_STRING_LENGTH, num);
	}

	/**
	 * Returns a random string using letters and numbers with a random length
	 * between the 0 and 5. The string will be aplhanumeric This will return at
	 * least 1 character even if a 0 is sent.
	 *
	 * @return the random string created
	 */
	public static String randomString() {
		return randomString(true);
	}
}
