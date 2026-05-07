import java.util.Scanner;

public class CesarBasique {

    public static String chiffrer(String message) {
        int k = 3; // clé fixe

          // StringBuilder est plus efficace qu'une String pour les concaténations répétées
        // Il permet d'accumuler les caractères sans créer de nouveaux objets à chaque fois
        StringBuilder resultat = new StringBuilder();

        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                //operation ternaire pour déterminer la base (A ou a) selon la casse
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                // (c - base)     : transforme la lettre en nombre (A=0, B=1, ..., Z=25)
                // + k            : ajoute le décalage de 3
                // % 26           : si on dépasse 25, on revient au début (Z+3→C)
                // + base         : retransforme le nombre en lettre
                // (char)         : convertit le nombre en caractère
                char chiffre = (char) ((c - base + k) % 26 + base);
                resultat.append(chiffre);
            } else {
                resultat.append(c); // espaces conservés
            }
        }
        return resultat.toString(); // On transforme le seau en texte et on le renvoie
    }
    

    // Message fixé pour les tests
        public static void main(String[] args) {

        // Message fixe
        String message = "Bonjour Olivier";

        // Chiffrement
        String messageChiffre = chiffrer(message);

        // Affichage
        System.out.println("Message original : " + message);
        System.out.println("Message chiffre  : " + messageChiffre);
        System.out.println("Cle utilisee     : k = 3 (fixe)");
    }

}