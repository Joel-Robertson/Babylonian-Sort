/* 
 * Name: Joel Robertson
 * Date: 10.16.18
 * Problem:  Use radix sorting to sort a base 60 number system.
 */

import java.util.*;
import java.util.Map.Entry;

public class BabylonianSort {

	// Create a Hashmap for the conversions
	static final HashMap<String, Integer> CONVERSIONS;
	static {
		CONVERSIONS = new HashMap<>();
		CONVERSIONS.put("0", 0);
		CONVERSIONS.put("1", 1);
		CONVERSIONS.put("2", 2);
		CONVERSIONS.put("3", 3);
		CONVERSIONS.put("4", 4);
		CONVERSIONS.put("5", 5);
		CONVERSIONS.put("6", 6);
		CONVERSIONS.put("7", 7);
		CONVERSIONS.put("8", 8);
		CONVERSIONS.put("9", 9);
		CONVERSIONS.put("a", 10);
		CONVERSIONS.put("b", 11);
		CONVERSIONS.put("c", 12);
		CONVERSIONS.put("d", 13);
		CONVERSIONS.put("e", 14);
		CONVERSIONS.put("f", 15);
		CONVERSIONS.put("g", 16);
		CONVERSIONS.put("h", 17);
		CONVERSIONS.put("i", 18);
		CONVERSIONS.put("j", 19);
		CONVERSIONS.put("k", 20);
		CONVERSIONS.put("l", 21);
		CONVERSIONS.put("m", 22);
		CONVERSIONS.put("n", 23);
		CONVERSIONS.put("o", 24);
		CONVERSIONS.put("p", 25);
		CONVERSIONS.put("q", 26);
		CONVERSIONS.put("r", 27);
		CONVERSIONS.put("s", 28);
		CONVERSIONS.put("t", 29);
		CONVERSIONS.put("u", 30);
		CONVERSIONS.put("v", 31);
		CONVERSIONS.put("w", 32);
		CONVERSIONS.put("x", 33);
		CONVERSIONS.put("y", 34);
		CONVERSIONS.put("z", 35);
		CONVERSIONS.put("A", 36);
		CONVERSIONS.put("B", 37);
		CONVERSIONS.put("C", 38);
		CONVERSIONS.put("D", 39);
		CONVERSIONS.put("E", 40);
		CONVERSIONS.put("F", 41);
		CONVERSIONS.put("G", 42);
		CONVERSIONS.put("H", 43);
		CONVERSIONS.put("I", 44);
		CONVERSIONS.put("J", 45);
		CONVERSIONS.put("K", 46);
		CONVERSIONS.put("L", 47);
		CONVERSIONS.put("M", 48);
		CONVERSIONS.put("N", 49);
		CONVERSIONS.put("O", 50);
		CONVERSIONS.put("P", 51);
		CONVERSIONS.put("Q", 52);
		CONVERSIONS.put("R", 53);
		CONVERSIONS.put("S", 54);
		CONVERSIONS.put("T", 55);
		CONVERSIONS.put("U", 56);
		CONVERSIONS.put("V", 57);
		CONVERSIONS.put("W", 58);
		CONVERSIONS.put("X", 59);
	}

	// Create a char array containing the sexagesimal representation for the Long.MAX_VALUE
	static final char[] LONG_SEXAGESIMAL = {
			'f', 'f', 'd', 'y', 'w', 'v', 'T', 'k', 'f', 'u', '7'
	};

	// Convert a decimal value to the sexagesimal representation
	public static String decimalToSexagesimal(long number) {

		System.out.println("Number = " + number);
		String sexagesimalRepresentation;
		long[] baseValues;
		String[] sexagesimalValues;
		int length, i = 0, count = 0, base = 60;

		// Determine the highest divisible power of 60
		while (number / Math.pow(base, count) >= 1)
		{
			count++;
		}
		
		if (number == 0)
		{
			count++;
		}

		// Populate an array with the numbers to be converted
		baseValues = new long[count];
		length = baseValues.length;
		while (count > 0)
		{
			baseValues[i] = number / (long) Math.pow(base, count - 1);
			number = number - (baseValues[i] * (long) Math.pow(base, count - 1));
			i++;
			count--;
		}

		// Convert each value in the base values array to the sexagesimal representation
		sexagesimalValues = new String[length];
		for (i = 0; i < length; i++)
		{
			sexagesimalValues[i] = conversionMap((int)baseValues[i]);
		}

		// Create the final base 60 String representation of the decimal
		StringBuilder builder = new StringBuilder();
		for(String s : sexagesimalValues)
		{
			builder.append(s);
		}
		sexagesimalRepresentation = builder.toString();

		return sexagesimalRepresentation;
	}

	// Convert a sexagesimal value to the decimal representation
	public static long sexagesimalToDecimal(String number) throws NumberFormatException, ArithmeticException {

		int i, length, base = 60;
		int[] decimalValues;
		long result = 0;

		// Check whether the String contains invalid characters
		if (isValidSexagesimalNumber(number) == false)
			throw new NumberFormatException("The String number passed to this function contains invalid characters.");

		// Check whether the String is too large to convert to a long
		if (numberTooLarge(number) == true)
			throw new ArithmeticException("The String number passed to this function is to large to store in a long."
					+ " Largest base 60 number acceptable: ffdywvTkfu7, or 9223372036854775807.");

		// Split the String into individual Strings of length 1
		String[] arraySplit = number.split("");
		length = arraySplit.length;

		// For each individual String, assign the proper decimal value to a new array
		decimalValues = new int[length];
		for (i = 0; i < length; i++)
		{
			decimalValues[i] = CONVERSIONS.get(arraySplit[i]);
		}

		i = 0;

		// Build the decimal representation
		while (length - 1 >= 0)
		{
			result += (decimalValues[i] * Math.pow(base, length - 1));
			length--;
			i++;
		}

		return result;
	}

	// Determine if the sexagesimal value is valid
	public static boolean isValidSexagesimalNumber(String number) {

		int result = 1, length = number.length(), i;
		int splitLength;

		// Split the String into individual Strings of length 1
		String[] arraySplit = number.split("");
		splitLength = arraySplit.length;
		
		if (splitLength > 1)
		{
			if (arraySplit[0].equals("0"))
			{
				return false;
			}
		}
		
		// For each individual String, check if it exists in the conversion map
		for (i = 0; i < length; i++)
		{
			if (!CONVERSIONS.containsKey(arraySplit[i]))
			{
				result = 0;
			}
		}

		if (result == 0)
			return false;
		else
			return true;
	}

	// Helper function to access the sexagesimal hash map
	public static String conversionMap(int number) {

		String result = null;

		for (Entry<String, Integer> entry : CONVERSIONS.entrySet())
		{
			if (entry.getValue().equals(number))
			{
				result = entry.getKey();
				break;
			}
		}

		return result;
	}

	// This function is used to determine if a sexagesimal number is too large for decimal representation
	public static boolean numberTooLarge(String number) {
		// Returns true if too large, false is not
		char[] chars = number.toCharArray();
		int length = number.length(), i;

		if (length > 11)
			return true;
		else if (length < 11)
			return false;
		else
		{
			for (i = 0; i < length; i++)
			{
				if (chars[i] > LONG_SEXAGESIMAL[i])
				{
					return true;
				}
				else if (chars[i] < LONG_SEXAGESIMAL[i])
				{
					return false;
				}
				i++;
			}
			return false;
		}
	}

	// Helper function to determine the longest String in an array
	public static int getLongestString(String[] numbers) {

		int max = numbers[0].length();
		int i, length = numbers.length;

		for(i = 1; i < length; i++)
		{
			if(numbers[i].length() > max)
			{
				max = numbers[i].length();
			}
		}

		return max;
	}

	// Pads a string with leading zeros
	public static String padZeros(String s, int max)
	{
		if (s.length() >= max)
			return s;
		else
			return String.format("%0" + (max - s.length()) + "d%s", 0, s);
	}
	
	// Removes all leading zeros from a string
	public static String removeZeros(String s)
	{
		return s.replaceFirst("^0+(?!$)", "");
	}

	// Sorts a String array of sexagesimal values
	public static void babylonianSort(String[] numbers) throws NumberFormatException {

		int i, max, length = numbers.length, number, splitLength, j = 0, count = 1;
		String[] arraySplit, expIndex, copy;
		int[] bucket;
		String arrayString, key;
		StringBuilder builder;

		for (i = 0; i < numbers.length; i++)
		{
			// Check whether the String contains invalid characters
			if (isValidSexagesimalNumber(numbers[i]) == false)
				throw new NumberFormatException("The String number passed to this function contains invalid characters (-,!,?,<,>), or leading zeros.");
		}

		// set the max variable to the longest string length
		max = getLongestString(numbers);

		// pad each string with zeros, used in the radix sort so that each string is the same length
		for ( i = 0; i < length; i++)
		{
			numbers[i] = padZeros(numbers[i], max);
		}

		// begin radix sorting
		while (count <= max)
		{
			bucket = new int[length];

			// create buckets for each letter in the string
			for (i = 0; i < length; i ++)
			{
				arraySplit = numbers[i].split("");
				splitLength = arraySplit.length;

				number = CONVERSIONS.get(arraySplit[splitLength - count]);
				bucket[i] = number;
			}

			Arrays.sort(bucket);
			arrayString = Arrays.toString(bucket);
			expIndex = arrayString.substring(1, arrayString.length() - 1).split(", ");
			copy = new String[length];
			builder = new StringBuilder();
			int result;

			for (i = 0; i < length; i++)
			{
				arraySplit = numbers[i].split("");
				splitLength = arraySplit.length;
				key = arraySplit[splitLength - count];

				for(j = 0; j < length; j++)
				{
					result = Integer.parseInt(expIndex[j]);
					if (CONVERSIONS.get(key).equals(result) && copy[j] == null)
					{
						builder.setLength(0);
						for(String s : arraySplit)
						{
							builder.append(s);
						}

						copy[j] = builder.toString();
						break;
					}
				}
			}
			
			for (i = 0; i < copy.length; i++)
			{
				numbers[i] = copy[i];
			}
			
			count++;
		}

		// remove any leading zeros
		for ( i = 0; i < length; i++)
		{
			numbers[i] = removeZeros(numbers[i]);
		}
	}

}	// end class
