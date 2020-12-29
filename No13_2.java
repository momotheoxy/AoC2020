import java.util.*;
import java.io.*;
import static java.util.Arrays.stream;

class No13_2
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		File file = new File("./testcases/Day13.txt"); 
		Scanner in = new Scanner(file); 

		in.nextLine();
		
		String[] buses = in.nextLine().split("[,]+");
		
		ArrayList<Integer> busIDList = new ArrayList<>();
		ArrayList<Integer> busDelayedList = new ArrayList<>();

		int max = Integer.MIN_VALUE;
		int maxDelay = 0;

		for (int i = 0; i < buses.length; i++)
		{
			if (!buses[i].equals("x"))
			{
				int busID = Integer.parseInt(buses[i]);
				busIDList.add(busID);
				busDelayedList.add(i);

				if (busID > max)
				{
					max = busID;
					maxDelay = i;
				}
			}
		}

		System.out.println("max="+max);
		System.out.println("maxDelay="+maxDelay);

		long[] busIDArray = busIDList.stream().mapToLong(i -> i).toArray();
		long[] busDelayedArray = busDelayedList.stream().mapToLong(i -> i).toArray();
		long[] partialProduct = new long[busIDArray.length];
		long[] inverse = new long[busIDArray.length];

		long timestamp = 100911859510369L;
		//System.out.println(timestamp % 29);
		System.out.println(chineseRemainder(busIDArray, busDelayedArray));

		//compute crt

		// print("sum = "+sum+"\n");
	}
	public static long chineseRemainder(long[] n, long[] a) {
 
        long prod = stream(n).reduce(1, (i, j) -> i * j);
 
        long p, sm = 0;
        for (int i = 0; i < n.length; i++) {
            p = prod / n[i];
            sm += a[i] * mulInv(p, n[i]) * p;
        }
        return prod - sm % prod;
    }
 
    private static long mulInv(long a, long b) {
        long b0 = b;
        long x0 = 0;
        long x1 = 1;
 
        if (b == 1)
            return 1;
 
        while (a > 1) {
            long q = a / b;
            long amb = a % b;
            a = b;
            b = amb;
            long xqx = x1 - q * x0;
            x1 = x0;
            x0 = xqx;
        }
 
        if (x1 < 0)
            x1 += b0;
 
        return x1;
    }
}