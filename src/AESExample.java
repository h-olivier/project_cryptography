import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;
import java.util.Scanner;

public class AESExample {

    // ==========================================
    // CHIFFREMENT AES
    // ==========================================
    public static String chiffrer(
            String message,
            String cle
    ) throws Exception {

        // Clé 16 bytes
        SecretKeySpec key =
                new SecretKeySpec(
                        cle.getBytes(),
                        "AES"
                );

        Cipher cipher =
                Cipher.getInstance("AES");

        cipher.init(
                Cipher.ENCRYPT_MODE,
                key
        );

        byte[] crypte =
                cipher.doFinal(
                        message.getBytes()
                );

        return Base64.getEncoder()
                .encodeToString(crypte);
    }

    // ==========================================
    // DECHIFFREMENT AES
    // ==========================================
    public static String dechiffrer(
            String messageCrypte,
            String cle
    ) throws Exception {

        SecretKeySpec key =
                new SecretKeySpec(
                        cle.getBytes(),
                        "AES"
                );

        Cipher cipher =
                Cipher.getInstance("AES");

        cipher.init(
                Cipher.DECRYPT_MODE,
                key
        );

        byte[] decode =
                Base64.getDecoder()
                        .decode(messageCrypte);

        byte[] resultat =
                cipher.doFinal(decode);

        return new String(resultat);
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

            // ==========================
            // CLE
            // ==========================
            System.out.print(
                    "Entrer clé 16 caractères : "
            );

            String cle =
                    sc.nextLine();

            // Vérifie longueur
            if (cle.length() != 16) {

                System.out.println(
                        "La clé doit contenir 16 caractères."
                );

                return;
            }

            // ==========================
            // CHIFFREMENT
            // ==========================
            String crypte =
                    chiffrer(message, cle);

            // ==========================
            // DECHIFFREMENT
            // ==========================
            String dechiffre =
                    dechiffrer(crypte, cle);

            // ==========================
            // RESULTAT
            // ==========================
            System.out.println(
                    "\n===== AES ====="
            );

            System.out.println(
                    "Message original : "
                            + message
            );

            System.out.println(
                    "\nMessage crypté : "
                            + crypte
            );

            System.out.println(
                    "\nMessage décrypté : "
                            + dechiffre
            );

        } catch (Exception e) {

            System.out.println(
                    "Erreur : "
                            + e.getMessage()
            );
        }

        sc.close();
    }
}