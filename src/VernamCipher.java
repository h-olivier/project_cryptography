import java.util.Base64;
import java.util.Scanner;

public class VernamCipher {

    // ==========================================
    // XOR TEXTE
    // ==========================================
    public static byte[] xor(
            byte[] message,
            byte[] cle
    ) {

        byte[] resultat =
                new byte[message.length];

        // Parcours
        for (int i = 0; i < message.length; i++) {

            // Répète automatiquement la clé
            resultat[i] =
                    (byte)(message[i]
                    ^ cle[i % cle.length]);
        }

        return resultat;
    }

    // ==========================================
    // MAIN
    // ==========================================
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ==============================
        // MESSAGE
        // ==============================
        System.out.print(
                "Entrer le message : "
        );

        String message = sc.nextLine();

        // ==============================
        // CLE
        // ==============================
        System.out.print(
                "Entrer la clé texte : "
        );

        String cle = sc.nextLine();

        // Vérification
        if (message.trim().isEmpty()
                || cle.trim().isEmpty()) {

            System.out.println(
                    "Message ou clé vide."
            );

            return;
        }

        // ==============================
        // CHIFFREMENT
        // ==============================
        byte[] crypte =
                xor(
                        message.getBytes(),
                        cle.getBytes()
                );

        // Base64
        String base64 =
                Base64.getEncoder()
                        .encodeToString(crypte);

        // ==============================
        // DECHIFFREMENT
        // ==============================
        byte[] dechiffre =
                xor(
                        crypte,
                        cle.getBytes()
                );

        // ==============================
        // RESULTAT
        // ==============================
        System.out.println(
                "\n===== RESULTAT ====="
        );

        System.out.println(
                "Message original : "
                        + message
        );

        System.out.println(
                "Clé : "
                        + cle
        );

        System.out.println(
                "\nMessage crypté Base64 :"
        );

        System.out.println(base64);

        System.out.println(
                "\nMessage décrypté :"
        );

        System.out.println(
                new String(dechiffre)
        );

        sc.close();
    }
}