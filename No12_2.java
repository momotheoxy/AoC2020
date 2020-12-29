import java.util.*;
import java.io.*;

class No12_2
{
    public static void main(String[] args) throws FileNotFoundException 
    {        
		File file = new File("./testcases/Day12.txt"); 
		Scanner in = new Scanner(file); 

		int latitude = 0;
		int longtitude = 0;

		int waypointLat = -1;
		int waypointLong = 10;

		while(in.hasNext())
		{
			String input = in.next();

			switch (input.charAt(0))
			{
				case 'N':	waypointLat -= Integer.parseInt(input.substring(1)); break;
				case 'S':	waypointLat += Integer.parseInt(input.substring(1)); break;
				case 'W':	waypointLong -= Integer.parseInt(input.substring(1)); break;
				case 'E':	waypointLong += Integer.parseInt(input.substring(1)); break;
				case 'L':	
							if (Integer.parseInt(input.substring(1)) == 90)	
							{
								int temp = waypointLat;
								waypointLat = -waypointLong;
								waypointLong = temp;
							}
							else if (Integer.parseInt(input.substring(1)) == 180)
							{
								waypointLat = -waypointLat;
								waypointLong = -waypointLong;
							}
							else if (Integer.parseInt(input.substring(1)) == 270)
							{
								int temp = waypointLat;
								waypointLat = waypointLong;
								waypointLong = -temp;
							}
							break;
				case 'R':	
							if (Integer.parseInt(input.substring(1)) == 90)
							{
								int temp = waypointLat;
								waypointLat = waypointLong;
								waypointLong = -temp;
							}
							else if (Integer.parseInt(input.substring(1)) == 180)
							{
								waypointLat = -waypointLat;
								waypointLong = -waypointLong;
							}
							else if (Integer.parseInt(input.substring(1)) == 270)
							{
								int temp = waypointLat;
								waypointLat = -waypointLong;
								waypointLong = temp;
							}
							break;
				case 'F':	latitude += Integer.parseInt(input.substring(1)) * waypointLat;
							longtitude += Integer.parseInt(input.substring(1)) * waypointLong;
							break;
			}
			System.out.println(latitude + " " + longtitude + " " + waypointLat + " " + waypointLong);
		}

		System.out.println((Math.abs(latitude) + Math.abs(longtitude)));
    }
}