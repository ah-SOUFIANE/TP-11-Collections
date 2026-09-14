package ma.projet;

import java.util.*;
import java.util.stream.Collectors;

public class DictionaryManager {
    private final Map<String, String> hashMap = new HashMap<>();
    private final Map<String, String> linkedMap = new LinkedHashMap<>();
    private final NavigableMap<String, String> treeMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public void addEntry(String eng, String fr) {
        hashMap.put(eng, fr);
        linkedMap.put(eng, fr);
        treeMap.put(eng, fr);
    }

    public String getTranslation(String eng) {
        return hashMap.get(eng);
    }

    public boolean removeEntry(String eng) {
        boolean existed = hashMap.remove(eng) != null;
        linkedMap.remove(eng);
        treeMap.remove(eng);
        return existed;
    }

    public void displayAll() {
        System.out.println("=== HashMap ===");
        hashMap.forEach((k, v) -> System.out.printf("%-10s → %s%n", k, v));
        System.out.println("\n=== LinkedHashMap ===");
        linkedMap.forEach((k, v) -> System.out.printf("%-10s → %s%n", k, v));
        System.out.println("\n=== TreeMap ===");
        treeMap.forEach((k, v) -> System.out.printf("%-10s → %s%n", k, v));
    }

    public Map<String, String> searchByPrefix(String prefix) {
        String low = prefix.toLowerCase(Locale.ROOT);
        return treeMap.entrySet().stream()
            .filter(e -> e.getKey().toLowerCase(Locale.ROOT).startsWith(low))
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (a, b) -> a,
                LinkedHashMap::new
            ));
    }
}