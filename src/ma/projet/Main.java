package ma.projet;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== TP 11 : Collections ===");

        // Test Exercice 2
        System.out.println("\n--- Test TaskManager ---");
        TaskManager mgr = new TaskManager();
        mgr.addTask("Écrire la doc", 2);
        mgr.addTask("Corriger les bugs", 1);
        mgr.sortByPriority();
        mgr.listTasks().forEach(System.out::println);

        // Test Exercice 3
        System.out.println("\n--- Test WordManager ---");
        WordManager wm = new WordManager("Java collections, List Set Map.");
        wm.parseText();
        wm.displayAll();

        // Test Exercice 5
        System.out.println("\n--- Test Library ---");
        Library lib = new Library();
        Book b1 = new Book("978-0134685991", "Effective Java", "Joshua Bloch");
        User user1 = new User(1, "Alice");
        lib.addBook(b1, 1);
        System.out.println("Livre prêté : " + lib.lendBook(user1, b1));
        System.out.println("Disponibles : " + lib.listAvailable());
    }
}