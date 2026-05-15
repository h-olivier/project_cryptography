import java.util.Scanner;

public class XORSecure {

    // ==========================================
    // FONCTION XOR
    // ==========================================
    public static String xor(String texte, int cle) {

        String resultat = "";

        // Parcours du texte
        for (int i = 0; i < texte.length(); i++) {

            char c = texte.charAt(i);

            // XOR
            char x = (char)(c ^ cle);

            resultat += x;
        }

        return resultat;
    }

    // ==========================================
    // MAIN
    // ==========================================
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String message = "";
        int cle = 0;

        // ==========================================
        // RECUPERATION MESSAGE
        // ==========================================
        while (true) {

            System.out.print("Entrer le message : ");

            message = sc.nextLine();

            // Vérifie message vide
            if (message.trim().isEmpty()) {

                System.out.println(
                    "Erreur : message vide interdit."
                );

            } else {

                break;
            }
        }

        // ==========================================
        // RECUPERATION CLE
        // ==========================================
        while (true) {

            try {

                System.out.print(
                    "Entrer une clé XOR (1-255) : "
                );

                cle = Integer.parseInt(sc.nextLine());

                // Vérifie intervalle
                if (cle < 1 || cle > 255) {

                    System.out.println(
                        "Erreur : clé doit être entre 1 et 255."
                    );

                } else {

                    break;
                }

            } catch (NumberFormatException e) {

                System.out.println(
                    "Erreur : entrer uniquement un nombre."
                );
            }
        }

        // ==========================================
        // CHIFFREMENT
        // ==========================================
        String crypte = xor(message, cle);

        // ==========================================
        // DECHIFFREMENT
        // ==========================================
        String dechiffre = xor(crypte, cle);

        // ==========================================
        // AFFICHAGE
        // ==========================================
        System.out.println("\n========================");

        System.out.println("Message original :");
        System.out.println(message);

        System.out.println("\nClé XOR : " + cle);

        System.out.println("\nMessage crypté :");
        System.out.println(crypte);

        System.out.println("\nMessage décrypté :");
        System.out.println(dechiffre);

        sc.close();
    }
}