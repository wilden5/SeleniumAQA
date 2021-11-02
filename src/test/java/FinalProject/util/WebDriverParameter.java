package FinalProject.util;

public class WebDriverParameter {

    private String name;
    private Boolean isRemote;
    private String url;

    public WebDriverParameter(String name, Boolean isRemote) {
        this.name = name;
        this.isRemote = isRemote;
    }

    public WebDriverParameter(String name, Boolean isRemote, String url) {
        this.name = name;
        this.isRemote = isRemote;
        this.url = url;
    }

    public Boolean getRemote() {
        return isRemote;
    }

    public void setRemote(Boolean remote) {
        isRemote = remote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}