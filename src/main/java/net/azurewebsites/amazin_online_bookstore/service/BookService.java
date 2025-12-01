package net.azurewebsites.amazin_online_bookstore.service;

import net.azurewebsites.amazin_online_bookstore.entity.Book;
import net.azurewebsites.amazin_online_bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class BookService {
    private final BookRepository repo;

    public BookService(BookRepository repo) {      // Constructor injection (no @Autowired needed)
        this.repo = repo;
    }

    public Book getById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public boolean isInStock(Integer bookId, int qty) {
        return repo.findById(bookId)
                .map(b -> b.getInventory() != null && b.getInventory() >= qty)
                .orElse(false);
    }

    /**
     * Retrieves a single book with the bookId from that book's inventory.
     * @param bookId The id of the book to retrieve.
     * @return True if the retrieval could be done false otherwise.
     */
    public boolean takeBookFromInventory(Integer bookId) {
        Book book = repo.findById(bookId).orElse(null);

        if (book != null) {
            book.decrementInventory();
            repo.save(book);
            return true;
        }
        return false;
    }

    public List<Book> findRelatedByAuthor(Book b, int limit) {
        if (b == null || b.getAuthor() == null) return List.of();
        return repo.findTop5ByAuthorAndIdNot(b.getAuthor(), b.getId());
    }

    public boolean existsByIsbn(String isbn) {
        return (repo.findByIsbn(isbn).orElse(null) != null);
    }

    public void restockBookInInventory(String isbn, int qty) {
        Book book = repo.findByIsbn(isbn).orElse(null);

        if (book != null) {
            for (int i = 0; i < qty; i++){
                book.incrementInventory();
            }
            repo.save(book);
        }
    }

    public void saveNewBook(Book book) {
        repo.save(book);
	}

    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    public List<Book> searchAndFilter(String q, String author, String genre, String publisher) {
        List<Book> all = repo.findAll();

        Stream<Book> stream = all.stream();

        if (q != null && !q.isBlank()) {
            String term = q.toLowerCase();
            stream = stream.filter(b ->
                    (b.getTitle() != null && b.getTitle().toLowerCase().contains(term)) ||
                            (b.getAuthor() != null && b.getAuthor().toLowerCase().contains(term)) ||
                            (b.getGenre() != null && b.getGenre().toLowerCase().contains(term))
            );
        }

        if (author != null && !author.isBlank()) {
            stream = stream.filter(b -> author.equals(b.getAuthor()));
        }

        if (genre != null && !genre.isBlank()) {
            stream = stream.filter(b -> genre.equals(b.getGenre()));
        }

        if (publisher != null && !publisher.isBlank()) {
            stream = stream.filter(b -> publisher.equals(b.getPublisher()));
        }

        return stream.toList();
    }

    public List<String> getAllAuthors() {
        return repo.findAll().stream()
                .map(Book::getAuthor)
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getAllGenres() {
        return repo.findAll().stream()
                .map(Book::getGenre)
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getAllPublishers() {
        return repo.findAll().stream()
                .map(Book::getPublisher)
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Book> applySorting(List<Book> books, String sort) {
        if (sort == null || sort.isBlank()) {
            return books;
        }

        Stream<Book> stream = books.stream();

        switch (sort) {
            case "priceAsc":
                stream = stream.sorted(
                        Comparator.comparing(
                                Book::getPrice,
                                Comparator.nullsLast(Double::compareTo)
                        )
                );
                break;

            case "priceDesc":
                stream = stream.sorted(
                        Comparator.comparing(
                                Book::getPrice,
                                Comparator.nullsLast(Double::compareTo)
                        ).reversed()
                );
                break;

            case "yearAsc":
                stream = stream.sorted(
                        Comparator.comparing(
                                Book::getPublishedYear,
                                Comparator.nullsLast(Integer::compareTo)
                        )
                );
                break;

            case "yearDesc":
                stream = stream.sorted(
                        Comparator.comparing(
                                Book::getPublishedYear,
                                Comparator.nullsLast(Integer::compareTo)
                        ).reversed()
                );
                break;

            case "titleAsc":
                stream = stream.sorted(
                        Comparator.comparing(
                                Book::getTitle,
                                Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)
                        )
                );
                break;

            case "titleDesc":
                stream = stream.sorted(
                        Comparator.comparing(
                                Book::getTitle,
                                Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)
                        ).reversed()
                );
                break;

            default:
                return books;
        }

        return stream.toList();
    }

    public List<Book> applyLengthAndInventoryFilters(List<Book> books,
                                                     String length,
                                                     Boolean inStockOnly) {

        Stream<Book> stream = books.stream();

        if (Boolean.TRUE.equals(inStockOnly)) {
            stream = stream.filter(b ->
                    b.getInventory() != null && b.getInventory() > 0
            );
        }

        if (length != null && !length.isBlank()) {
            switch (length) {
                case "short":
                    stream = stream.filter(b ->
                            b.getNumOfPages() != null && b.getNumOfPages() < 200
                    );
                    break;

                case "medium":
                    stream = stream.filter(b ->
                            b.getNumOfPages() != null &&
                                    b.getNumOfPages() >= 200 &&
                                    b.getNumOfPages() <= 400
                    );
                    break;

                case "long":
                    stream = stream.filter(b ->
                            b.getNumOfPages() != null && b.getNumOfPages() > 400
                    );
                    break;

                default:
            }
        }

        return stream.toList();
    }


    public List<String> suggestSimilarTitles(String query) {
        if (query == null || query.isBlank()) {
            return List.of();
        }

        String q = query.toLowerCase();

        return repo.findAll().stream()
                .map(Book::getTitle)
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(t -> !t.isBlank())
                .distinct()
                .filter(t -> isSimilar(q, t.toLowerCase()))
                .limit(10)
                .toList();
    }

    private boolean isSimilar(String q, String candidate) {
        String qNorm = normalize(q);
        String cNorm = normalize(candidate);

        if (cNorm.contains(qNorm) || qNorm.contains(cNorm)) {
            return true;
        }

        int dist = levenshteinDistance(qNorm, cNorm);
        return dist <= 2;
    }

    private String normalize(String s) {
        if (s == null) return "";
        return s.toLowerCase().trim().replaceAll("\\s+", " ");
    }

    private int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= b.length(); j++) dp[0][j] = j;

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                int cost = (a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1,     // delete
                                dp[i][j - 1] + 1),    // insert
                        dp[i - 1][j - 1] + cost        // substitute
                );
            }
        }
        return dp[a.length()][b.length()];
    }
}
