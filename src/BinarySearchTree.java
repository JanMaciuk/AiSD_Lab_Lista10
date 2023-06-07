import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {

    class Node {
        Word element; // Wartość węzła
        Node left; // Wskaźnik na mniejszy węzeł
        Node right; // Wskaźnik na większy węzeł


        public Node(Word element)
        {
            this.element = element;
            left = null;
            right = null;
        }
    }


    Node root = null; // Korzeń drzewa, na początku drzewo jest puste

    // Usuwanie zmienia drzewo, zwracam nowy korzeń.
    public void delete(String text) {
        root = deleteNode(root, new Word(text, 0));
        System.out.println("Usuwanie słowa " + text+ "\n");
    }

    private Node deleteNode(Node node, Word element)
    {
        // node to korzeń sprawdzanego poddrzewa, na początku całe drzewo, potem prawe/lewe itd.
        // Sprawdzam, czy drzewo nie jest puste lub nie doszliśmy do pozycji nieistniejącego elementu.
        if (node == null)  { return null; }

        //Jeżeli szukana wartość jest inna od wartości obecnego elementu, to szukamy w poddrzewach.
        if (element.AlphabeticOrder < node.element.AlphabeticOrder)  { node.left = deleteNode(node.left, element); }
        else if (element.AlphabeticOrder > node.element.AlphabeticOrder)  { node.right = deleteNode(node.right, element); }

            // Jeżeli wartość nie jest różna, to znaleźliśmy element do usunięcia.
        else {

            // Proste sytuacje z tylko jednym potomkiem lub brakiem potomków.
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;


            // Dla dwóch potomków zamieniam wartość z następnikiem (najmniejszym większym elementem), (poprzez wielkość elementu rozumiem kolejność alfabetyczną)
            // Szukam wartość najmniejszego większego elementu (idę raz w prawo a potem cały czas w lewo)
            Node checkedNode = node.right;
            Word smallestBiggerelement = checkedNode.element;
            while (checkedNode.left != null) {
                smallestBiggerelement = checkedNode.left.element;
                checkedNode = checkedNode.left;
            }
            // Zamieniam wartość z następnikiem
            node.element = smallestBiggerelement;

            //Usuwam Node następnika (jego wartość jest skopiowana do obecnego elementu)
            node.right = deleteNode(node.right, node.element);
        }

        return node;
    }



    public void insert(Word element) { root = insertNode(root, element); }


    private Node insertNode(Node node, Word element)
    {
        // Kiedy dojdę do końca drzewa, dodaje nowy węzeł.
        if (node == null) {
            node = new Node(element);
            return node;
        }

        // przechodzę do odpowiedniego poddrzewa, jeżeli będzie ono null, to dodam nowy węzeł.
        if (element.AlphabeticOrder < node.element.AlphabeticOrder)
            node.left = insertNode(node.left, element);
        else if (element.AlphabeticOrder > node.element.AlphabeticOrder)
            node.right = insertNode(node.right, element);

        //Nie uwzględniam duplikatów, więc jeżeli wartość już istnieje, to nic nie robię.
        // zwracam drzewo
        return node;
    }



    public void print() {
        System.out.println("Kolejność alfabetyczna:");
        InOrderWalkPrint(root);
        System.out.println();
    }

    private void InOrderWalkPrint(Node root)
    { //Przegląd in-order, najpierw idę do końca w lewo po drzewie, wyświetlam wartość, i przechodzę do prawej wartości.
        if (root != null) {
            InOrderWalkPrint(root.left);
            System.out.println(root.element.text + "  -" + root.element.lines + " "); // Todo, tyle spacji żeby wyrównać tekst jak na liście zadań.
            InOrderWalkPrint(root.right);
        }
    }
    public void makeStraight() {
        int longest = longestString(root);
        InOrderWalkAddSpaces(root, longest);

    }

    private void InOrderWalkAddSpaces(Node root, int longest)
    { //Przegląd in-order, najpierw idę do końca w lewo po drzewie, wyświetlam wartość, i przechodzę do prawej wartości.
        if (root != null) {
            while(root.element.text.length() < longest) {
                root.element.text = root.element.text + " ";
            }

            InOrderWalkAddSpaces(root.left, longest);
            InOrderWalkAddSpaces(root.right, longest);
        }
    }
    private int longestString(Node root) {
        if (root == null) {
            return 0;
        }
        int left = longestString(root.left);
        int right = longestString(root.right);
        int max = Math.max(left, right);
        return Math.max(max, root.element.text.length());
    }

    public Word find(String text) {
        return findNode(root, text);
    }

    private Word findNode(Node node, String text)
    {
        // Jeżeli dostałem null pointer to nie ma takiego elementu w drzewie.
        if (node == null) {
            return null;
        }

        // Jeżeli alfabetyczna kolejność jest mniejsza, to szukam w lewym poddrzewie.
        if (text.compareToIgnoreCase(node.element.text) < 0) {
            return findNode(node.left, text);
        }
        // Jeżeli alfabetyczna kolejność jest większa, to szukam w prawym poddrzewie.
        else if (text.compareToIgnoreCase(node.element.text) > 0) {
            return findNode(node.right, text);
        }
        // Jeżeli alfabetyczna kolejność jest równa, to mam ten sam napis, czyli znalazłem element.
        else {
            return node.element;
        }
    }


    public void levelPrint(){
        System.out.println("Przegląd poziomami / Przejście drzewa wszerz: ");
        LeveledWalkPrint(root);
        System.out.println();
    }
    private static void LeveledWalkPrint(Node node) {
        Queue<Node> kolejka = new LinkedList<>();
        kolejka.add(node);
        while(!kolejka.isEmpty()){
            if (kolejka.peek().left != null) kolejka.add(kolejka.peek().left);
            if (kolejka.peek().right != null) kolejka.add(kolejka.peek().right);
            Word printed = kolejka.remove().element;
            System.out.println(printed.text+ " " + printed.lines);
        }
    }




}