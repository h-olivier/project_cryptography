// CesarAvancé.java
import java.util.Scanner;

public class CesarAvance {

    // Validation : accepte uniquement lettres (a-z, A-Z) et espaces
    public static boolean estMessageValide(String message) {
        for (char c : message.toCharArray()) {
            if (!Character.isLetter(c) && c != ' ') {
                return false; // Caractère spécial détecté → rejet
            }
        }
        return !message.isBlank(); // Refuser aussi les messages vides
    }

    // Chiffrement César avec k variable
    public static String chiffrer(String message, int k) {
        StringBuilder resultat = new StringBuilder();

        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                char chiffre = (char) ((c - base + k) % 26 + base);
                resultat.append(chiffre);
            } else {
                resultat.append(c); // Garder les espaces tels quels
            }
        }
        return resultat.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = "";
        int k = 0;

        // --- Saisie et validation du message ---
        while (true) {
            System.out.print("Entrez votre message (lettres et espaces uniquement) : ");
            message = scanner.nextLine();

            if (estMessageValide(message)) {
                break; // Message valide → on continue
            } else {
                System.out.println("❌ Erreur : le message ne doit contenir que des lettres et des espaces.");
            }
        }

        // --- Saisie et validation de k ---
        while (true) {
            System.out.print("Entrez la clé k (nombre entier entre 1 et 25) : ");

            if (scanner.hasNextInt()) {
                k = scanner.nextInt();
                scanner.nextLine(); // Vider le buffer

                if (k >= 1 && k <= 25) {
                    break; // k valide → on continue
                } else {
                    System.out.println("❌ Erreur : k doit être entre 1 et 25.");
                }
            } else {
                System.out.println("❌ Erreur : veuillez entrer un nombre entier.");
                scanner.nextLine(); // Vider le buffer en cas de mauvaise saisie
            }
        }

        // --- Chiffrement et affichage ---
        String messageChiffre = chiffrer(message, k);
        System.out.println("\n✅ Message original : " + message);
        System.out.println("✅ Clé utilisée     : k = " + k);
        System.out.println("✅ Message chiffré  : " + messageChiffre);

        scanner.close();
    }
}