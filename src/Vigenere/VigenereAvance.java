package Vigenere;
import java.util.Scanner;

public class VigenereAvance {

    // =========================
    // CHIFFREMENT
    // =========================
    public static String chiffrer(String message, String cle) {

        StringBuilder resultat = new StringBuilder();

        int indexCle = 0;

        for (int i = 0; i < message.length(); i++) {

            char c = message.charAt(i);

            // Vérifie si c'est une lettre
            if (Character.isLetter(c)) {

                // Détermine la base
                char base = Character.isUpperCase(c) ? 'A' : 'a';

                // Lettre de la clé
                char lettreCle = Character.toUpperCase(
                        cle.charAt(indexCle % cle.length())
                );

                // Décalage
                int decalage = lettreCle - 'A';

                // Chiffrement
                char chiffre = (char) (
                        (c - base + decalage) % 26 + base
                );

                resultat.append(chiffre);

                indexCle++;

            } else {

                // Conserver espaces et symboles
                resultat.append(c);
            }
        }

        return resultat.toString();
    }

    // =========================
    // DECHIFFREMENT
    // =========================
    public static String dechiffrer(String message, String cle) {

        StringBuilder resultat = new StringBuilder();

        int indexCle = 0;

        for (int i = 0; i < message.length(); i++) {

            char c = message.charAt(i);

            if (Character.isLetter(c)) {

                char base = Character.isUpperCase(c) ? 'A' : 'a';

                char lettreCle = Character.toUpperCase(
                        cle.charAt(indexCle % cle.length())
                );

                int decalage = lettreCle - 'A';

                // Déchiffrement
                char dechiffre = (char) (
                        (c - base - decalage + 26) % 26 + base
                );

                resultat.append(dechiffre);

                indexCle++;

            } else {

                resultat.append(c);
            }
        }

        return resultat.toString();
    }

    // =========================
    // PROGRAMME PRINCIPAL
    // =========================
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Saisie message
        System.out.print("Entrez le message : ");
        String message = scanner.nextLine();

        // Saisie clé
        System.out.print("Entrez la clé : ");
        String cle = scanner.nextLine();

        // Vérification clé
        if (!cle.matches("[a-zA-Z]+")) {

            System.out.println(
                    "Erreur : la clé doit contenir uniquement des lettres."
            );

        }

        // Chiffrement
        String messageChiffre = chiffrer(message, cle);

        // Déchiffrement
        String messageDechiffre = dechiffrer(messageChiffre, cle);

        // Affichage
        System.out.println("\n===== RESULTATS =====");

        System.out.println("Message original   : " + message);

        System.out.println("Clé utilisée       : " + cle);

        System.out.println("Message chiffré    : " + messageChiffre);

        System.out.println("Message déchiffré  : " + messageDechiffre);

        scanner.close();
    }
}