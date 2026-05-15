import java.util.Scanner;

public class XORCipher {

    // Chiffrement/Déchiffrement
    public static String xor(String texte, int cle) {

        String resultat = "";

        for (int i = 0; i < texte.length(); i++) {

            char c = texte.charAt(i);

            // XOR
            char x = (char)(c ^ cle);

            resultat += x;
        }

        return resultat;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Message : ");
        String message = sc.nextLine();

        System.out.print("Clé : ");
        int cle = sc.nextInt();

        // Chiffrement
        String crypte = xor(message, cle);

        // Déchiffrement
        String dechiffre = xor(crypte, cle);

        System.out.println("\nMessage crypté : " + crypte);

        System.out.println("Message décrypté : " + dechiffre);

        sc.close();
    }
}