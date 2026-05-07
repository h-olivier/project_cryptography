public class VigenereBasique {

    // Méthode de chiffrement
    public static String chiffrer(String message, String cle) {

        StringBuilder resultat = new StringBuilder();

        // Convertir en majuscule
        message = message.toUpperCase();
        cle = cle.toUpperCase();

        int indexCle = 0;

        for (int i = 0; i < message.length(); i++) {

            char c = message.charAt(i);

            // Vérifie si c'est une lettre
            if (Character.isLetter(c)) {

                // Lettre de la clé
                char lettreCle = cle.charAt(indexCle % cle.length());

                // Décalage
                int decalage = lettreCle - 'A';

                // Chiffrement
                char chiffre = (char) ((c - 'A' + decalage) % 26 + 'A');

                resultat.append(chiffre);

                indexCle++;

            } else {

                // Conserver espaces et symboles
                resultat.append(c);
            }
        }

        return resultat.toString();
    }

    public static void main(String[] args) {

        // Valeurs fixes
        String message = "BONJOUR";
        String cle = "CLE";

        // Chiffrement
        String resultat = chiffrer(message, cle);

        // Affichage
        System.out.println("Message original : " + message);
        System.out.println("Cle              : " + cle);
        System.out.println("Message chiffre  : " + resultat);
    }
}