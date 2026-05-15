import java.util.Scanner;

public class HillCipher {

    // ==========================================
    // MATRICE CLE 2x2
    // ==========================================
    static int[][] cle = {
            {3, 3},
            {2, 5}
    };

    // ==========================================
    // CHIFFREMENT
    // ==========================================
    public static String chiffrer(String message) {

        // Met en majuscule
        message = message.toUpperCase();

        // Supprime espaces
        message = message.replaceAll(" ", "");

        // Si taille impaire ajoute X
        if (message.length() % 2 != 0) {

            message += "X";
        }

        String resultat = "";

        // Parcours 2 lettres par 2 lettres
        for (int i = 0; i < message.length(); i += 2) {

            // ==================================
            // TRANSFORMATION LETTRES -> NOMBRES
            // ==================================
            int a = message.charAt(i) - 'A';

            int b = message.charAt(i + 1) - 'A';

            // ==================================
            // MULTIPLICATION MATRICE
            // ==================================
            int c1 =
                    (cle[0][0] * a +
                     cle[0][1] * b) % 26;

            int c2 =
                    (cle[1][0] * a +
                     cle[1][1] * b) % 26;

            // ==================================
            // NOMBRES -> LETTRES
            // ==================================
            resultat += (char)(c1 + 'A');

            resultat += (char)(c2 + 'A');
        }

        return resultat;
    }

    // ==========================================
    // MAIN
    // ==========================================
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ==================================
        // MESSAGE
        // ==================================
        System.out.print("Entrer le message : ");

        String message = sc.nextLine();

        // ==================================
        // CHIFFREMENT
        // ==================================
        String crypte = chiffrer(message);

        // ==================================
        // AFFICHAGE
        // ==================================
        System.out.println("\n===== HILL CIPHER =====");

        System.out.println("Message original : "
                + message);

        System.out.println("Message crypté : "
                + crypte);

        sc.close();
    }
}