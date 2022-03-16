import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class practise {

	public static void main(String[] args) throws IOException {
		String[] str = new String[100000];
		int[] arr = new int[100000];
		BufferedReader br;
		BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\admin\\Eclipse-workspace-EE\\AAPL_Assignment\\src\\output.txt"));;
		try {
		br = new BufferedReader(new FileReader("C:\\Users\\admin\\Eclipse-workspace-EE\\AAPL_Assignment\\src\\Data.txt")); 
		
		String s = br.readLine();
		int lines = 0;

		int i=0;
		while(s!=null ) {
			str[i++] = s;
			lines++;
			s = br.readLine();
			
		}

		for(int j = 0;j<arr.length;j++) {
			arr[j] = Integer.parseInt(str[j]);
		}
//		for(int ii=0;ii<arr.length;ii++) {
//			System.out.println(arr[ii]);
//		}

		br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int chunksize = 5;

		System.out.println("------------");
		int count = 0;
		int res[][] = arraySplit(arr,chunksize);
		for(int[] x : res) {
			Arrays.sort(x);
			System.out.println(Arrays.toString(x));
		}
		System.out.println("-------------------------------");
		System.out.println("-------------------------------");
		System.out.println("Tuples being formed after phase 1:- "+ res.length);
		System.out.println("Length of each tuple:- " + res[0].length);
		
		//Phase 2
		System.out.println("Phase 2 begins");
		for(int i=0;i<20000;i++) {
			
			for(int j=0;j<5;j++) {
				for(int k=0;k<20000;k++) {
					for(int l=0;l<5;l++) {
						if(res[i][j] < res[k][l]) {
							int temp = res[i][j];
							res[i][j] = res[k][l];
							res[k][l] = temp;
					}
				}
			}
		}
	}
//		for(int[] x : res) {
//			
//			System.out.println(Arrays.toString(x));
//		}
//		System.out.println("sorted array");
//		for(int i=0;i<20000;i++) {
//			for(int j=0;j<5;j++) {
//				System.out.print(res[i][j]+ " ");
//			}
//			System.out.println();
//		}
		Integer[] final_arr = new Integer[100000];
		int count1 =0;
		for(int i=0;i<20000;i++) {
			for(int j=0;j<5;j++) {
				
				final_arr[count1++] = res[i][j];
			}
		}
		System.out.println("final 1D array");
		
		for(int i=0;i<final_arr.length;i++) {
			System.out.println(final_arr[i]);
		}
		
		for(int i=0;i<final_arr.length;i++) {
			bw.write(final_arr[i].toString());
			bw.newLine();
		}
		bw.flush();
		
}
	private static int[][] arraySplit(int[] arr, int chunksize) {
		// TODO Auto-generated method stub
		return IntStream.iterate(0,i-> i + chunksize)
				.limit((int) Math.ceil((double)arr.length / chunksize))
				.mapToObj(j -> Arrays.copyOfRange(arr,j,Math.min(arr.length, j+chunksize)))
				.toArray(int[][]::new);
	}

}
