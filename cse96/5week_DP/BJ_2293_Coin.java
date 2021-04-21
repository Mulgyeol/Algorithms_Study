import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine()," ");
		int n = Integer.parseInt(st.nextToken()); //동전개수
		int k = Integer.parseInt(st.nextToken()); //가치의 합
		int value[] = new int[100001];
		for(int i = 1; i <= n; i++) {
			int coin = Integer.parseInt(bf.readLine()); //동전의 가치
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
