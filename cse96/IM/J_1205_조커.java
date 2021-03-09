	package com.ssafy.IM;

	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.StringTokenizer;
	
	public class J_1205_��Ŀ {
		static int N, card[], select[], max_straight = 0, tmp, zero = 0;;
		static boolean isSelected[];
		public static void main(String[] args) throws NumberFormatException, IOException {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			N = Integer.parseInt(bf.readLine()); //ī�� ����
			StringTokenizer st = new StringTokenizer(bf.readLine()," ");
			card = new int[N];
			select = new int[N];
			isSelected = new boolean[N];
			for(int i = 0;i < N; i++) {
				card[i] = Integer.parseInt(st.nextToken());
				if(card[i] == 0) zero++;
			}
			
			permutation(0);
			System.out.println(max_straight);
		}
	
		private static void permutation(int cnt) {
			if(cnt == N) {
				checkStraight();
				//System.out.println(Arrays.toString(select));
				return;
			}
			
			for(int i = 0; i < N; i++) {
				if(isSelected[i]) continue;
				else {
					isSelected[i] = true;
					select[cnt] = card[i];
					permutation(cnt + 1);
					isSelected[i] = false;
				}
			}
		}
	
		private static void checkStraight() {
			int straight = 1;
			tmp = -3;
			int z = zero;
			for(int i = 0; i < N - 1; i++) {
				if(select[i] != 0) tmp = select[i];
				if( (i == 0 && select[i] == 0) || (tmp == (select[i+1] - 1)) || select[i+1] == 0 ) {
					straight++;
					if(i == N-2) max_straight = Math.max(straight, max_straight);// �ݺ��� �� ���� �� ��
				}
//				else if() {
//					
//				}
				else {
					max_straight = Math.max(straight, max_straight);//��Ʈ����Ʈ�� ������ ��
					straight = 1;
				}
			}
		}
	}
