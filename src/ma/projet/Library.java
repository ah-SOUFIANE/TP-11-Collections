package ma.projet;

import java.util.*;

public class Library {
    private List<Book> books = new ArrayList<>();
    private Map<Book, Integer> stock = new HashMap<>();
    private Map<User, List<Book>> loans = new HashMap<>();

    public void addBook(Book b, int quantity) {
        if (!books.contains(b)) {
            books.add(b);
            stock.put(b, quantity);
        } else {
            stock.put(b, stock.get(b) + quantity);
        }
    }

    public boolean lendBook(User u, Book b) {
        Integer qty = stock.getOrDefault(b, 0);
        if (qty <= 0) return false;
        
        loans.computeIfAbsent(u, k -> new ArrayList<>());
        List<Book> userLoans = loans.get(u);
        if (!userLoans.contains(b)) {
            userLoans.add(b);
            stock.put(b, qty - 1);
            return true;
        }
        return false;
    }

    public boolean returnBook(User u, Book b) {
        List<Book> userLoans = loans.get(u);
        if (userLoans != null && userLoans.remove(b)) {
            stock.put(b, stock.getOrDefault(b, 0) + 1);
            return true;
        }
        return false;
    }

    public List<Book> listAvailable() {
        List<Book> available = new ArrayList<>();
        for (Book b : books) {
            if (stock.getOrDefault(b, 0) > 0) {
                available.add(b);
            }
        }
        return available;
    }

    public List<Book> listLoans(User u) {
        return loans.getOrDefault(u, Collections.emptyList());
    }
}