package Cesar;

import java.util.Scanner;

public class AffineCipher {

//L’Affine Cipher est une amélioration du chiffrement de Julius Caesar.

    // ==========================================
    // VERIFIE SI LA CLE a EST VALIDE
    // ==========================================
    public static boolean cleValide(int a) {

        int[] valeurs = {
                1, 3, 5, 7, 9, 11,
                15, 17, 19, 21, 23, 25
        };

        for (int v : valeurs) {

            if (v == a) {

                return true;
            }
        }

        return false;
    }

    // ==========================================
    // INVERSE MODULAIRE
    // ==========================================
    public static int inverseModulaire(int a) {

        for (int i = 0; i < 26; i++) {

            if ((a * i) % 26 == 1) {

                return i;
            }
        }

        return -1;
    }

    // ==========================================
    // CHIFFREMENT
    // ==========================================
    public static String chiffrer(
            String message,
            int a,
            int b
    ) {

        String resultat = "";

        for (int i = 0; i < message.length(); i++) {

            char c = message.charAt(i);

            // ==============================
            // MINUSCULE
            // ==============================
            if (Character.isLowerCase(c)) {

                int x = c - 'a';

                int y = (a * x + b) % 26;

                resultat += (char)(y + 'a');
            }

            // ==============================
            // MAJUSCULE
            // ==============================
            else if (Character.isUpperCase(c)) {

                int x = c - 'A';

                int y = (a * x + b) % 26;

                resultat += (char)(y + 'A');
            }

            // ==============================
            // ESPACES
            // ==============================
            else {

                resultat += c;
            }
        }

        return resultat;
    }

    // ==========================================
    // DECHIFFREMENT
    // ==========================================
    public static String dechiffrer(
            String message,
            int a,
            int b
    ) {

        String resultat = "";

        // Inverse de a
        int inverseA = inverseModulaire(a);

        for (int i = 0; i < message.length(); i++) {

            char c = message.charAt(i);

            // ==============================
            // MINUSCULE
            // ==============================
            if (Character.isLowerCase(c)) {

                int y = c - 'a';

                int x =
                        (inverseA * (y - b + 26)) % 26;

                resultat += (char)(x + 'a');
            }

            // ==============================
            // MAJUSCULE
            // ==============================
            else if (Character.isUpperCase(c)) {

                int y = c - 'A';

                int x =
                        (inverseA * (y - b + 26)) % 26;

                resultat += (char)(x + 'A');
            }

            // ==============================
            // ESPACES
            // ==============================
            else {

                resultat += c;
            }
        }

        return resultat;
    }

    // ==========================================
    // MAIN
    // ==========================================
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String message;

        int a;
        int b;

        // ==================================
        // MESSAGE
        // ==================================
        while (true) {

            System.out.print(
                    "Entrer le message : "
            );

            message = sc.nextLine();

            if (message.trim().isEmpty()) {

                System.out.println(
                        "Message vide interdit."
                );

            } else {

                break;
            }
        }

        // ==================================
        // CLE a
        // ==================================
        while (true) {

            try {

                System.out.print(
                        "Entrer la clé a : "
                );

                a = Integer.parseInt(sc.nextLine());

                if (!cleValide(a)) {

                    System.out.println(
                            "Valeur invalide pour a."
                    );

                } else {

                    break;
                }

            } catch (Exception e) {

                System.out.println(
                        "Entrer un nombre valide."
                );
            }
        }

        // ==================================
        // CLE b
        // ==================================
        while (true) {

            try {

                System.out.print(
                        "Entrer la clé b : "
                );

                b = Integer.parseInt(sc.nextLine());

                if (b < 0 || b > 25) {

                    System.out.println(
                            "b doit être entre 0 et 25."
                    );

                } else {

                    break;
                }

            } catch (Exception e) {

                System.out.println(
                        "Entrer un nombre valide."
                );
            }
        }

        // ==================================
        // CHIFFREMENT
        // ==================================
        String crypte =
                chiffrer(message, a, b);

        // ==================================
        // DECHIFFREMENT
        // ==================================
        String dechiffre =
                dechiffrer(crypte, a, b);

        // ==================================
        // RESULTAT
        // ==================================
        System.out.println(
                "\n========================="
        );

        System.out.println(
                "Message original : "
                        + message
        );

        System.out.println(
                "Message crypté : "
                        + crypte
        );

        System.out.println(
                "Message décrypté : "
                        + dechiffre
        );

        sc.close();
    }
}