import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ParsLamoda womenClothes = new ParsLamoda("clothes_lamoda.txt");
        ArrayList<String> list =  womenClothes.getProductData();

        for (String i: list) {
            System.out.println(i);
        }
    }
}
