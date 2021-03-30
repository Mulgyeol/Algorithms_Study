package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 0:��ĭ
 * 1,2,3,4,5,6 : ĭ�� �ִ� ������� ũ��
 * 9: �Ʊ� ����� ��ġ
 * 
 */
public class BJ_16236_babyshark {
	static int size = 2, N, space[][], eat = 0, dx[] = {-1,1,0,0},dy[] = {0,0,-1,1}, shark_x = 0, shark_y = 0;
	static ArrayList<fish> fish_list;//�������ִ� ������� ����Ʈ
	static Queue<dis> q = new LinkedList<dis>();//bfs��
	static boolean visited[][];
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());//������ ũ�� N
		space = new int[N][N];
		fish_list = new ArrayList<fish>();
		visited = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine()," ");
			for(int j = 0; j < N; j++) {
				space[i][j] = Integer.parseInt(st.nextToken());
				if(space[i][j] == 9) {shark_x = i; shark_y=j;}//�����ġ
			}
		}//�Է� ��
		
		//�������� ũ�Ⱑ ������ + �Ÿ������� �����Ѵ�.
		
		int time = 0;
		while(check()) {//���� �� �ִ� ����Ⱑ �ִ��� Ȯ��
			bfs(new dis(shark_x, shark_y, 0));
			Collections.sort(fish_list);
			if(fish_list.size() == 0) break;
			fish f = fish_list.get(0);
			time += f.dist;
			space[shark_x][shark_y] = 0;
			space[f.x][f.y] = 9;
			shark_x = f.x;
			shark_y = f.y;
			eat++;
			if(eat == size) {
				size++;
				eat = 0;
			}
			//bfs�� ���ο� shark_x,shark_y�κ��� ����� �Ÿ� ����
			fish_list.clear();
			
			for(int u = 0; u < N; u++) {
				Arrays.fill(visited[u], false);
			}
		}
		
		System.out.println(time);
	}
	private static void bfs(dis d) {
		q.add(d);
		while(!q.isEmpty()) {
			dis tmp = q.poll();
			int x = tmp.getX();
			int y = tmp.getY();
			int tmp_dis = tmp.getDis();
			visited[x][y] = true;
			for(int dir = 0; dir < 4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
				if(space[nx][ny] > 0 && space[nx][ny] < size) {
					//����� : �Ÿ�����
					fish_list.add(new fish(nx,ny,space[nx][ny],tmp_dis+1));
				}
				else if(space[nx][ny] == 0 || space[nx][ny] == size) {
					visited[nx][ny] = true;
					q.add(new dis(nx,ny,tmp_dis+1));
				}
			}
		}
	}
	private static boolean check() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(space[i][j] != 0 && space[i][j] != 9 && space[i][j] < size) return true;//ArrayList�� ������ ����
			}
		}//�Է� ��
		return false;
	}
	
	static class dis{
		int x, y, dis;

		public dis(int x, int y, int dis) {
			this.x = x;
			this.y = y;
			this.dis = dis;
		}

		public int getX() {
			return x;
		}

		public int getDis() {
			return dis;
		}

		public int getY() {
			return y;
		}
	}
	
	static class fish implements Comparable<fish>{
		int x, y, size ,dist;
		
		public fish(int x, int y, int size, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
			this.dist = dist;
		}

		public void setDist(int dist) {
			this.dist = dist;
		}

		@Override
		public String toString() {
			return "fish [x=" + x + ", y=" + y + ", size=" + size + ", dist=" + dist + "]";
		}

		@Override
		public int compareTo(fish o) {
			if(this.dist == o.dist) {
					if(this.x == o.x) {
						return this.y - o.y;
					}
					return this.x - o.x;
				}
			return this.dist - o.dist;
		}
	}
}

