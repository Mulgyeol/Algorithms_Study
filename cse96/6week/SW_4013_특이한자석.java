package March;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_4013_Ư�����ڼ� {
	static int magnet[] = new int[32];//0~7 1�� 8~15 2�� 16~23 3�� 24~31 4��
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(bf.readLine()); //�� �׽�Ʈ���̽�
		for(int tc = 1; tc <= T; tc++) {
			int K = Integer.parseInt(bf.readLine());
			
			init();
			for(int i = 0; i < K; i++) { //ȸ�� �� ��ŭ
				st = new StringTokenizer(bf.readLine()," ");
				int n = Integer.parseInt(st.nextToken()); //ȸ�����ڼ�
				int dir = Integer.parseInt(st.nextToken());//����
				pick(n,dir);
			}
			int ans = 0;
			if(magnet[0] == 1) ans+=1;
			if(magnet[8] == 1) ans+=2;
			if(magnet[16] == 1) ans+=4;
			if(magnet[24] == 1) ans+=8;
			System.out.println("#"+tc+" "+ans);
			
		}//for-tc
	}

	private static void pick(int n, int dir) {
		//0~7 1��(2) 8~15 (14)2��(10) 16~23 (22)3��(18) 24~31(30)4��
		int dir2 = 0;
		if(dir == 1) {
			dir2 = -1;
		}
		else {
			dir2 = 1;
		}
		
		if(n == 1) {//1�� �ڼ� ȸ���� ��
			if(magnet[2] == magnet[14]) {
				rotate1(dir);
				return; //1�� 2�� �´��� ���� ���� �ڼ� ȸ��x 
			}
			else {//ȸ�� 1~2��
				if(magnet[10] == magnet[22]) { //2�� 3�� �´��� ���� ���� �ڼ�
					rotate2(dir2);
				}//1~2�� �ڼ� ȸ��
				else {
					if(magnet[18] ==  magnet[30]) {//3�� 4�� �´��� ���� ���� �ڼ�
						//1~3�� �ڼ� ȸ��
						rotate2(dir2);
						rotate3(dir);
					}
					else {
						rotate2(dir2);
						rotate3(dir);
						rotate4(dir2);
					}
				}
			}
			rotate1(dir);
		}
		else if(n == 2) {//2�� �ڼ� ȸ���� ��
			if(magnet[2] == magnet[14]) ; //1�� 2�� �´��� ���� ���� �ڼ� ȸ��x 
			else {
				rotate1(dir2);
			}
			if(magnet[10] == magnet[22]) {//2�� 3�� ���� �ڼ� ȸ�� x
			}
			else {
				if(magnet[18] == magnet[30]) {//2�� 3�� �ٸ� �ڼ�, 3��4�� ���� �ڼ�
					rotate3(dir2);
				}
				else {
					rotate3(dir2);
					rotate4(dir);
				}
			}
			rotate2(dir);
		}
		else if(n == 3) {//3�� �ڼ� ȸ���� ��
			if(magnet[18] == magnet[30]);//������ Ȯ��
			else {
				rotate4(dir2);
			}
			if(magnet[10] == magnet[22]) { //2�� 3�� ���� �ڼ�
				
			}
			else {//2�� 3�� �ٸ��ڼ�
				if(magnet[2] == magnet[14]) { // 1�� 2�� ���� �ڼ�
					rotate2(dir2);
				}
				else {
					rotate2(dir2);
					rotate1(dir);
				}
			}
			rotate3(dir);
		}
		else if(n == 4) {//4�� �ڼ� ȸ���� ��
			if(magnet[18] == magnet[30]) {
				rotate4(dir);
				return;//3,4�� ���� �ڼ�
			}
			else {
				if(magnet[10] == magnet[22]) {//3,4�� �ٸ� �ڼ� 2,3�� ���� �ڼ�
					rotate3(dir2);
				}
				else {
					if(magnet[2] == magnet[14]) {//2,3,4�� �ٸ� �ڼ� 1,2�� ���� �ڼ�
						rotate2(dir);
						rotate3(dir2);
					}
					else {
						rotate1(dir2);
						rotate2(dir);
						rotate3(dir2);
					}
				}
			}
			rotate4(dir);
		}
	}
	//4�� �ڼ�ȸ�� 24~31
	private static void rotate4(int dir) {
		if(dir == 1) {//�ð����
			int temp = magnet[31];
			for(int i = 31; i > 24; i--) {
				magnet[i] = magnet[i-1];
			}
			magnet[24] = temp;
		}
		else if(dir == -1) {
			int temp = magnet[24];
			for(int i = 24; i < 31; i++) {
				magnet[i] = magnet[i+1];
			}
			magnet[31] = temp;
		}
	}
	//3�� �ڼ�ȸ�� 16~23
	private static void rotate3(int dir) {
		if(dir == 1) {//�ð����
			int temp = magnet[23];
			for(int i = 23; i > 16; i--) {
				magnet[i] = magnet[i-1];
			}
			magnet[16] = temp;
		}
		else if(dir == -1) {
			int temp = magnet[16];
			for(int i = 16; i < 23; i++) {
				magnet[i] = magnet[i+1];
			}
			magnet[23] = temp;
		}
	}
	//2�� �ڼ�ȸ�� 8~15
	private static void rotate2(int dir) {
		if(dir == 1) {//�ð����
			int temp = magnet[15];
			for(int i = 15; i > 8; i--) {
				magnet[i] = magnet[i-1];
			}
			magnet[8] = temp;
		}
		else if(dir == -1) {
			int temp = magnet[8];
			for(int i = 8; i < 15; i++) {
				magnet[i] = magnet[i+1];
			}
			magnet[15] = temp;
		}
	}
	//1�� �ڼ�ȸ�� 0~7
	private static void rotate1(int dir) {
		if(dir == 1) {//�ð����
			int temp = magnet[7];
			for(int i = 7; i > 0; i--) {
				magnet[i] = magnet[i-1];
			}
			magnet[0] = temp;
		}
		else if(dir == -1) {//�ݽð����
			int temp = magnet[0];
			for(int i = 0; i < 7; i++) {
				magnet[i] = magnet[i+1];
			}
			magnet[7] = temp;
		}
	}

	private static void init() throws IOException {
		st = new StringTokenizer(bf.readLine()," ");
		for(int i = 0; i < 32; i++) {
			if(i!=0 && i%8==0) {
				st = new StringTokenizer(bf.readLine()," ");
			}
			magnet[i] = Integer.parseInt(st.nextToken());
		}
	}//�Է¿Ϸ�
}