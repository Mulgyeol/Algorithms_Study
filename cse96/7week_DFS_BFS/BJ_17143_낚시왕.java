package com.ssafy.sub.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_17143_���ÿ� {
	//s:�ӷ� d:�̵����� 1:��, 2: �Ʒ�, 3: ������, 4: ����/z:ũ��
	static int R,C,M,ans,map[][];
	//�� ������ �Ʒ� ����
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	static ArrayList<Shark> l = new ArrayList<Shark>();
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine()," ");
		R = Integer.valueOf(st.nextToken());//���� ũ��
		C = Integer.valueOf(st.nextToken());//���� ũ��
		M = Integer.valueOf(st.nextToken());//��� ��
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine()," ");
			int r = Integer.valueOf(st.nextToken());
			int c = Integer.valueOf(st.nextToken());//��ġ
			int s = Integer.valueOf(st.nextToken());//�ӷ�
			int d = Integer.valueOf(st.nextToken());//���� 1�� 2�Ʒ� 3������ 4����
			if(d == 2) d = 3;//�Ʒ�
			else if(d == 3) d = 2;//������//  0:�� 1:������ 2:�Ʒ� 3:����
			int z = Integer.valueOf(st.nextToken());//ũ��
			d=d-1;
			if(d==0 || d==2)l.add(new Shark(r,c,s%((R-1)*2) ,d,z));
			else if(d==1 || d==3)l.add(new Shark(r,c,s%((C-1)*2) ,d,z));
		}
		map = new int[R + 1][C + 1];
		for(int i = 1; i <= C; i++) {
            //�ʱ�ȭ
		    for(int j = 1; j <= R; j++) {
			    Arrays.fill(map[j], 0);
		    }
			fishing(i);//���ÿ� ��ġ i
			Move();//����̵�
		}
		System.out.println(ans);
	}
	private static void Check(int until) {
		Shark cur = l.get(until);
		for(int i = 0; i < until; i++) {
			Shark s = l.get(i);
			if(s.fished || !s.isAlive) continue;
			if(s.r == cur.r && s.c == cur.c) {//������ġ
				if(s.z < cur.z) s.setAlive(false);
				else cur.setAlive(false);
				break;
			}
		}
	}
	private static void Move() {
		for(int i = 0; i < M; i++) {
			Shark s = l.get(i);
			if(s.fished || !s.isAlive) continue; //�����ų� �����Ÿ� ����ä�� �д�.
			int x = s.r;
			int y = s.c;
			int dir = s.d;
			int nx = x + (dx[dir]*s.s);
			int ny = y + (dy[dir]*s.s);
			if(nx <= R && ny <= C && nx > 0 && ny > 0) { //����״��, ��ġ ����
				s.r = nx;
				s.c = ny;
			}
			//������ ����Ƿ� ������ �ٲ�� ��ġ�� ����ȴ�.
			else if(ny > C || ny < 1 && nx == s.r){
				//�¿�� �̵�
				int cnt = 0;
				while(cnt < s.s) {
					int c = y + dy[dir];//�ӽð����� ����ĭ ���
					if(c < 1 || c > C) dir = (dir+2) % 4;//����� ���� ĭ�� ��踦 ����� ��� ������ �̸� ��ȯ
					y += dy[dir];//�̸���ȯ��ų� �ùٸ��������� ��ĭ �̵�
					cnt++;
				}
				ny = y;
				s.c = ny;//��ġ����
				s.d = dir;//���� ����
			}
			else if(nx > R || nx < 1 && ny == s.c) {
				//���Ʒ��� �̵�
				int cnt = 0;
				while(cnt != s.s) {
					int c = x + dx[dir];
					if(c < 1 || c > R) dir = (dir + 2) % 4;
					x += dx[dir];
					cnt++;
				}
				nx = x;
				s.r = nx;//��ġ����
				s.d = dir;//���ⰻ��
			}
			if(map[s.r][s.c] == 0) map[s.r][s.c] = s.z;
			else Check(i);
		}
	}
	private static void fishing(int loc) {//������
		Shark target = null;
		for(int i = 0; i < M; i++) {//�ݺ������鼭 ��� ã��
			Shark s = l.get(i);
			if(s.fished || !s.isAlive) continue;
			if(loc == s.c) {
				if(target == null) {//loc�� ������ġ�� �� ���ݱ��� �����ٸ�
					target = s;//target�� ���� i��° ���� ����
					continue;
				}
				else if(target.r > s.r){//target�� null�� �ƴϸ�
					target = s;//target���� ���� i��° �� ���� �ִ°�� target����
				}
			}
		}
		if(target != null) {//���
			ans += target.z;
			target.setFished(true);
		}
	}
	static class Shark{
		int r,c,s,d,z;
		boolean fished = false;
		boolean isAlive = true;
		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;//��� ��ġ
			this.c = c;
			this.s = s;//�ӷ�
			this.d = d;//�̵�����
			this.z = z;//ũ��
		}
		public void setFished(boolean fished) {
			this.fished = fished;
		}
		public void setAlive(boolean isAlive) {
			this.isAlive = isAlive;
		}
	}
}

