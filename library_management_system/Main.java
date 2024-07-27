import java.util.List;
import java.util.Scanner;

public class Main {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Sample data
        library.addBook(new Book("Book1", "Author1", "ISBN1"));
        library.addBook(new Book("Book2", "Author2", "ISBN2"));
        library.addBook(new Book("Book3", "Author3", "ISBN3"));
        library.addBook(new Book("Book4", "Author4", "ISBN4"));
        library.addUser(new User("User1", 1));
        library.addUser(new User("User2", 2));
        library.addUser(new User("User3", 3));
        library.addUser(new User("User4", 4));

        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();   

            switch (choice) {
                case 1:
                    listAvailableBooks();
                    break;
                case 2:
                    searchBookByTitle();
                    break;
                case 3:
                    searchBookByAuthor();
                    break;
                case 4:
                    borrowBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    listBorrowedBooks();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nLibrary Management System:");
        System.out.println("1. View a list of available books");
        System.out.println("2. Search for a book by title");
        System.out.println("3. Search for a book by author");
        System.out.println("4. Borrow a book");
        System.out.println("5. Return a borrowed book");
        System.out.println("6. View a list of books borrowed by a user");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void listAvailableBooks() {
        List<Book> books = library.listAvailableBooks();
        if (books.isEmpty()) {
            System.out.println("No available books.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private static void searchBookByTitle() {
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();
        List<Book> books = library.searchBooksByTitle(title);
        if (books.isEmpty()) {
            System.out.println("No books found with the title: " + title);
        } else {
            books.forEach(System.out::println);
        }
    }

    private static void searchBookByAuthor() {
        System.out.print("Enter the author of the book: ");
        String author = scanner.nextLine();
        List<Book> books = library.searchBooksByAuthor(author);
        if (books.isEmpty()) {
            System.out.println("No books found by the author: " + author);
        } else {
            books.forEach(System.out::println);
        }
    }

    private static void borrowBook() {
        System.out.print("Enter the ISBN of the book: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter your user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        if (library.borrowBook(isbn, userId)) {
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Unable to borrow book. It may be unavailable or user not found.");
        }
    }

    private static void returnBook() {
        System.out.print("Enter the ISBN of the book: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter your user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        if (library.returnBook(isbn, userId)) {
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Unable to return book. It may be already available or user not found.");
        }
    }

    private static void listBorrowedBooks() {
        System.out.print("Enter the user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        List<Book> books = library.listBorrowedBooks(userId);
        if (books.isEmpty()) {
            System.out.println("No books borrowed by the user.");
        } else {
            books.forEach(System.out::println);
        }
    }
}
