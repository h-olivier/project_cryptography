package Vigenere;
import java.util.Scanner;

public class VigenereBlocMod26 {

    // ==========================================
    // CHIFFREMENT
    // ==========================================
    public static String chiffrer(String message, int[] cle) {

        String resultat = "";

        int indexCle = 0;

        // Parcours du message
        for (int i = 0; i < message.length(); i++) {

            char c = message.charAt(i);

            // ==========================
            // SI C'EST UNE LETTRE
            // ==========================
            if (Character.isLetter(c)) {

                // Clé actuelle
                int k = cle[indexCle % 4];

                // Gestion minuscule
                if (Character.isLowerCase(c)) {

                    // a = 97
                    char chiffre = (char) (((c - 'a' + k) % 26) + 'a');

                    resultat += chiffre;

                }
                // Gestion majuscule
                else if (Character.isUpperCase(c)) {

                    // A = 65
                    char chiffre = (char) (((c - 'A' + k) % 26) + 'A');

                    resultat += chiffre;
                }

                // Passe à la clé suivante
                indexCle++;
            }

            // ==========================
            // SI ESPACE
            // ==========================
            else if (c == ' ') {

                resultat += ' ';
            }
        }

        return resultat;
    }

    // ==========================================
    // DECHIFFREMENT
    // ==========================================
    // public static String dechiffrer(String message, int[] cle) {

    //     String resultat = "";

    //     int indexCle = 0;

    //     // Parcours
    //     for (int i = 0; i < message.length(); i++) {

    //         char c = message.charAt(i);

    //         // ==========================
    //         // SI LETTRE
    //         // ==========================
    //         if (Character.isLetter(c)) {

    //             int k = cle[indexCle % 4];

    //             // Minuscule
    //             if (Character.isLowerCase(c)) {

    //                 char dechiffre =
    //                         (char) (((c - 'a' - k + 26) % 26) + 'a');

    //                 resultat += dechiffre;
    //             }

    //             // Majuscule
    //             else if (Character.isUpperCase(c)) {

    //                 char dechiffre =
    //                         (char) (((c - 'A' - k + 26) % 26) + 'A');

    //                 resultat += dechiffre;
    //             }

    //             indexCle++;
    //         }

    //         // ==========================
    //         // ESPACE
    //         // ==========================
    //         else if (c == ' ') {

    //             resultat += ' ';
    //         }
    //     }

    //     return resultat;
    // }

    // ==========================================
    // AFFICHAGE BLOCS
    // ==========================================
    public static void afficherBlocs(String texte) {

        int compteur = 0;

        System.out.println("\n===== BLOCS =====");

        for (int i = 0; i < texte.length(); i++) {

            char c = texte.charAt(i);

            System.out.print(c);

            // Ignore les espaces pour les blocs
            if (c != ' ') {

                compteur++;

                if (compteur == 4) {

                    System.out.println();
                    compteur = 0;
                }
            }
        }

        System.out.println();
    }

    // ==========================================
    // MAIN
    // ==========================================
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // ==========================
        // MESSAGE
        // ==========================
        System.out.print("Entrer le message : ");

        String message = scanner.nextLine();

        // ==========================
        // CLES
        // ==========================
        int[] cle = new int[4];

        System.out.println("Entrer 4 clés :");

        for (int i = 0; i < 4; i++) {

            System.out.print("Clé " + (i + 1) + " : ");

            cle[i] = scanner.nextInt();
        }

        // ==========================
        // CHIFFREMENT
        // ==========================
        String crypte = chiffrer(message, cle);

        // ==========================
        // DECHIFFREMENT
        // ==========================
        // String dechiffre = dechiffrer(crypte, cle);

        // ==========================
        // AFFICHAGE
        // ==========================
        System.out.println("\n======================");

        System.out.println("MESSAGE ORIGINAL :");
        System.out.println(message);

        afficherBlocs(message);

        System.out.println("\nCLÉS UTILISÉES :");

        for (int k : cle) {

            System.out.print(k + " ");
        }

        System.out.println("\n\nMESSAGE CHIFFRÉ :");
        System.out.println(crypte);

        afficherBlocs(crypte);

        // System.out.println("\nMESSAGE DÉCHIFFRÉ :");
        // System.out.println(dechiffre);

        scanner.close();
    }
}