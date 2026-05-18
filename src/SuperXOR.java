import java.security.MessageDigest;
import java.security.SecureRandom;

import java.util.Base64;
import java.util.Scanner;

public class SuperXOR {

    // ==========================================
    // SHA-256
    // ==========================================
    public static byte[] sha256(
            String texte
    ) throws Exception {

        MessageDigest md =
                MessageDigest.getInstance(
                        "SHA-256"
                );

        return md.digest(
                texte.getBytes("UTF-8")
        );
    }

    // ==========================================
    // XOR
    // ==========================================
    public static byte[] xor(
            byte[] data,
            byte[] key
    ) {

        byte[] resultat =
                new byte[data.length];

        for (int i = 0; i < data.length; i++) {

            resultat[i] =
                    (byte)(
                            data[i]
                            ^ key[i % key.length]
                    );
        }

        return resultat;
    }

    // ==========================================
    // CHIFFREMENT
    // ==========================================
    public static String chiffrer(
            String message,
            String password
    ) throws Exception {

        // ==============================
        // SHA-256 PASSWORD
        // ==============================
        byte[] cle =
                sha256(password);

        // ==============================
        // IV ALEATOIRE
        // ==============================
        byte[] iv = new byte[16];

        SecureRandom random =
                new SecureRandom();

        random.nextBytes(iv);

        // ==============================
        // MESSAGE UTF-8
        // ==============================
        byte[] messageBytes =
                message.getBytes("UTF-8");

        // ==============================
        // XOR CLE
        // ==============================
        byte[] etape1 =
                xor(messageBytes, cle);

        // ==============================
        // XOR IV
        // ==============================
        byte[] etape2 =
                xor(etape1, iv);

        // ==============================
        // IV + DONNEES
        // ==============================
        byte[] finalData =
                new byte[
                        iv.length +
                        etape2.length
                ];

        System.arraycopy(
                iv,
                0,
                finalData,
                0,
                iv.length
        );

        System.arraycopy(
                etape2,
                0,
                finalData,
                iv.length,
                etape2.length
        );

        // Base64
        return Base64.getEncoder()
                .encodeToString(finalData);
    }

    // ==========================================
    // DECHIFFREMENT
    // ==========================================
    public static String dechiffrer(
            String messageCrypte,
            String password
    ) throws Exception {

        // ==============================
        // SHA-256 PASSWORD
        // ==============================
        byte[] cle =
                sha256(password);

        // ==============================
        // BASE64 -> BYTES
        // ==============================
        byte[] data =
                Base64.getDecoder()
                        .decode(messageCrypte);

        // ==============================
        // EXTRACTION IV
        // ==============================
        byte[] iv = new byte[16];

        System.arraycopy(
                data,
                0,
                iv,
                0,
                16
        );

        // ==============================
        // EXTRACTION MESSAGE
        // ==============================
        byte[] crypte =
                new byte[data.length - 16];

        System.arraycopy(
                data,
                16,
                crypte,
                0,
                crypte.length
        );

        // ==============================
        // XOR IV
        // ==============================
        byte[] etape1 =
                xor(crypte, iv);

        // ==============================
        // XOR CLE
        // ==============================
        byte[] resultat =
                xor(etape1, cle);

        return new String(
                resultat,
                "UTF-8"
        );
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

            if (message.trim().isEmpty()) {

                System.out.println(
                        "Message vide interdit."
                );

                return;
            }

            // ==========================
            // PASSWORD
            // ==========================
            System.out.print(
                    "Entrer mot de passe : "
            );

            String password =
                    sc.nextLine();

            if (password.length() < 8) {

                System.out.println(
                        "Mot de passe trop court."
                );

                return;
            }

            // ==========================
            // CHIFFREMENT
            // ==========================
            String crypte =
                    chiffrer(
                            message,
                            password
                    );

            // ==========================
            // DECHIFFREMENT
            // ==========================
            String dechiffre =
                    dechiffrer(
                            crypte,
                            password
                    );

            // ==========================
            // RESULTAT
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