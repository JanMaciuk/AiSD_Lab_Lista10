import java.util.ArrayList;

public class Word {
    protected String text;
    protected ArrayList<Integer> lines = new ArrayList<>();
    protected int AlphabeticOrder;

    public Word(String text, int line) {
        this.text = text;
        lines.add(line);
        // Typ to int, więc automatycznie przekonwertuje pierwszy znak do ASCII.
        AlphabeticOrder = text.toLowerCase().charAt(0);
        // Używam toLowerCase, bo kody ASCII dla wielkich liter są inne.
    }

    @Override // Jeżeli obiekt słowo przechowuje ten sam String słowo to jest identyczny.
    public boolean equals(Object obj) {
        if (obj instanceof Word word) {
            return text.equals(word.text);
        }
        return false;
    }


}
