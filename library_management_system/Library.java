import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<Book> listAvailableBooks() {
        return books.stream().filter(Book::isAvailable).collect(Collectors.toList());
    }

    public List<Book> searchBooksByTitle(String title) {
        return books.stream().filter(book -> book.getTitle().equalsIgnoreCase(title)).collect(Collectors.toList());
    }

    public List<Book> searchBooksByAuthor(String author) {
        return books.stream().filter(book -> book.getAuthor().equalsIgnoreCase(author)).collect(Collectors.toList());
    }

    public boolean borrowBook(String isbn, int userId) {
        Book book = books.stream().filter(b -> b.getIsbn().equals(isbn) && b.isAvailable()).findFirst().orElse(null);
        User user = users.stream().filter(u -> u.getId() == userId).findFirst().orElse(null);

        if (book != null && user != null) {
            book.borrow();
            user.borrowBook(book);
            return true;
        }
        return false;
    }

    public boolean returnBook(String isbn, int userId) {
        Book book = books.stream().filter(b -> b.getIsbn().equals(isbn) && !b.isAvailable()).findFirst().orElse(null);
        User user = users.stream().filter(u -> u.getId() == userId).findFirst().orElse(null);

        if (book != null && user != null) {
            book.returnBook();
            user.returnBook(book);
            return true;
        }
        return false;
    }

    public List<Book> listBorrowedBooks(int userId) {
        User user = users.stream().filter(u -> u.getId() == userId).findFirst().orElse(null);
        if (user != null) {
            return user.getBorrowedBooks();
        }
        return new ArrayList<>();
    }
}
