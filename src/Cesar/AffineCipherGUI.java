package Cesar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AffineCipherGUI extends JFrame {

    // ==========================================
    // COMPOSANTS
    // ==========================================
    JTextArea messageArea;
    JTextArea resultatArea;

    JTextField aField;
    JTextField bField;

    JButton crypterButton;
    JButton decrypterButton;
    JButton viderButton;

    // ==========================================
    // CONSTRUCTEUR
    // ==========================================
    public AffineCipherGUI() {

        // ==============================
        // FENETRE
        // ==============================
        setTitle("Affine Cipher");
        setSize(600, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        // ==============================
        // PANEL PRINCIPAL
        // ==============================
        JPanel panel = new JPanel();

        panel.setLayout(new BorderLayout());

        // ==============================
        // PANEL HAUT
        // ==============================
        JPanel topPanel = new JPanel();

        topPanel.setLayout(new GridLayout(4, 2, 10, 10));

        // Message
        topPanel.add(new JLabel("Message :"));

        messageArea = new JTextArea(3, 20);

        JScrollPane scroll1 =
                new JScrollPane(messageArea);

        topPanel.add(scroll1);

        // Clé a
        topPanel.add(new JLabel("Clé a :"));

        aField = new JTextField();

        topPanel.add(aField);

        // Clé b
        topPanel.add(new JLabel("Clé b :"));

        bField = new JTextField();

        topPanel.add(bField);

        panel.add(topPanel, BorderLayout.NORTH);

        // ==============================
        // RESULTAT
        // ==============================
        resultatArea = new JTextArea();

        resultatArea.setEditable(false);

        JScrollPane scroll2 =
                new JScrollPane(resultatArea);

        panel.add(scroll2, BorderLayout.CENTER);

        // ==============================
        // BOUTONS
        // ==============================
        JPanel buttonPanel = new JPanel();

        crypterButton =
                new JButton("Crypter");

        decrypterButton =
                new JButton("Décrypter");

        viderButton =
                new JButton("Vider");

        buttonPanel.add(crypterButton);

        buttonPanel.add(decrypterButton);

        buttonPanel.add(viderButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        // ==============================
        // AJOUT PANEL
        // ==============================
        add(panel);

        // ==============================
        // ACTION CHIFFREMENT
        // ==============================
        crypterButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ) {

                        traiter(true);
                    }
                });

        // ==============================
        // ACTION DECHIFFREMENT
        // ==============================
        decrypterButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ) {

                        traiter(false);
                    }
                });

        // ==============================
        // ACTION VIDER
        // ==============================
        viderButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ) {

                        messageArea.setText("");

                        resultatArea.setText("");

                        aField.setText("");

                        bField.setText("");
                    }
                });
    }

    // ==========================================
    // VERIFICATION CLE a
    // ==========================================
    public boolean cleValide(int a) {

        int[] valeurs = {
                1, 3, 5, 7, 9,
                11, 15, 17, 19,
                21, 23, 25
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
    public int inverseModulaire(int a) {

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
    public String chiffrer(
            String message,
            int a,
            int b
    ) {

        String resultat = "";

        for (int i = 0; i < message.length(); i++) {

            char c = message.charAt(i);

            // Minuscule
            if (Character.isLowerCase(c)) {

                int x = c - 'a';

                int y = (a * x + b) % 26;

                resultat += (char)(y + 'a');
            }

            // Majuscule
            else if (Character.isUpperCase(c)) {

                int x = c - 'A';

                int y = (a * x + b) % 26;

                resultat += (char)(y + 'A');
            }

            // Espace
            else {

                resultat += c;
            }
        }

        return resultat;
    }

    // ==========================================
    // DECHIFFREMENT
    // ==========================================
    public String dechiffrer(
            String message,
            int a,
            int b
    ) {

        String resultat = "";

        int inverseA =
                inverseModulaire(a);

        for (int i = 0; i < message.length(); i++) {

            char c = message.charAt(i);

            // Minuscule
            if (Character.isLowerCase(c)) {

                int y = c - 'a';

                int x =
                        (inverseA *
                         (y - b + 26)) % 26;

                resultat += (char)(x + 'a');
            }

            // Majuscule
            else if (Character.isUpperCase(c)) {

                int y = c - 'A';

                int x =
                        (inverseA *
                         (y - b + 26)) % 26;

                resultat += (char)(x + 'A');
            }

            // Espace
            else {

                resultat += c;
            }
        }

        return resultat;
    }

    // ==========================================
    // TRAITEMENT
    // ==========================================
    public void traiter(boolean crypter) {

        try {

            String message =
                    messageArea.getText();

            if (message.trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Message vide."
                );

                return;
            }

            int a =
                    Integer.parseInt(
                            aField.getText()
                    );

            int b =
                    Integer.parseInt(
                            bField.getText()
                    );

            // Vérifie a
            if (!cleValide(a)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Clé a invalide."
                );

                return;
            }

            // Vérifie b
            if (b < 0 || b > 25) {

                JOptionPane.showMessageDialog(
                        this,
                        "Clé b doit être entre 0 et 25."
                );

                return;
            }

            String resultat;

            // Chiffrement
            if (crypter) {

                resultat =
                        chiffrer(message, a, b);

            }

            // Déchiffrement
            else {

                resultat =
                        dechiffrer(message, a, b);
            }

            resultatArea.setText(resultat);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erreur de saisie."
            );
        }
    }

    // ==========================================
    // MAIN
    // ==========================================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                new Runnable() {

                    @Override
                    public void run() {

                        new AffineCipherGUI()
                                .setVisible(true);
                    }
                });
    }
}