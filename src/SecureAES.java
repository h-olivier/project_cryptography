import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.MessageDigest;
import java.security.SecureRandom;

import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

public class SecureAES {

    // ==========================================
    // GENERATION CLE AES AVEC SHA-256
    // ==========================================
    public static SecretKeySpec genererCle(
            String motDePasse
    ) throws Exception {

        // SHA-256
        MessageDigest sha =
                MessageDigest.getInstance(
                        "SHA-256"
                );

        byte[] cle =
                sha.digest(
                        motDePasse.getBytes("UTF-8")
                );

        // AES 128 bits
        cle = Arrays.copyOf(cle, 16);

        return new SecretKeySpec(cle, "AES");
    }

    // ==========================================
    // CHIFFREMENT
    // ==========================================
    public static String chiffrer(
            String message,
            String motDePasse
    ) throws Exception {

        // ==============================
        // CLE AES
        // ==============================
        SecretKeySpec cle =
                genererCle(motDePasse);

        // ==============================
        // IV ALEATOIRE
        // ==============================
        byte[] iv = new byte[16];

        SecureRandom random =
                new SecureRandom();

        random.nextBytes(iv);

        IvParameterSpec ivSpec =
                new IvParameterSpec(iv);

        // ==============================
        // AES CBC
        // ==============================
        Cipher cipher =
                Cipher.getInstance(
                        "AES/CBC/PKCS5Padding"
                );

        cipher.init(
                Cipher.ENCRYPT_MODE,
                cle,
                ivSpec
        );

        // ==============================
        // CHIFFREMENT
        // ==============================
        byte[] crypte =
                cipher.doFinal(
                        message.getBytes("UTF-8")
                );

        // ==============================
        // IV + MESSAGE
        // ==============================
        byte[] resultat =
                new byte[
                        iv.length +
                        crypte.length
                ];

        System.arraycopy(
                iv,
                0,
                resultat,
                0,
                iv.length
        );

        System.arraycopy(
                crypte,
                0,
                resultat,
                iv.length,
                crypte.length
        );

        // Base64
        return Base64.getEncoder()
                .encodeToString(resultat);
    }

    // ==========================================
    // DECHIFFREMENT
    // ==========================================
    public static String dechiffrer(
            String messageCrypte,
            String motDePasse
    ) throws Exception {

        // ==============================
        // CLE AES
        // ==============================
        SecretKeySpec cle =
                genererCle(motDePasse);

        // ==============================
        // BASE64 -> BYTES
        // ==============================
        byte[] donnees =
                Base64.getDecoder()
                        .decode(messageCrypte);

        // ==============================
        // EXTRACTION IV
        // ==============================
        byte[] iv =
                Arrays.copyOfRange(
                        donnees,
                        0,
                        16
                );

        // ==============================
        // EXTRACTION MESSAGE
        // ==============================
        byte[] crypte =
                Arrays.copyOfRange(
                        donnees,
                        16,
                        donnees.length
                );

        IvParameterSpec ivSpec =
                new IvParameterSpec(iv);

        // ==============================
        // AES CBC
        // ==============================
        Cipher cipher =
                Cipher.getInstance(
                        "AES/CBC/PKCS5Padding"
                );

        cipher.init(
                Cipher.DECRYPT_MODE,
                cle,
                ivSpec
        );

        // ==============================
        // DECHIFFREMENT
        // ==============================
        byte[] resultat =
                cipher.doFinal(crypte);

        return new String(resultat, "UTF-8");
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
                    "Entrer le message : "
            );

            String message =
                    sc.nextLine();

            // Vérifie vide
            if (message.trim().isEmpty()) {

                System.out.println(
                        "Message vide interdit."
                );

                return;
            }

            // ==========================
            // MOT DE PASSE
            // ==========================
            System.out.print(
                    "Entrer mot de passe : "
            );

            String password =
                    sc.nextLine();

            // Vérifie taille
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