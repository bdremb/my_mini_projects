import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class CustomRecursiveTask extends RecursiveTask<List<String>> {
    private final NodeSite node;

    public CustomRecursiveTask(NodeSite node) {
        this.node = node;
    }

    @Override
    protected List<String> compute() {
        String url = node.getUrl();
        List<CustomRecursiveTask> subTasks = new LinkedList<>();
        List<String> urls = new ArrayList<>();
        urls.add(url);

        setChildNodes(url);

        for (NodeSite child : node.getChildrens()) {
            CustomRecursiveTask task = new CustomRecursiveTask(child);
            task.fork();
            subTasks.add(task);
        }

        for (CustomRecursiveTask task : subTasks) {
            List<String> listFromTask = task.join();
            for (String urlFromTask : listFromTask) {
                urls.add("\t" + urlFromTask);
            }
        }
        return urls;
    }

    private void setChildNodes(String url) {
        List<NodeSite> childNodes = new ArrayList<>();
        try {
            TimeUnit.MILLISECONDS.sleep(130);
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByTag("a");
            for (Element element : elements) {
                String str = element.absUrl("href");
                if (str.matches(url + ".+/")) {
                    childNodes.add(new NodeSite(str, childNodes));
                }
            }
            node.setChildrens(childNodes);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
