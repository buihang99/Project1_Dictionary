package sample.Model;

public class Word {
    private String english_Word;
    private String vietnamese;
    private String type;
    private String example;

    public Word(){
    }

    public Word(String english, String vietnamese, String type, String example){
        this.english_Word = english;
        this.vietnamese = vietnamese;
        this.type = type;
        this.example = example;
    }

    public String getEnglish_Word() {
        return english_Word;
    }

    public void setEnglish_Word(String english_Word) {
        this.english_Word = english_Word;
    }

    public String getVietnamese() {
        return vietnamese;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVietnamese(String vietnamese) {
        this.vietnamese = vietnamese;
    }
}
