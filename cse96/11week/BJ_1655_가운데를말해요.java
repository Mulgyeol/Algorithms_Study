package study.May;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class BJ_1655_��������ؿ� {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> minq = new PriorityQueue<Integer>(Collections.reverseOrder());
		PriorityQueue<Integer> maxq = new PriorityQueue<Integer>();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num1 = 0, num2 = 0, num3 = 0;
		int N = Integer.valueOf(bf.readLine());
		
		num1 = Integer.valueOf(bf.readLine());
		bw.write(num1 + "\n");
		if(N == 1) {
			bw.flush();
			bf.close();
			bw.close();
			return;
		}
		num2 = Integer.valueOf(bf.readLine());
		if(N == 2) {
			bw.write(Math.min(num1,num2) + "\n");
			bw.flush();
			bf.close();
			bw.close();
			return;
		}
		bw.write(Math.min(num1, num2) + "\n");
		int big = Math.max(num1, num2);
		maxq.add(big);
		int lit = Math.min(num1, num2);
		minq.add(lit);
		for(int i = 2; i < N; i++) {
			num1 = minq.peek();
			num2 = maxq.peek();
			num3 = Integer.valueOf(bf.readLine());
			
			int minqsize = minq.size();
			int maxqsize = maxq.size();
			
			//���� �� ���� ũ�� ��
			if(minqsize == maxqsize){//q�� ������ ������ �� ���� �߰����� ���
				if(num3 < num1) {//�Էµ� ���� �ּҷ� ����
					bw.write(num1 + "\n");
					minq.add(num3);
				}
				else if(num3 > num2) { //�Էµ� ���� �ִ�� �� ��
					bw.write(num2 + "\n");
					maxq.add(num3);
				}else {//�ԷµȰ��� ���ų� �����϶�
					bw.write(num3 + "\n");
					int mid = (num1+num2)/2;
					if(num3 > mid) maxq.add(num3);
					else minq.add(num3);
				}
			}
			else if(minqsize > maxqsize) {//minqũ�Ⱑ �� Ŭ��
				if(num3 < num1) {//�Էµ� ���� �ּҷ� ����
					num1 = minq.poll();
					bw.write(Math.max(num3, minq.peek()) + "\n");
					maxq.add(num1);//minq�� �ϳ� maxq�� �̵�
					minq.add(num3);//�Էµ� ���� minq��
				}
				else if(num3 > num2) { //�Էµ� ���� �ִ�� �� ��
					bw.write(num1 + "\n");
					maxq.add(num3);
				}else {//�ԷµȰ��� ���ų� �����϶�
					bw.write(num1 + "\n");
					maxq.add(num3);
				}
			}
			else {//maxqũ�Ⱑ �� Ŭ��
				if(num3 < num1) {//�Էµ� ���� �ּҷ� ����
					bw.write(num1 + "\n");
					minq.add(num3);
				}
				else if(num3 > num2) { //�Էµ� ���� �ִ�� �� ��
					num2 = maxq.poll();
					bw.write(Math.min(num2, maxq.peek()) + "\n");
					minq.add(num2);
					maxq.add(num3);
				}else {//�ԷµȰ��� ���ų� �����϶�
					bw.write(num3 + "\n");
					minq.add(num3);
				}
			}
			
		}
		bw.flush();
		bf.close();
		bw.close();
	}
}
