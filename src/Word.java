import java.util.ArrayList;

public class Word {
    protected String text;
    protected ArrayList<Integer> lines = new ArrayList<>();

    public Word(String text, int line) {
        this.text = text;
        lines.add(line);
    }

    @Override // Jeżeli obiekt słowo przechowuje ten sam String słowo to jest identyczny.
    public boolean equals(Object obj) {
        if (obj instanceof Word word) {
            return text.equals(word.text);
        }
        return false;
    }


}
