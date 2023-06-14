package algorithmTest;

import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
//4.Frame allocation algorithms for multiple processes -simulation of algorithms: random, proportional, page fault frequency, working set. 
//Compare their numbers of generated page faults.
public class Main {
	public static int[] generateRequests(int length, int range) {
		int[] arr = new int[length];
		Random random = new Random();
		int prev=0;
		for (int i = 0; i < length; i++) {
			arr[i] = (int) ((double)((random.nextGaussian()/10.0+0.5)*range+0.5)+prev);
			while(arr[i]<0||arr[i]>range) {
				arr[i] = (int) ((double)((random.nextGaussian()/10.0+0.5)*range+0.5)+prev);
			}
			prev=arr[i]-range/2;
		}
		displayArr(arr);
		return arr;
	}

	public static int findOnPage(int page[], int index) {
		for (int i = 0; i < page.length; i++) {
			if (page[i] == index) {
				return i;
			}
		}
		return -1;
	}

	public static void displayArr(int[] page) {
		for (int i = 0; i < page.length; i++) {
			if (page[i] == -1) {
				System.out.print("_ ");
			} else {
				System.out.print(page[i] + " ");
			}

		}
		System.out.println();
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter page size:");
		int pageLength = scanner.nextInt();
		System.out.println("Enter test length:");
		int testLength = scanner.nextInt();
		System.out.println("Enter range of requests:");
		int range = scanner.nextInt();
		int temp;
		System.out.println("Enter initial pages (enter -1 to start empty):");
		int initial[] = new int[pageLength];
		boolean empty=false;
		for(int i=0;i<pageLength;i++) {
			if(empty) {
				initial[i]=-1;
			}else {
				initial[i]=scanner.nextInt();
				if(initial[i]==-1) {
					empty=true;
				}
			}
			
		}
		scanner.close();
		try {
			PrintWriter results = new PrintWriter("Results.txt");
			System.out.println("Generated requests:");
			int[] requests = generateRequests(testLength, range);
			System.out.println("Simulation of FIFO:");
			temp = FIFO.simulate(pageLength, requests, initial);
			System.out.println("Resulting page faults: " + temp);
			results.println("FIFO page faults: " + temp);
			System.out.println("Simulation of OPT:");
			temp = OPT.simulate(pageLength, requests,initial);
			results.println("OPT page faults: " + temp);
			System.out.println("Resulting page faults: " + temp);
			System.out.println("Simulation of LRU:");
			temp = LRU.simulate(pageLength, requests,initial);
			results.println("LRU page faults: " + temp);
			System.out.println("Resulting page faults: " + temp);
			System.out.println("Simulation of ALRU:");
			temp = ALRU.simulate(pageLength, requests,initial);
			results.println("ALRU page faults: " + temp);
			System.out.println("Resulting page faults: " + temp);
			System.out.println("Simulation of RAND:");
			temp = RAND.simulate(pageLength, requests,initial);
			results.println("RAND page faults: " + temp);
			System.out.println("Resulting page faults: " + temp);
			results.close();
		} catch (Exception ex) {
		}
	}
}
