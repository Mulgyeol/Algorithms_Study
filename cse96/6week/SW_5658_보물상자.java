package March;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SW_5658_�������� {

	public static void main(String[] args) throws NumberFormatException, IOException {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			int T = Integer.parseInt(bf.readLine());
			ArrayList<String> cipher = new ArrayList<String>();
			StringBuilder sb = new StringBuilder();
			for(int tc = 1; tc <= T; tc++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				int N =  Integer.parseInt(st.nextToken());
				int K =  Integer.parseInt(st.nextToken());
				char code[] = new char[N];//�����Է¹��� �迭
				
				code = bf.readLine().toCharArray();
				
				int rotate = N/4; //ȸ�� �� - �� 4�и�
				boolean flag = false; //�ߺ����� Ȯ���ϱ����� flag����
				
				for(int i = 0; i < rotate; i++) {
					for(int j = 0; j < N; j++) {
						sb.append(code[j]);
						if(j%rotate == rotate-1) {
							flag = false; //flag false�� �ʱ�ȭ
							String e = sb.toString();
							if(cipher.size() > 0) {//�ߺ��˻� ����
								for(String s : cipher) {
									if( e.equals(s) ) {
										sb.setLength(0);
										flag = true; //�ߺ��� ��� true
										break;
									}
								}
							}//�ߺ� �˻� ��
							if(flag) continue;//�ߺ��ȰŸ� continue
							
							cipher.add(e);//list�� �߰�
							sb.setLength(0);
						}
					}//for N������
					//1ȸ��
					char c = code[0];
					for(int j = 1; j < N; j++) {
						code[j - 1] = code[j];
					}
					code[N - 1] = c;
				}//for�� ��ü ȸ���� -�Ϸ�
				
				//����
				Collections.sort(cipher, new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						//1B3 1B4 1C5 1C6
						for(int j = 0; j < rotate; j++) {
							if(o1.charAt(j) == o2.charAt(j)) continue;
							else return o2.charAt(j) - o1.charAt(j);
						}
						return 0;
					}
				});
				//���ĿϷ�
				
				//K��° ���
				String ans = cipher.get(K - 1);
				int result = 0;//���� ��
				for(int j = 0; j < ans.length(); j++) {
					char tmp = ans.charAt(j);
					int a = 0;//�� �ڸ����� 16���� ��ŭ�� �����ִ� �ӽ� ����
					if('A' <= tmp && 'F' >= tmp) {
						a = (int)(tmp - 'A') + 10;
					}
					else {
						a = (int)(tmp) - '0';
					}
					for(int k = 0; k < rotate - j - 1; k++) {
						a *= 16;
					}
					result += a;//���� �信 +
				}
				System.out.println("#" + tc + " " + result);
				cipher.clear();
			}
	}
}