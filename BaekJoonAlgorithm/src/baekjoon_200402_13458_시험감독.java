import java.util.*;
import java.io.*;

public class baekjoon_200402_13458_시험감독 {
	public static int N, mainDirector, subDirector;
	public static long Answer;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input13458.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			Answer = N;
			String[] peopleList = br.readLine().split(" ");
			String[] temp = br.readLine().split(" ");
			mainDirector = Integer.parseInt(temp[0]);
			subDirector = Integer.parseInt(temp[1]);
			for(int i = 0; i < N; i++) {
				int people = Integer.parseInt(peopleList[i]);
				people -= mainDirector;
				if(people > 0) {
					Answer += people / subDirector;
					if(people % subDirector != 0) Answer++;
				}
			}
			System.out.println(Answer);
		}
		br.close();
	}
}
