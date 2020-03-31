import java.util.*;
import java.io.*;

public class baekjoon_200330_13460_구슬탈출2 {
	public static int N, M, Rx, Ry, Bx, By, Ox, Oy;
	public static char[][] board;
	public static Queue<int[]> q;
	
	public static boolean up(int rx, int ry, int bx, int by) {
		int moveRy = 0;
		int moveBy = 0;
		// 공이 동시에 들어가는 경우 체크
		int check = 0;
		// 빨간공 먼저 동작
		for(int i = ry - 1; i >= 0; i--) {
			if(board[i][rx] == '#') {
				moveRy = i + 1;
				break;
			}
			if(rx == Ox && i == Oy) {
				check = 1;
				moveRy = i;
				break;
			}
		}
		// 파란공 동작
		for(int i = by - 1; i >= 0; i--) {
			if(board[i][bx] == '#') {
				moveBy = i + 1;
				break;
			}
			if(bx == Ox && i == Oy) {
				return false;
			}
		}
		// 공이 같은 칸에 왔을 때 재배치
		if(rx == bx && moveRy == moveBy) {
			if(ry > by) {
				moveRy++;
			} else {
				moveBy++;
			}
		}
		// 빨간공 골인
		if(check == 1) return true;
		// 새로 queue에 시작점 추가
		q.add(new int[] {rx, moveRy, bx, moveBy, 1});
		return false;
	}
	public static boolean down(int rx, int ry, int bx, int by) {
		int moveRy = 0;
		int moveBy = 0;
		// 공이 동시에 들어가는 경우 체크
		int check = 0;
		// 빨간공 먼저 동작
		for(int i = ry + 1; i < N; i++) {
			if(board[i][rx] == '#') {
				moveRy = i - 1;
				break;
			}
			if(rx == Ox && i == Oy) {
				check = 1;
				moveRy = i;
				break;
			}
		}
		// 파란공 동작
		for(int i = by + 1; i < N; i++) {
			if(board[i][bx] == '#') {
				moveBy = i - 1;
				break;
			}
			if(bx == Ox && i == Oy) {
				return false;
			}
		}
		// 공이 같은 칸에 왔을 때 재배치
		if(rx == bx && moveRy == moveBy) {
			if(ry > by) {
				moveBy--;
			} else {
				moveRy--;
			}
		}
		// 빨간공 골인
		if(check == 1) return true;
		// 새로 queue에 시작점 추가
		q.add(new int[] {rx, moveRy, bx, moveBy, 2});
		return false;
	}
	public static boolean left(int rx, int ry, int bx, int by) {
		int moveRx = 0;
		int moveBx = 0;
		// 공이 동시에 들어가는 경우 체크
		int check = 0;
		// 빨간공 먼저 동작
		for(int i = rx - 1; i >= 0; i--) {
			if(board[ry][i] == '#') {
				moveRx = i + 1;
				break;
			}
			if(i == Ox && ry == Oy) {
				check = 1;
				moveRx = i;
				break;
			}
		}
		// 파란공 동작
		for(int i = bx - 1; i >= 0; i--) {
			if(board[by][i] == '#') {
				moveBx = i + 1;
				break;
			}
			if(i == Ox && by == Oy) {
				return false;
			}
		}
		// 공이 같은 칸에 왔을 때 재배치
		if(moveRx == moveBx && ry == by) {
			if(rx > bx) {
				moveRx++;
			} else {
				moveBx++;
			}
		}
		// 빨간공 골인
		if(check == 1) return true;
		// 새로 queue에 시작점 추가
		q.add(new int[] {moveRx, ry, moveBx, by, 3});
		return false;
	}
	public static boolean right(int rx, int ry, int bx, int by) {
		int moveRx = 0;
		int moveBx = 0;
		// 공이 동시에 들어가는 경우 체크
		int check = 0;
		// 빨간공 먼저 동작
		for(int i = rx + 1; i < M; i++) {
			if(board[ry][i] == '#') {
				moveRx = i - 1;
				break;
			}
			if(i == Ox && ry == Oy) {
				check = 1;
				moveRx = i;
				break;
			}
		}
		// 파란공 동작
		for(int i = bx + 1; i < M; i++) {
			if(board[by][i] == '#') {
				moveBx = i - 1;
				break;
			}
			if(i == Ox && by == Oy) {
				return false;
			}
		}
		// 공이 같은 칸에 왔을 때 재배치
		if(moveRx == moveBx && ry == by) {
			if(rx > bx) {
				moveBx--;
			} else {
				moveRx--;
			}
		}
		// 빨간공 골인
		if(check == 1) return true;
		// 새로 queue에 시작점 추가
		q.add(new int[] {moveRx, ry, moveBx, by, 4});
		return false;
	}
	
	public static int bfs() {
		int cnt = 1;
		while(!q.isEmpty()) {
			if(cnt > 10) break;
			int limit = q.size();
			for(int i = 0; i < limit; i++) {
				int[] temp = q.poll();
				int rx = temp[0];
				int ry = temp[1];
				int bx = temp[2];
				int by = temp[3];
				// direct는 최초 0이고 갔던 방향의 반대로 다시 돌아가지 않기위해 체크한다. {위, 아래, 좌, 우} 순서대로 {1 2 3 4}로 한다.
				int direct = temp[4];
				// 위로 기울기 (빨간공이나 파란공이 위로 갈 수 있고 이전 방향이 아래가 아닐 때)
				if((board[ry - 1][rx] == '.' || board[by - 1][bx] == '.') && direct != 2) {
					if(rx != bx || ry - 1 != by || board[by - 1][bx] != '#') {
						if(up(rx, ry, bx, by)) return cnt;
					}
				}
				// 아래로 기울기
				if((board[ry + 1][rx] == '.' || board[by + 1][bx] == '.') && direct != 1) {
					if(rx != bx || ry + 1 != by || board[by + 1][bx] != '#') {
						if(down(rx, ry, bx, by)) return cnt;
					}
				}
				// 좌로 기울기
				if((board[ry][rx - 1] == '.' || board[by][bx - 1] == '.') && direct != 4) {
					if(ry != by || rx - 1 != bx || board[by][bx - 1] != '#') {
						if(left(rx, ry, bx, by)) return cnt;
					}
				}
				// 우로 기울기
				if((board[ry][rx + 1] == '.' || board[by][bx + 1] == '.') && direct != 3) {
					if(ry != by || rx + 1 != bx || board[by][bx + 1] != '#') {
						if(right(rx, ry, bx, by)) return cnt;
					}
				}
			}
			cnt++;
		}
		return -1;
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input13460.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= T; tc++) {
			String[] temp = br.readLine().split(" ");
			N = Integer.parseInt(temp[0]);
			M = Integer.parseInt(temp[1]);
			board = new char[N][M];
			for(int i = 0; i < N; i++) {
				String line = br.readLine();
				for(int j = 0; j < M; j++) {
					board[i][j] = line.charAt(j);
					if(board[i][j] == 'R') {
						Rx = j;
						Ry = i;
						board[i][j] = '.';
					} else if(board[i][j] == 'B') {
						Bx = j;
						By = i;
						board[i][j] = '.';
					} else if(board[i][j] == 'O') {
						Ox = j;
						Oy = i;
						board[i][j] = '.';
					}
				}
			}
			q = new LinkedList<>();
			q.add(new int[] {Rx, Ry, Bx, By, 0});
			System.out.println(bfs());
		}
	}
}
