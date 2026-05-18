import java.util.Scanner;

public class TranspositionCipher {

    // ==========================================
    // CHIFFREMENT
    // ==========================================
    public static String chiffrer(
            String message,
            int colonnes
    ) {

        // ==============================
        // CALCUL LIGNES
        // ==============================
        int lignes =
                (int)Math.ceil(
                        (double)message.length()
                                / colonnes
                );

        // ==============================
        // MATRICE
        // ==============================
        char[][] grille =
                new char[lignes][colonnes];

        int index = 0;

        // ==============================
        // REMPLISSAGE
        // ==============================
        for (int i = 0; i < lignes; i++) {

            for (int j = 0; j < colonnes; j++) {

                if (index < message.length()) {

                    grille[i][j] =
                            message.charAt(index);

                    index++;

                } else {

                    // Remplissage vide
                    grille[i][j] = 'X';
                }
            }
        }

        // ==============================
        // AFFICHAGE GRILLE
        // ==============================
        System.out.println(
                "\n===== GRILLE ====="
        );

        for (int i = 0; i < lignes; i++) {

            for (int j = 0; j < colonnes; j++) {

                System.out.print(
                        grille[i][j] + " "
                );
            }

            System.out.println();
        }

        // ==============================
        // LECTURE COLONNES
        // ==============================
        String resultat = "";

        for (int j = 0; j < colonnes; j++) {

            for (int i = 0; i < lignes; i++) {

                resultat += grille[i][j];
            }
        }

        return resultat;
    }

    // ==========================================
    // DECHIFFREMENT
    // ==========================================
    public static String dechiffrer(
            String messageCrypte,
            int colonnes
    ) {

        int lignes =
                (int)Math.ceil(
                        (double)messageCrypte.length()
                                / colonnes
                );

        // ==============================
        // MATRICE
        // ==============================
        char[][] grille =
                new char[lignes][colonnes];

        int index = 0;

        // ==============================
        // REMPLISSAGE COLONNES
        // ==============================
        for (int j = 0; j < colonnes; j++) {

            for (int i = 0; i < lignes; i++) {

                if (index < messageCrypte.length()) {

                    grille[i][j] =
                            messageCrypte.charAt(index);

                    index++;
                }
            }
        }

        // ==============================
        // LECTURE LIGNES
        // ==============================
        String resultat = "";

        for (int i = 0; i < lignes; i++) {

            for (int j = 0; j < colonnes; j++) {

                resultat += grille[i][j];
            }
        }

        // Supprime X ajoutés
        return resultat.replaceAll("X+$", "");
    }

    // ==========================================
    // MAIN
    // ==========================================
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            // ==========================
            // MESSAGE
            // ==========================
            System.out.print(
                    "Entrer message : "
            );

            String message =
                    sc.nextLine();

            // Vérification
            if (message.trim().isEmpty()) {

                System.out.println(
                        "Message vide interdit."
                );

                return;
            }

            // ==========================
            // COLONNES
            // ==========================
            System.out.print(
                    "Nombre colonnes : "
            );

            int colonnes =
                    Integer.parseInt(
                            sc.nextLine()
                    );

            if (colonnes <= 1) {

                System.out.println(
                        "Colonnes invalides."
                );

                return;
            }

            // ==========================
            // CHIFFREMENT
            // ==========================
            String crypte =
                    chiffrer(
                            message,
                            colonnes
                    );

            // ==========================
            // DECHIFFREMENT
            // ==========================
            String dechiffre =
                    dechiffrer(
                            crypte,
                            colonnes
                    );

            // ==========================
            // RESULTATS
            // ==========================
            System.out.println(
                    "\n===== RESULTAT ====="
            );

            System.out.println(
                    "Message original :"
            );

            System.out.println(message);

            System.out.println(
                    "\nMessage crypté :"
            );

            System.out.println(crypte);

            System.out.println(
                    "\nMessage décrypté :"
            );

            System.out.println(dechiffre);

        } catch (Exception e) {

            System.out.println(
                    "Erreur : "
                            + e.getMessage()
            );
        }

        sc.close();
    }
}