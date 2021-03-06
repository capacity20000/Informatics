import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ParsLamoda womenClothes = new ParsLamoda("clothes_lamoda.txt");
        ArrayList<String> list =  womenClothes.getProductData();

        print(list);
    }

    public static void print(ArrayList<String> l) {
        int k, m, llen = 2;
        int[][] len = new int[l.size()][llen];
        int[] maxLens = {0, 0, 0};

        for (int i = 0; i < l.size(); i++) {
            m = 0;
            k = 0;
            String s = l.get(i);
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == ',') {
                    len[i][m] = j - k;
                    k = len[i][m];
                    m++;
                }
            }
        }

        for (int i = 0; i < l.size(); i++) {
            for (int j = 0; j < llen; j++) {
                if (len[i][j] > maxLens[j]) {
                    maxLens[j] = len[i][j];
                }
            }
        }

        for (int i = 0; i < l.size(); i++) {
            String s = l.get(i);
            m = 0;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == ',') {
                    for (int f = 0; f < maxLens[m] - len[i][m]; f++) {
                        System.out.print(" ");
                    }
                    System.out.print(" | ");
                    m++;
                } else {
                    System.out.print(s.charAt(j));
                }
            }
            System.out.println();
        }
    }
}
