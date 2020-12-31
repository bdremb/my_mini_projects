import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class ParseHtml {
    public static final String URL_ADDRESS = "https://lenta.ru/";
    public static final String IMAGE_SELECT = "img[src~=.png|.jpe?g|.gif]";

    public static void main(String[] args) {
        downloader(getListImg());
    }

    private static Set<String> getListImg() {
        Set<String> pictures = new HashSet<>();
        try {
            Document doc = Jsoup.connect(URL_ADDRESS).get();
            Elements elements = doc.select(IMAGE_SELECT);
            for (Element el : elements) {
                String picture = el.attr("abs:src");
                pictures.add(picture);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pictures;
    }

    private static void downloader(Set<String> pictures) {
        for (String string : pictures) {
            String pictureName = string.substring(string.lastIndexOf("/") + 1);
            String pathname = "image/" + pictureName;
            try {
                URL url = new URL(string);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                BufferedImage image = ImageIO.read(url);
                ImageIO.write(image, string.substring(string.lastIndexOf(".") + 1), new File(pathname));
                System.out.println(pictureName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
