package study.May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * ���÷��̾� Ȧ�� ����
 * ���÷��̾� ¦�� ����
 */
public class BJ_20040_����Ŭ���� {
	static int N, M, ans, parents[];
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.valueOf(st.nextToken());//���� ����
		M = Integer.valueOf(st.nextToken());//������ ��
		
		parents = new int[N];//���� ������ŭ �迭 ����
		for(int i = 0; i < N; i++) {
			parents[i] = i;//�ʱ�ȭ ���� ����Ȱ��� ��� �ڱ��ڽ��� ��Ʈ���
		}
		
		for(int i = 1; i <= M; i++) {//���ʸ�ŭ �ݺ�
			st = new StringTokenizer(bf.readLine());
			int point1 = Integer.valueOf(st.nextToken());
			int point2 = Integer.valueOf(st.nextToken());
			//unionȮ��
			if(union(point1,point2)) {
				ans = i;//i��° ���ʿ� ����Ŭ ����
				break;
			}
		}
		System.out.println(ans);
		bf.close();
		return;
	}
	
	private static boolean union(int p1, int p2) {
		int aRoot = find(p1);//p1�� ��Ʈ���
		int bRoot = find(p2);//p2�� ��Ʈ���
		
		if(aRoot == bRoot) return true;//���ٸ� ����Ŭ�� ������.
		parents[bRoot] = aRoot; //p1-p2���� p1�� p2�� ��Ʈ��尡 ��
		return false;
	}
	
	private static int find(int n) {
		if(n == parents[n]) return n; //������� ���� ���� �ڽ��� ��Ʈ���
		return parents[n] = find(parents[n]);//������ ��Ʈ��带 ����. ��Ʈ��� ����
	}
}
