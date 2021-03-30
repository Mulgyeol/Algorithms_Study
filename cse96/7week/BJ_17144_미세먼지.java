package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_17144_�̼����� {

	static int[][] room,temp;
	static int R,C,filter_x,filter_y,dx[] = {-1,1,0,0},dy[] = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine()," ");
		R = Integer.parseInt(st.nextToken());//��
		C = Integer.parseInt(st.nextToken());//��
		int T = Integer.parseInt(st.nextToken());//T�� ���� ��
		room = new int[R][C];
		temp = new int[R][C];
		int filter_x = 0, filter_y = 0;//����û���� x,y��ǥ
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(bf.readLine()," ");
			for(int j = 0; j < C; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if(room[i][j] == -1) {
					filter_x = i;
					filter_y = j;
				}//���������� ����û���� �Ʒ��κ��� i,j�����
			}
		}//�Է� ��
		for(int t = 0; t < T; t++) {//T�ʵ��� �ݺ�
			for(int i = 0; i < R; i++) {
				Arrays.fill(temp[i], 0);
			}
			expand();//Ȯ��
			run_filter(filter_x,filter_y);//û���� ����
		}
		int ans = 0;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(room[i][j]>0)
					ans+=room[i][j];
			}
		}
		
		System.out.println(ans);
	}
	private static void run_filter(int x,int y) {
		int top_x = x - 1;
		int bot_x = x;
		//�ð���� �κ�
		for(int i = top_x - 1; i > 0; i--) {
			room[i][y] = room[i-1][y];
		}//����û���� ��
		for(int i = y; i < C - 1; i++) {
			room[y][i] = room[y][i+1];
		}
		for(int i = 0; i < top_x; i++) {
			room[i][C-1] = room[i+1][C-1];
		}
		for(int i = C-1; i > y; i--) {
			room[top_x][i] = room[top_x][i-1];
		}
		room[top_x][y+1] = 0;
		
		//�ݽð����
		for(int i = bot_x + 1; i < R - 1; i++) {
			room[i][y] = room[i+1][y]; 
		}
		for(int i = y; i < C - 1; i++) {
			room[R-1][i] = room[R-1][i+1];
		}
		for(int i = R - 1; i > bot_x - 1; i--) {
			room[i][C-1] = room[i-1][C-1];
		}
		for(int i = C - 1; i > y; i--) {
			room[bot_x][i] = room[bot_x][i-1];
		}
		room[bot_x][y+1] = 0;
	}
	private static void expand() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(room[i][j] > 0) {//�̼������� �ִ� ĭ
					int dir_cnt = 0;
					int expand_dust = room[i][j]/5;
					for(int k = 0; k < 4; k++) {
						int tx = i + dx[k];
						int ty = j + dy[k];
						if(tx < 0 || ty < 0 || tx >= R || ty >= C || room[tx][ty] == -1 ) {
							//�迭���� ���̰ų� ����û������ �κ�
							continue;
						}
						else {
							temp[tx][ty] += expand_dust;
							dir_cnt++;
						}
					}//for-k
					room[i][j] = room[i][j] - (expand_dust * dir_cnt);
				}//if�̼������� �մ�ĭ
			}//for-C
		}//for-R
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(room[i][j] != -1) {
					temp[i][j] += room[i][j];
					room[i][j] = temp[i][j];//�迭 ����
				}
			}
		}
	}//expand
}//expand�Ϸ�

