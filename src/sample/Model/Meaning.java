package sample.Model;

public class Meaning {
    private String type;
    private String vietnam;
    private String example;

    public Meaning(String type, String vietnam, String example) {
        this.type = type;
        this.vietnam = vietnam;
        this.example = example;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVietnam() {
        return vietnam;
    }

    public void setVietnam(String vietnam) {
        this.vietnam = vietnam;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}
