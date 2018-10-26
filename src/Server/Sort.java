package Server;

public class Sort {
	
	 void selectionsort(int arr[]){
	        int n = arr.length;
	        // One by one move boundary of unsorted subarray
	        for (int i = 0; i < n-1; i++){
	            // Find the minimum element in unsorted array
	            int min_idx = i;
	            for (int j = i+1; j < n; j++)
	                if (arr[j] < arr[min_idx])
	                    min_idx = j;
	            // Swap the found minimum element with the first
	            // element
	            int temp = arr[min_idx];
	            arr[min_idx] = arr[i];
	            arr[i] = temp;
	        }
	    }
	
	 public void insertionSort(int list[]){ 
		 int n = list.length; 
	     for (int i=1; i<n; ++i){ 
	    	 int temp = list[i]; 
	         int j = i-1; 
	         while (j>=0 && list[j] > temp){ 
	        	 list[j+1] = list[j]; 
	             j = j-1; 
	            } 
	         list[j+1] = temp; 
	    } 
	} 
	 
	
			public int[] quickSort(int[] arr) {
				int low = 0;
				int high = arr.length - 1;
				return auxquickSort(arr, low, high);
			}
		 
			private int[] auxquickSort(int[] arr, int low, int high) {
				if (arr == null || arr.length == 0) {
					return arr;
					
				}if (low >= high) {
					return arr;
				}
				// pick the pivot
				int middle = low + (high - low) / 2;
				int pivot = arr[middle];
		 
				// make left < pivot and right > pivot
				int i = low, j = high;
				while (i <= j) {
					
					while (arr[i] < pivot) {
						i++;
						
					}while (arr[j] > pivot) {
						j--;
					}if (i <= j) {
						int temp = arr[i];
						arr[i] = arr[j];
						arr[j] = temp;
						i++;
						j--;
					}
				}
				if (low < j)
					auxquickSort(arr, low, j);
		 
				if (high > i)
					auxquickSort(arr, i, high);
			}
	// Function to insert nodes in level order 
	public Node insertLevelOrder(int[] arr, Node root,int i){ 
		  // Base case for recursion 
		  if (i < arr.length) { 
		      Node temp = new Node(arr[i]); 
		      root = temp; 
		  
		      // insert left child 
		      root.left = insertLevelOrder(arr, root.left,2 * i + 1); 
		      // insert right child 
		      root.right = insertLevelOrder(arr, root.right,2 * i + 2); 
		    } 
		  return root; 
	} 
	 

}
