package algorithmTest;

public class Proportional {
	public  int[] allocate(int processSizes[], int frameNum) {
		int[] allocatedFrames = new int[processSizes.length];
		int totalSize=0;
		for(int i=0;i<processSizes.length;i++) {
			totalSize+=processSizes[i];
		}
		for(int i=0;i<processSizes.length;i++) {
			allocatedFrames[i]=frameNum*processSizes[i]/totalSize;
		}
		return allocatedFrames;
	}
}
