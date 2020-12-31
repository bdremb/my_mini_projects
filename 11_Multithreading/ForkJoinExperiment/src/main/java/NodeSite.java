import java.util.*;

public class NodeSite {
    private String url;
    private List<NodeSite> childrens = new ArrayList<>();
    private List<NodeSite> allUrls;

    public NodeSite(String url, List<NodeSite> allUrls) {
        this.url = url;
        this.allUrls = allUrls;
    }

    public NodeSite(String url) {
        this.url = url;
        allUrls = new ArrayList<>();
    }

    String getUrl() {
        return url;
    }

    public List<NodeSite> getChildrens() {
        List<NodeSite> childrenList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        for (NodeSite nodeSite : allUrls) {
            stringList.add(nodeSite.getUrl());
        }

        for (NodeSite node : childrens) {
            String str = node.getUrl();
            if (!stringList.contains(str)) {
                stringList.add(node.getUrl());
                childrenList.add(node);
            }
        }
        return childrenList;
    }

    public void setChildrens(List<NodeSite> childrens) {
        this.childrens = childrens;
    }
}
