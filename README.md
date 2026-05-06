# CryptoLab — Projet Académique Java

> Projet académique personnel visant à renforcer les compétences en **algorithmique** et **cryptographie** à travers l'implémentation progressive d'algorithmes classiques de chiffrement en Java.

---

## Objectifs du projet

- Comprendre le fonctionnement interne des algorithmes de chiffrement classiques
- Améliorer la maîtrise du langage Java (structures de contrôle, Scanner, modularité)
- Construire une base de code évolutive, propre et bien documentée
- Progresser du chiffrement symétrique simple vers des algorithmes plus avancés

---

## Environnement de développement

| Outil             | Version recommandée     |
| ----------------- | ----------------------- |
| Langage           | Java 17+                |
| IDE               | Visual Studio Code      |
| Extension VS Code | Extension Pack for Java |
| OS                | Windows / Linux / macOS |

---

## Arborescence du projet

```
CryptoLab/
├── .vscode/
│   ├── settings.json       # Configuration Java pour VS Code
│   └── launch.json         # Configuration exécution / debug
├── src/
│   ├── cesar/
│   │   ├── CesarBasique.java     # Chiffrement César k=3 fixe
│   │   └── CesarAvance.java      # Chiffrement César k variable (à venir)
│   └── [futur_algorithme]/       # Dossier par algorithme ajouté
├── bin/                    # Fichiers .class compilés (auto-généré)
└── README.md               # Ce fichier
```

> Chaque algorithme est isolé dans son propre sous-dossier `src/` pour garantir la clarté et la maintenabilité du code.

---

## Algorithmes implémentés

### Algorithme 1 — Chiffrement de César

| Propriété | Détail                                        |
| --------- | --------------------------------------------- |
| Type      | Chiffrement par substitution monoalphabétique |
| Clé       | Décalage `k` dans l'alphabet (26 lettres)     |
| Statut    | Implémenté                                    |
| Fichier   | `src/cesar/CesarBasique.java`                 |

#### Principe

Chaque lettre du message est décalée de `k` positions dans l'alphabet. Le décalage est circulaire : après `Z` on revient à `A`.

```
Formule : C = (P + k) mod 26
Exemple  : k=3 → A→D, B→E, Z→C
```

#### Mode d'emploi — Version basique (k = 3 fixe)

**1. Compiler le programme**

```bash
javac -d bin src/cesar/CesarBasique.java
```

**2. Exécuter le programme**

```bash
java -cp bin CesarBasique
```

**3. Saisir le message**

```
Entrez votre message : Bonjour
Message original : Bonjour
Message chiffre  : Erqmrxu
Cle utilisee     : k = 3 (fixe)
```

#### Règles de saisie

- Lettres uniquement (A–Z, a–z)
- Les espaces sont autorisés et conservés
- Les caractères spéciaux (`!`, `@`, `1`, `#`…) sont **rejetés**

---

## Algorithmes prévus (feuille de route)

> Cette section sera mise à jour au fur et à mesure de l'avancement du projet.

| #   | Algorithme                | Type                          | Statut      |
| --- | ------------------------- | ----------------------------- | ----------- |
| 1   | César basique (k=3)       | Substitution                  | ✅ Fait     |
| 2   | César avancé (k variable) | Substitution                  | 🔄 En cours |
| 3   | Vigenère                  | Substitution polyalphabétique | 📋 Prévu    |
| 4   | Transposition             | Permutation                   | 📋 Prévu    |
| 5   | ROT13                     | Substitution                  | 📋 Prévu    |
| 6   | Chiffre de Vernam (XOR)   | Clé à usage unique            | 📋 Prévu    |

---

## Comment ajouter un nouvel algorithme

1. Créer un sous-dossier dans `src/` portant le nom de l'algorithme
   ```bash
   mkdir src/vigenere
   ```
2. Créer le fichier `.java` correspondant dans ce dossier
3. Documenter l'algorithme dans ce README (copier le bloc "Algorithme N")
4. Compiler et tester avant de valider

---

## Auteur

| Champ    | Information                                  |
| -------- | -------------------------------------------- |
| Projet   | CryptoLab — Projet académique personnel      |
| Contexte | Apprentissage Java & cryptographie classique |
| Statut   | En développement actif                       |

---

_Dernière mise à jour : Mai 2026_
