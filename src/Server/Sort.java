package Server;
 public class Sort {
	
	// list es un ARRAY
	 public void bubbleSort(int[] list) {  
	        int n = list.length;  
	        int temp = 0;  
	        for(int i=0; i < n; i++){  
	        	for(int j=1; j < (n-i); j++){  
	        		if(list[j-1] > list[j]){  
	        			//swap   
	                    temp = list[j-1];  
	                    list[j-1] = list[j];  
	                    list[j] = temp;  
	                    }  
	                          
	           }  
	       } 
	
	 }
	  // list es un ARRAY
	 public void sort(int list[]){ 
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
	 
	 
 }