package algorithmTest;



public class FIFO {
	private static int findOldest(int age[]) {
		int oldest=0;
		for(int i=0;i<age.length;i++) {
			if(age[i]>age[oldest]) {
				oldest=i;
			}
		}
		return oldest;
	}
	private static void increaseAge(int age[]){
		for(int i=0;i<age.length;i++) {
			age[i]++;
		}
	}	
	public static int simulate(int length, int requests[], int initial []){
		int faults=0;
		int[] page = initial.clone();
//		for(int i=0;i<length;i++) {
//			page[i]=-1; //-1 means empty
//		}
		int[] age = new int[length];
		int temp;
		for(int i=0;i<requests.length;i++) {
			temp=Main.findOnPage(page, requests[i]);
			if(temp==-1) {
				temp=Main.findOnPage(page, -1);
				if(temp==-1) {
					temp=findOldest(age);
				}
				age[temp]=0;
				page[temp]=requests[i];
				faults++;
			}
			Main.displayArr(page);
			increaseAge(age);
		}
		return faults;
	}
}
