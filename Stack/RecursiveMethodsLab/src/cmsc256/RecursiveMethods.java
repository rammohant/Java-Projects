package cmsc256;
 import java.util.Arrays;

/*
*	William Coleman
*	Tara Ram Mohan
*/

public class RecursiveMethods {

	/*
	 * @returns a String of character, ch. The length is determined
	 * 			by the second parameter, length.
	 */
	public static String buildStringOfCharacters(char ch, int length) {
		if (length == 0) {
			return "";
		}
		return ch + buildStringOfCharacters(ch,length-1);
	}
//I seriousyl

	/*
	 * returns an int array that has the elements in reverse order
	 * 			of the parameter array, nums. 
	 * Process this recursively beginning with the last element.
	 */
	public static int[] reverseNumArray(int[] nums, int backIndex) {
		int length = nums.length;
		if (backIndex <= length/2) {
			return nums;
		}
		int swapIndex = length - 1 - backIndex;
		int temp = nums[swapIndex];
		nums[swapIndex] = nums[backIndex];
		nums[backIndex] = temp;

		//WHy the fuck does omar have to be ssuch an asshoel, I literally never did anythign that cuold vempossibyl itnerefered wih this
		//life and he just literally bullys me
		//I dont even mnwot the fuck I want to do with my life I jstu want ot overe all the chaos tat in our owkr

		return reverseNumArray(nums, backIndex -1 );
	}
	
	/*
	 * returns true if the int array parameter i2s sorted from smallest
	 * 			to largest, false otherwise.
	 * Process this recursively beginning with the first element.
	 */
	public static boolean isSmallestToLargest(int[] values, int firstIndex) {
		int length = values.length;
		if (firstIndex == length - 1) {
			return true;
		}
		return (values[firstIndex] <= values[firstIndex+1]) && (isSmallestToLargest(values, firstIndex+1));
	}
	
	/* 
	 * @returns true if the parameter String, str is a palindrome
	 * 			false otherwise
	 */
	public static boolean isPalindrome(String str, int begin, int end) {
		int length = str.length();
		if (end <= length/2) {
			return true;
		}
		return (str.charAt(begin) == str.charAt(end)) && (isPalindrome(str,begin+1, end-1));
	}

	public static void main(String[] args) {

		int[] nums = new int[] {1,2,3,4,5};
		String palindromeFalse = "Tarar";
		String palindromeTrue = "racecar";
		String palindromeLong = "gohangasalamiimalasagnahog";


		System.out.println(buildStringOfCharacters('t',4));
		System.out.println(Arrays.toString(reverseNumArray(nums, 4)));
		System.out.println(isSmallestToLargest(nums, 0));
		System.out.println(isPalindrome(palindromeFalse,0,palindromeFalse.length() - 1));
		System.out.println(isPalindrome(palindromeTrue,0,palindromeTrue.length() - 1));
		System.out.println(isPalindrome(palindromeLong,0,palindromeLong.length() - 1));

	}
}