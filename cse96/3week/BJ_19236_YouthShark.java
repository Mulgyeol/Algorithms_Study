package com.ssafy.March;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * �迭ũ��� 4x4
 */

public class BJ_19236_YouthShark {
	                  //�� �»� �� ���� �� ���� �� ��� �����ϸ� �ݽð�
	static int dx[] = {-1, -1, 0, 1, 1, 1, 0, -1},
	           dy[] = {0, -1, -1, -1, 0, 1, 1, 1};
	
	static int ans = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		ArrayList<fish> space = new ArrayList<fish>();
		fish shark = null;
		int eat_f = 0;
		for(int i = 1; i < 5; i ++) {
			st = new StringTokenizer(bf.readLine()," ");
			for(int j = 1; j < 5; j++) {
				int no = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				if(i == 1 && j == 1) {
					eat_f = no;
					shark = new fish(0, i, j ,dir);
				}
				else {
					space.add(new fish(no, i, j, dir));
				}
			}
		}//�Է� ��
		
		space.sort( (f1,f2) -> f1.getNo() - f2.getNo());//no������ ����
//		System.out.println(space);
		int N = space.size();//����Ʈ ������(���� ����� ��)��ŭ �̵�
		Move(space, N, 0, shark);
		
		System.out.println(ans + eat_f);
		
	}
	
	private static void Move_shark(ArrayList<fish> space, int n, int sum, fish shark) {
		ans = Math.max(sum, ans);
		int x = shark.getX();
		int y = shark.getY();
		int dir = shark.getDir();
		fish te = shark;
		int nx = x + dx[dir - 1];
		int ny = y + dy[dir - 1];
		while(nx > 0 && nx < 5 && ny > 0 && ny <5) {
			for(int i = 0; i < n; i++) {
				fish t = space.get(i);
				int tx = t.getX();
				int ty = t.getY();
				if(nx == tx && ny == ty) {
					te.setX(tx);
					te.setY(ty);
					te.setDir(t.getDir());
					space.remove(i);
					Move(space, space.size(), sum + t.getNo(), te);
					space.add(i, t);
				}
			}//for
			nx += dx[dir - 1];
			ny += dy[dir - 1];
		}
	}
	
	//n�� ����Ʈ�� size ����Ⱑ ���ٸ� ������
	private static boolean Check(ArrayList<fish> space, int n, fish shark) {
		int x = shark.getX();
		int y = shark.getY();
		int dir = shark.getDir();
		int nx = x + dx[dir - 1];
		int ny = y + dy[dir - 1];
		while(nx > 0 && nx < 5 && ny > 0 && ny < 5) {
			for(int i = 0; i < n; i++) {
				int tx = space.get(i).getX();
				int ty = space.get(i).getY();
				if(nx == tx && ny == ty)
					return true;
			}
			nx += dx[dir - 1];
			ny += dy[dir - 1];
		}
		return false;
	}

	//n�� ����Ʈ�� ������ �� ���� ��� ����⸦ �̵�
	private static void Move(ArrayList<fish> space, int n, int sum, fish shark) {
		fish temp;
		for(int i = 0; i < n; i++) {
			temp = space.get(i);
			Decision(space, i,temp, shark); //����� �̵���Ҹ� �����ϴ� �Լ�
		}
		if(Check(space, n, shark)) {
			Move_shark(space,n, sum, shark);
		}
		else {
			return;
		}
	}
	
	//����� �̵���Ҹ� �����ϴ� �Լ�
	private static void Decision(ArrayList<fish> space, int index, fish temp, fish shark) {
		int x = temp.getX();
		int y = temp.getY();
		int dir = temp.getDir();
		int move_dir = dir + 1;
		boolean flag = false;
		//ó�� dir�� �������� while�� Ż��
		while(move_dir != dir) {
			if(move_dir == 9) {
				move_dir = move_dir % 8;
			}

			int nx = x + dx[move_dir - 1];
			int ny = y + dy[move_dir - 1];//�̵��� ĭ nx,ny
			if(nx < 1 || ny < 1 || nx > 5 || ny > 5) {
				//���� ������ ���� ��� �ݽð�� ȸ����.
				move_dir++;
				continue;
			}
			else if(nx == shark.getX() && ny == shark.getY()) {
				//�� �ִ� ��� �ݽð�� ȸ����.
				move_dir++;
				continue;
			}
			
			//���� ������ ������ �ʰ� �� ����ġ�� ���� ���
			int N = space.size();
			for(int i = 0; i < N; i++) {
				fish tmp = space.get(i);
				//�̵��� ĭ�� ����Ⱑ �ִ� ���
				if(tmp.getX() == nx && tmp.getY() == ny) {
					int t1x = tmp.getX();//�̵��� ĭ�� x,y
					int t1y = tmp.getY();
					
					int t2x = temp.getX();//������ x,y
					int t2y = temp.getY();
					
					//�� ������� x,y�� �ٲ�.(�ڸ� �ٲ�)
					space.get(index).setX(t1x);//index�� ����ĭ ����ĭ�� x,y�� �̵���ĭ�� x,y�� �ٲ۴�.
					space.get(index).setY(t1y);
					
					space.get(i).setX(t2x); //i�� �̵���ĭ, �̵���ĭ�� x,y�� ����ĭ�� x,y�� �ٲ۴�.
					space.get(i).setY(t2y);
					
					flag = true;//�̵���
					move_dir++;
					break;
				}
			}
			if(flag) break;
			
			//�̵���ĭ�� ����Ⱑ ���� ���
			//index�� ����Ⱑ ���� ������̹Ƿ� ���� ������� x,y�� �̵���ĭ�� nx,ny�� �ٲ۴�.
			space.get(index).setX(nx);
			space.get(index).setX(ny);
			flag = true;
			move_dir++;
			if(flag) break;
		}//while
	}
}

class fish{
	private int no;
	private int x;
	private int y;
	private int dir;
	
	fish(int no, int x, int y, int dir) {
		this.no = no;
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	@Override
	public String toString() {
		return "fish [no=" + no + ", x=" + x + ", y=" + y + ", dir=" + dir + "]";
	};
	
}