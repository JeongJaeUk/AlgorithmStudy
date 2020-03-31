import java.util.*;
import java.io.*;

public class baekjoon_200331_12100_2048Easy {
	public static int N, Answer;
	
	public static void upMove(int[][] board) {
		for(int i = 0; i < N; i++) {
			loop:for(int j = 0; j < N; j++) {
				if(board[j][i] != 0) {
					for(int k = j + 1; k < N; k++) {
						if(board[j][i] != board[k][i] && board[k][i] != 0) continue loop;
						else if(board[j][i] == board[k][i]) {
							board[j][i] *= 2;
							board[k][i] = 0;
							continue loop;
						}
					}
				}
			}
			int count = N;
			for(int j = 0; j < count; j++) {
				if(board[j][i] == 0) {
					for(int k = j; k < N - 1; k++) {
						board[k][i] = board[k + 1][i];
					}
					board[N - 1][i] = 0;
					j--;
					count--;
				}
			}
		}
	}
	public static void downMove(int[][] board) {
		for(int i = 0; i < N; i++) {
			loop:for(int j = N - 1; j >= 0; j--) {
				if(board[j][i] != 0) {
					for(int k = j - 1; k >= 0; k--) {
						if(board[j][i] != board[k][i] && board[k][i] != 0) continue loop;
						else if(board[j][i] == board[k][i]) {
							board[j][i] *= 2;
							board[k][i] = 0;
							continue loop;
						}
					}
				}
			}
			int count = 0;
			for(int j = N - 1; j >= count; j--) {
				if(board[j][i] == 0) {
					for(int k = j; k > 0; k--) {
						board[k][i] = board[k - 1][i];
					}
					board[0][i] = 0;
					j++;
					count++;
				}
			}
		}
	}
	public static void leftMove(int[][] board) {
		for(int i = 0; i < N; i++) {
			loop:for(int j = 0; j < N; j++) {
				if(board[i][j] != 0) {
					for(int k = j + 1; k < N; k++) {
						if(board[i][j] != board[i][k] && board[i][k] != 0) continue loop;
						else if(board[i][j] == board[i][k]) {
							board[i][j] *= 2;
							board[i][k] = 0;
							continue loop;
						}
					}
				}
			}
			int count = N;
			for(int j = 0; j < count; j++) {
				if(board[i][j] == 0) {
					for(int k = j; k < N - 1; k++) {
						board[i][k] = board[i][k + 1];
					}
					board[i][N - 1] = 0;
					j--;
					count--;
				}
			}
		}
	}
	public static void rightMove(int[][] board) {
		for(int i = 0; i < N; i++) {
			loop:for(int j = N - 1; j >= 0; j--) {
				if(board[i][j] != 0) {
					for(int k = j - 1; k >= 0; k--) {
						if(board[i][j] != board[i][k] && board[i][k] != 0) continue loop;
						else if(board[i][j] == board[i][k]) {
							board[i][j] *= 2;
							board[i][k] = 0;
							continue loop;
						}
					}
				}
			}
			int count = 0;
			for(int j = N - 1; j >= count; j--) {
				if(board[i][j] == 0) {
					for(int k = j; k > 0; k--) {
						board[i][k] = board[i][k - 1];
					}
					board[i][0] = 0;
					j++;
					count++;
				}
			}
		}
	}
	
	public static void start(int[][] board, int cnt) {
		if(cnt == 5) {
			int max = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(board[i][j] > max) {
						max = board[i][j];
					}
				}
			}
			if(max > Answer) Answer = max;
			return;
		}
		int[][] subBoard = new int[N][N];
		for(int i = 0; i < N; i++) {
			subBoard[i] = board[i].clone();
		}
		upMove(subBoard);
		start(subBoard, cnt + 1);
		for(int i = 0; i < N; i++) {
			subBoard[i] = board[i].clone();
		}
		downMove(subBoard);
		start(subBoard, cnt + 1);
		for(int i = 0; i < N; i++) {
			subBoard[i] = board[i].clone();
		}
		leftMove(subBoard);
		start(subBoard, cnt + 1);
		for(int i = 0; i < N; i++) {
			subBoard[i] = board[i].clone();
		}
		rightMove(subBoard);
		start(subBoard, cnt + 1);
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input12100.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			int[][] board = new int[N][N];
			Answer = 0;
			for(int i = 0; i < N; i++) {
				String[] temp = br.readLine().split(" ");
				for(int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(temp[j]);
				}
			}
			start(board, 0);
			System.out.println(Answer);
		}
	}
}
