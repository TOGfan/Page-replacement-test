package algorithmTest;
//least recently used

public class LRU {
	
	private static int findLRU(int age[]) {
		int oldest=0;
		for(int i=0;i<age.length;i++) {
			if(age[i]>age[oldest]) {
				oldest=i;
			}
		}
		return oldest;
	}
	private static void increaseTime(int age[]){
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
		int[] lastUse = new int[length];
		int temp;
		for(int i=0;i<requests.length;i++) {
			temp=Main.findOnPage(page, requests[i]);
			if(temp==-1) {
				temp=Main.findOnPage(page, -1);
				if(temp==-1) {
					temp=findLRU(lastUse);
				}
				page[temp]=requests[i];
				faults++;
			}
			lastUse[temp]=0;
			Main.displayArr(page);
			increaseTime(lastUse);
		}
		return faults;
	}
}
