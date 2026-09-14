# TP 11 - Collections Java (List, Set, Map)

Cours : Fondamentaux et Concepts Avancés de la Programmation Java

Ce TP contient 5 exercices sur les collections Java : List, Set et Map, leurs principales implémentations (ArrayList, HashSet, LinkedHashSet, TreeSet, HashMap, LinkedHashMap, TreeMap), et leur combinaison pour modéliser un système réel.

## Objectifs

- Comprendre le rôle des interfaces List, Set et Map et de leurs implémentations courantes
- Maîtriser les opérations de base : add, remove, contains, get, size
- Utiliser les Streams pour filtrer et trier des collections
- Comparer les comportements de HashSet, LinkedHashSet et TreeSet (ordre, performance)
- Comparer les comportements de HashMap, LinkedHashMap et TreeMap
- Combiner plusieurs collections pour garantir la cohérence des données dans un système complet

## Prérequis

- JDK installé (version 8 ou supérieure, pour les Streams)
- Un IDE (Eclipse, IntelliJ, VS Code) ou un terminal avec `javac` et `java`
- Notions de base : classes, méthodes, boucles, conditions

## Compilation et exécution

Chaque exercice est regroupé dans le package `com.example.tp`, avec sa propre classe `Main` servant de programme de test.

```bash
cd src
javac com/example/tp/*.java
java com.example.tp.Main
```

## Exercice 1 : Gestionnaire de liste de courses avec List

Construire une application console `ShoppingList` qui gère dynamiquement une liste d'articles (`List<String>`), avec ajout, suppression, recherche et affichage numéroté.

```java
public class ShoppingList {
    private final List<String> items = new ArrayList<>();

    public void add(String item) {
        items.add(item);
        System.out.println("'" + item + "' ajouté.");
    }

    public boolean remove(String item) {
        return items.remove(item);
    }

    public boolean contains(String item) {
        return items.contains(item);
    }
}
```

Résultat attendu (extrait) :

```
Entrez une commande : add
Article à ajouter : Lait
'Lait' ajouté.

Entrez une commande : show

Votre liste de courses :
 1. Lait
 2. Pain
```

Classes : `ShoppingList.java`, `Main.java`

## Exercice 2 : Gestionnaire de tâches avec List

Construire un `TaskManager` qui stocke des objets `Task` (id, description, priorité, statut) dans un `List<Task>`, avec ajout, suppression, mise à jour de statut, filtrage et tri via les Streams.

```java
public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(String description, int priority) {
        tasks.add(new Task(description, priority));
    }

    public List<Task> filterByStatus(Status status) {
        return tasks.stream()
                     .filter(t -> t.getStatus() == status)
                     .collect(Collectors.toList());
    }

    public void sortByPriority() {
        tasks.sort(Comparator.comparingInt(Task::getPriority));
    }
}
```

Résultat attendu (extrait) :

```
=== Trier par priorité ===
[2] (prio=1) PENDING — Corriger les bugs
[1] (prio=2) PENDING — Écrire la doc
[3] (prio=3) PENDING — Préparer démo
[4] (prio=4) PENDING — Envoyer emails
```

Classes : `Status.java`, `Task.java`, `TaskManager.java`, `Main.java`

## Exercice 3 : Maîtrise des Set - Gestionnaire de mots uniques

Construire un `WordManager` qui extrait les mots d'un texte et les stocke simultanément dans un `HashSet`, un `LinkedHashSet` et un `TreeSet`, afin de comparer leur ordre d'affichage, avec recherche et suppression synchronisées sur les trois ensembles.

```java
public class WordManager {
    private final Set<String> hashSet   = new HashSet<>();
    private final Set<String> linkedSet = new LinkedHashSet<>();
    private final Set<String> treeSet   = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

    public void parseText() {
        String[] tokens = rawText.toLowerCase(Locale.ROOT).split("[^a-zA-Z]+");
        for (String token : tokens) {
            if (token.isEmpty()) continue;
            hashSet.add(token);
            linkedSet.add(token);
            treeSet.add(token);
        }
    }
}
```

Points clés :
- `HashSet` : ordre indéfini, performances O(1)
- `LinkedHashSet` : conserve l'ordre d'insertion
- `TreeSet` : garde les mots triés alphabétiquement (`String.CASE_INSENSITIVE_ORDER`)

Classes : `WordManager.java`, `Main.java`

## Exercice 4 : Gestion d'un dictionnaire bilingue avec Map

Construire un `DictionaryManager` anglais → français en s'appuyant sur un `HashMap`, un `LinkedHashMap` et un `TreeMap`, avec ajout, recherche, suppression, affichage, et recherche par préfixe (autocomplétion) grâce au `TreeMap`.

```java
public class DictionaryManager {
    private final Map<String,String> hashMap   = new HashMap<>();
    private final Map<String,String> linkedMap = new LinkedHashMap<>();
    private final NavigableMap<String,String> treeMap = new TreeMap<>();
}
```

Résultat attendu (extrait) :

```
Traduction de 'apple' : pomme

Préfixe à chercher : ap
Résultats pour "ap" :
apple      → pomme
apricot    → abricot
```

Classes : `DictionaryManager.java`, `Main.java`

## Exercice 5 : Maîtrise des collections - Gestion d'une bibliothèque

Combiner `List`, `Map` et `Set` pour modéliser une bibliothèque : `List<Book>` pour le catalogue, `Map<Book,Integer>` pour le stock, `Map<User,List<Book>>` pour les emprunts.

```java
public class Library {
    private List<Book> books = new ArrayList<>();
    private Map<Book, Integer> stock = new HashMap<>();
    private Map<User, List<Book>> loans = new HashMap<>();

    public boolean lendBook(User u, Book b) {
        Integer qty = stock.getOrDefault(b, 0);
        if (qty <= 0) return false;
        stock.put(b, qty - 1);

        loans.computeIfAbsent(u, k -> new ArrayList<>());
        List<Book> userLoans = loans.get(u);
        if (!userLoans.contains(b)) {
            userLoans.add(b);
            return true;
        }
        return false;
    }
}
```

Résultat attendu (extrait) :

```
Disponibles : [Effective Java (ISBN:978-0134685991) by Joshua Bloch, ...]
Alice emprunte Effective Java : true
Bob emprunte Effective Java : true
Bob emprunte Effective Java à nouveau : false
```

Classes : `Book.java`, `User.java`, `Library.java`, `Main.java`

## Structure du projet

```
TP11_Collections/
├── src/
│   └── com/example/tp/
│       ├── ShoppingList.java
│       ├── Status.java
│       ├── Task.java
│       ├── TaskManager.java
│       ├── WordManager.java
│       ├── DictionaryManager.java
│       ├── Book.java
│       ├── User.java
│       ├── Library.java
│       └── Main.java
├── README.md
└── videos/
    └── demo.mp4
```

## Concepts mobilisés

- List / ArrayList : collection ordonnée et redimensionnable dynamiquement
- Set (HashSet, LinkedHashSet, TreeSet) : unicité des éléments, différences d'ordre et de performance
- Map (HashMap, LinkedHashMap, TreeMap) : association clé-valeur, recherche rapide, tri des clés
- Streams Java 8 pour le filtrage et le tri déclaratifs
- Cohérence des données entre plusieurs collections liées entre elles (stock, emprunts)

## Démo vidéo

Une seule vidéo montre l'exécution des 5 exercices, dans l'ordre.

https://github.com/user-attachments/assets/de97062f-5ac6-4301-b31e-ecf5f5a0c83b

## Auteur

Soufiane Ait Hmad — TP11 Java, ENS Marrakech
