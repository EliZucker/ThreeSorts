import javax.swing.JOptionPane;

public class Main {

	/**
	 * The main method (first thing that is called)
	 * @param args
	 */
	public static void main(String[] args) {

		//create a new array of ints, and get the length from user input
		int numberOfValues = Integer.parseInt(JOptionPane.showInputDialog(null, "How many values would you like to enter?", "Three Sorting Algorithms", JOptionPane.QUESTION_MESSAGE));
		int[] originalList = new int[numberOfValues];

		//assign each element in the array to a value obtained from the user
		for (int i = 0; i < numberOfValues; i++) {
			originalList[i] = Integer.parseInt(JOptionPane.showInputDialog(null, "Value " + (i+1) + ": ", "Three Sorting Algorithms", JOptionPane.QUESTION_MESSAGE)); 
		}

		//check which sorting algorithm should be used with user input
		Object[] options = { "Bubble Sort", "Selection Sort", "Insertion Sort" };
		int option = JOptionPane.showOptionDialog(null, "Which sorting algorithm would you like to use?", "Three Sorting Algorithms", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, options, options[0]);

		//assign originalList to the corresponding sorting algorithm, while timing the task
		long startTime = System.nanoTime();
		switch(option) {
		case 0:
			originalList = bubbleSort(originalList);
			break;
		case 1:
			originalList = selectionSort(originalList);
			break;
		case 2:
			originalList = insertionSort(originalList);
			break;
		}
		long endTime = System.nanoTime();

		//create an output String with the ordered list
		String output = "";
		for(int i = 0; i < originalList.length; i++) {
			output+=originalList[i]+" ";
		}

		//display final output
		JOptionPane.showMessageDialog(null, "The sorted list is: " + output + "\n\n" + "That took "+ (endTime - startTime) + " milliseconds!", "Three Sorting Algorithms", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Sort a list using bubble sort
	 * Go through a list and swap elements if they are in the wrong order, until no swaps are necessary
	 * @param originalList the original list
	 * @return newList a sorted list going from low to high
	 */
	public static int[] bubbleSort(int[] originalList) {
		//clone newList onto a new list called newList
		int[] newList = originalList.clone();

		//infinitely loops until 0 swaps are done in an iteration
		while (true) {

			//assign swaps to 0 (because no swaps have been completed so far)
			int swaps = 0;

			//loop through the list and swap elements if the first on is bigger than the second
			for (int i = 1; i < originalList.length; i++) {
				if (newList[i-1] > newList[i]) {
					int temp = newList[i-1];
					newList[i-1] = newList[i];
					newList[i] = temp;
					swaps++;
				}	
			}

			//if no swaps have been completed, exit the infinite loop
			if (swaps == 0) {
				break;
			}
		}

		//return the new list
		return newList;
	}

	/**
	 * Sort a list using selection sort
	 * Loop through the list and each time find the next highest number,
	 * until there is a list going from low to high
	 * @param originalList the original list
	 * @return newList a new list going from low to high
	 */
	public static int[] selectionSort(int[] originalList) {

		//clone newList onto a new list called newList
		int[] newList = originalList.clone();

		//loop through the list length-1 times, to determine each value
		for (int i = 0; i < newList.length-1; i++) {
			//make the largest index originally i
			int largestIndex = i;

			//for each index, loop again to find the lowest unsorted value
			for (int j = i+1; j < newList.length; j++) {
				//if it is lower than the current index, change the current index to j
				if (newList[j] < newList[largestIndex]) {
					largestIndex = j;
				}
			}

			//Swap the current index with the index of the lowest value
			int temp = newList[i];
			newList[i] = newList[largestIndex];
			newList[largestIndex] = temp;
		}

		//return the newly sorted list
		return newList;
	}

	/**
	 * Sort the list from low to high using insertion sort
	 * Create a new list, and for each value insert in the correct position
	 * @param originalList the original unsorted list
	 * @return newList, the sorted list
	 */
	public static int[] insertionSort(int[] originalList) {

		//for the moment, make newList have length 1, containing the first element of originalList
		int[] newList = new int[1];
		newList[0] = originalList[0];

		//loop through each index (other than the first one)
		for (int i = 1; i < originalList.length; i++) {
			//if the value is less than the first, insert it into the front of the list
			if(originalList[i] < newList[0]) {
				newList = insert(newList, -1, originalList[i]);
				continue;
			}
			
			//otherwise, loop through the list and insert it after a value that is less than the value
			for(int j = newList.length-1; j >= 0; j--) {
				if (originalList[i] >= newList[j]) {
					newList = insert(newList, j, originalList[i]);
					break;
				}
			}
		}
		
		//return the newly sorted list
		return newList;
	}
	
	/**
	 * The insertion algorithm used in insertion sort
	 * @param originalList the list that an element is inserted into
	 * @param index the index BEFORE the new element will be
	 * @param x the element to be inserted
	 * @return newList, the list containing the inserted element
	 */
	public static int[] insert (int[] originalList, int index, int x) {
		
		//create a new list that is one element larger than the original length
		int[] newList = new int[originalList.length+1];

		//for each element of the original list, place it in the new list
		for (int i = 0; i < originalList.length; i++) {
			//insert the element into the array with the same index if it is before the inserted element
			if(i <= index) {
				newList[i] = originalList[i];
			} else {
			//otherwise, insert the element into index+1
				newList[i+1] = originalList[i];
			}
		}
		
		//place the element to be inserted into its index
		newList[index+1] = x;

		//return the list with the inserted element
		return newList;
	}
}
