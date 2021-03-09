package com.ssafy.March.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2293_coin1 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine()," ");
		int n = Integer.parseInt(st.nextToken()); //��������
		int k = Integer.parseInt(st.nextToken()); //��ġ�� ��
		int value[] = new int[100001];   //���� ���ǿ� ������ ��ġ�� �ʸ�����...............;
		for(int i = 1; i <= n; i++) {
			int coin = Integer.parseInt(bf.readLine()); //������ ��ġ
			int index = coin;
			value[coin]++;
			while(index <= k) {
				value[index] += value[index-coin];
				index++;
			}
		}
		System.out.println(value[k]);
	}

}
