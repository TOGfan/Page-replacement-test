package algorithmTest;
//optimal
public class OPT {
	private static int findNextUse(int requests[],int step, int item) {
		int nextUse=0;
		for(int i=step;i<requests.length;i++) {
			if(requests[i]==item) {
				return nextUse;
			}
			nextUse++;
		}
		return nextUse;
	}
	private static int findOptimal(int requests[], int step,int page[]) {
		int optimal=findNextUse(requests,step,page[0]);
		int result=0;
		int temp;
		for(int i=1;i<page.length;i++) {
			temp=findNextUse(requests,step,page[i]);
			if(temp>optimal) {
				optimal=temp;
				result=i;
			}
		}
		return result;
	}
	public static int simulate(int length, int requests[], int initial []){
		int faults=0;
		int[] page = initial.clone();
//		for(int i=0;i<length;i++) {
//			page[i]=-1; //-1 means empty
//		}
		int temp;
		for(int i=0;i<requests.length;i++) {
			temp=Main.findOnPage(page, requests[i]);
			if(temp==-1) {
				temp=Main.findOnPage(page, -1);
				if(temp==-1) {
					temp=findOptimal(requests,i,page);
				}
				page[temp]=requests[i];
				faults++;
			}
			Main.displayArr(page);
		}
		return faults;
	}
}
