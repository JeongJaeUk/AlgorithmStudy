import java.util.*;
import java.io.*;

public class baekjoon_200402_3190_뱀 {
	public static int N;
	public static int[][] board;
	public static char[] directCommand;
	public static Deque<int[]> snake;
	public static int[] dx = {1, 0, -1, 0};
	public static int[] dy = {0, 1, 0, -1};
	
	public static int start() {
		while(true) {
			int[] temp = snake.peekFirst();
			int headx = temp[0];
			int heady = temp[1];
			int direct = temp[2];
			int time = temp[3];
			// 시간에 따른 방향전환 체크
			if(directCommand[time] == 'D') {
				direct = (direct + 1) % 4;
			} else if (directCommand[time] == 'L') {
				direct = (direct + 3) % 4;
			}
			time++;
			int nextx = headx + dx[direct];
			int nexty = heady + dy[direct];
			if(nextx < 0 || nextx >= N || nexty < 0 || nexty >= N || board[nexty][nextx] == 1) {
				return time;
			}
			snake.addFirst(new int[] {nextx, nexty, direct, time});
			// 사과를 안먹었을 때
			if(board[nexty][nextx] == 0) {
				int[] tail = snake.pollLast();
				//기존의 꼬리 부분을 제거
				board[tail[1]][tail[0]] = 0;
			}
			// 사과를 먹었다면 기존의 몸통, 꼬리는 존재하고 다음머리의 위치만 표시 
			board[nexty][nextx] = 1;
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input3190.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			board = new int[N][N];
			int appleCnt = Integer.parseInt(br.readLine().trim());
			for(int i = 0; i < appleCnt; i++) {
				String[] temp = br.readLine().split(" ");
				board[Integer.parseInt(temp[0]) - 1][Integer.parseInt(temp[1]) - 1] = 2;
			}
			// 시간을 1씩 늘려가면서 해당지점에 D or L이 존재한다면 동작하도록 하기 위한 배열
			directCommand = new char[10001];
			int directCnt = Integer.parseInt(br.readLine().trim());
			for(int i = 0; i < directCnt; i++) {
				String[] temp = br.readLine().split(" ");
				directCommand[Integer.parseInt(temp[0])] = temp[1].charAt(0);
			}
			board[0][0] = 1;
			snake = new ArrayDeque<int[]>();
			// 시작지점, 방향 정보, 걸린 시간을 Deque에 삽입
			snake.addLast(new int[] {0, 0, 0, 0});
			System.out.println(start());
		}
		br.close();
	}
}
