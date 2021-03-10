package com.ssafy.March.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2718_Tile {

	
		public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		int tile[] = new int[1001];
		tile[0] = 1;//tile[0]�κ��� �־�� N��° Ÿ�Ͽ��� �߰��Ǵ� Ÿ���� ����� �߰���**
		tile[1] = 1;
		tile[2] = 5;
		tile[3] = 11;
		int tmp = 3;//3��ĭ �������ʱ�ȭ ��
		for(int i = 0; i < T; i++) {
			int N = Integer.parseInt(bf.readLine());
			if(N <= tmp) {//3��ĭ ���̰ų� ���� �ݺ������� ó���� ���
				System.out.println(tile[N]);
				continue;
			}
			else {//������ tmp���� ū N
				for(int j = tmp + 1; j <= N; j++) {//j: tmp������ ĭ���� �Էµ� N������ ĭ�� ���
					for(int k = j - 1; k >= 0; k--) {//k: j���� 1��ĭ ����
						int dist = j-k;
						if(k == j - 1) {      //��ĭ��
							tile[j] += tile[k];
						}
						else if(k == j - 2) { //2ĭ��
							tile[j] += tile[k]*4;
						}
						else if( dist % 2 == 0) { //¦��ĭ ������ ���         =
							tile[j] += tile[k]*3;//                =   
 						}
						else if( dist % 2 == 1) { //Ȧ��ĭ ������ ���
							tile[j] += tile[k]*2;
						}
					}
				}
			}
			System.out.println(tile[N]);
			tmp = N;
		}
	}
}
