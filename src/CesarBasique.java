import java.util.Scanner;

public class CesarBasique {

    public static String chiffrer(String message) {
        int k = 3; // clé fixe
        StringBuilder resultat = new StringBuilder();

        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                char chiffre = (char) ((c - base + k) % 26 + base);
                resultat.append(chiffre);
            } else {
                resultat.append(c); // espaces conservés
            }
        }
        return resultat.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez votre message : ");
        String message = scanner.nextLine();

        String messageChiffre = chiffrer(message);

        System.out.println("Message original : " + message);
        System.out.println("Message chiffre  : " + messageChiffre);
        System.out.println("Cle utilisee     : k = 3 (fixe)");

        scanner.close();
    }
}