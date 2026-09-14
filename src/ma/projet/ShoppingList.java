package ma.projet;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList {
    private final List<String> items = new ArrayList<>();

    public void add(String item) {
        items.add(item);
        System.out.println("'" + item + "' ajouté.");
    }

    public boolean remove(String item) {
        boolean ok = items.remove(item);
        if (ok) {
            System.out.println("'" + item + "' supprimé.");
        } else {
            System.out.println("'" + item + "' introuvable.");
        }
        return ok;
    }

    public boolean contains(String item) {
        boolean present = items.contains(item);
        if (present) {
            System.out.println("'" + item + "' est dans la liste.");
        } else {
            System.out.println("'" + item + "' n'est pas dans la liste.");
        }
        return present;
    }

    public void display() {
        System.out.println("\nVotre liste de courses :");
        if (items.isEmpty()) {
            System.out.println("(vide)");
        } else {
            for (int i = 0; i < items.size(); i++) {
                System.out.printf("%2d. %s%n", i + 1, items.get(i));
            }
        }
    }
}