import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        readToTree("tekst2.txt",tree);
        tree.makeStraight(); // Wyrównuje drzewo tak aby ładnie się drukowało (jak na liście zadań)
        tree.print();
        tree.levelPrint();
        tree.delete("parasol");
        tree.print();
        tree.levelPrint();

    }

    public static void readToTree(String filename, BinarySearchTree tree) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {
                String[] words = line.replaceAll("[^\\p{L} ]", "").split("\\s+");
                // \p{L} - dowolna litera Unicode
                // \s - białe znaki, "+" sprawia że ignorujemy jeżeli jest ich kilka z rzędu.


                for (String word : words) {
                    Word newWord = new Word(word, lineNumber);

                    Word existingWord = tree.find(newWord.text);
                    if (Objects.isNull(existingWord)) {
                        tree.insert(newWord);
                    } else {
                        if (!existingWord.lines.contains(lineNumber)) {
                            // Nie powtarzam numerów linii.
                            existingWord.lines.add(lineNumber);
                        }

                    }

                }

                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}