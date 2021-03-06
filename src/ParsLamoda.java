import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsLamoda {
    private String fileLink;
    private String reg_name = "(?<=alt=\").*(?=, цвет:)";
    private String reg_price = "(?<=;)\\d+(?=&quot;}])";
    private String reg_link = "(?<=<view-source:).*www\\.lamoda\\.ru\\/p\\/.*(?=>)";

    public ParsLamoda(String fileLink) {
        this.fileLink = fileLink;
    }

    public ArrayList<String> getProductData() throws IOException {
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> prices = new ArrayList<>();
        ArrayList<String> links = new ArrayList<>();
        ArrayList<String> products = new ArrayList<>();
        ArrayList<String> textFile = (ArrayList<String>) Files.readAllLines(Paths.get(fileLink));
        String s;
        for (String product : textFile) {
            s = getProduct(product, reg_name);
            if (isCorrectString(s)) {
                names.add(withoutCommas(s));
            }
            s = getProduct(product, reg_price);
            if (isCorrectString(s)) {
                prices.add(s);
            }
            s = getProduct(product, reg_link);
            if (isCorrectString(s)) {
                links.add(s);
            }
        }

        if ((names.size() == prices.size()) && (names.size() == links.size())) {
            for (int i = 0; i < names.size(); i++) {
                products.add(names.get(i) + ", " + prices.get(i) + " rub, " + links.get(i));
            }
        }

        return products;
    }

    private String getProduct(String product, String reg) {
        Matcher pr = Pattern.compile(reg).matcher(product);
        while (pr.find()) {
            String s = product.substring(pr.start(), pr.end());
            if (isCorrectString(s)) {
                return s;
            }
        }
        return "";
    }

    private boolean isCorrectString(String s) {
        if (s.length() > 0) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != ' ') {
                    return true;
                }
            }
        }
        return false;
    }

    private String withoutCommas(String s) {
        String a = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ',') {
                a += s.charAt(i);
            }
        }
        return a;
    }
}
