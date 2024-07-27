import java.util.Scanner;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
       
        initializeData();

        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    issueTicket();
                    break;
                case 2:
                    listMoviesByTheatre();
                    break;
                case 3:
                    listAllMovies();
                    break;
                case 4:
                    listTheatresByMovie();
                    break;
                case 5:
                    generateRevenueReport();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice....try again please....");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nTheatre Management System:");
        System.out.println("1. Issue a ticket");
        System.out.println("2. List all movies in a particular theatre");
        System.out.println("3. List all movies");
        System.out.println("4. List of theatres with shows for a movie");
        System.out.println("5. Generate revenue report");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void initializeData() {
        Theatre theatreA = new Theatre("A", 50);
        Theatre theatreB = new Theatre("B", 25);

        Movie movie1 = new Movie("Movie1", "Tamil");
        Movie movie2 = new Movie("Movie2", "Hindi");
        Movie movie3 = new Movie("Movie3", "Tamil");

        theatreA.addShow(new Show("9:30 AM", movie1));
        theatreA.addShow(new Show("1:30 PM", movie2));
        theatreA.addShow(new Show("6:00 PM", movie3));

        theatreB.addShow(new Show("10:00 AM", movie1));
        theatreB.addShow(new Show("2:00 PM", movie2));
        theatreB.addShow(new Show("7:00 PM", movie3));

        library.addTheatre(theatreA);
        library.addTheatre(theatreB);
    }

    private static void issueTicket() {
        System.out.print("Enter theatre name: ");
        String theatreName = scanner.nextLine();
        System.out.print("Enter movie title: ");
        String movieTitle = scanner.nextLine();
        System.out.print("Enter show time: ");
        String showTime = scanner.nextLine();
        System.out.print("Enter ticket type (I, II, III): ");
        String ticketType = scanner.nextLine();
        System.out.print("Enter ticket price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        Theatre theatre = library.getTheatres().stream().filter(t -> t.getName().equalsIgnoreCase(theatreName)).findFirst().orElse(null);
        if (theatre != null) {
            Show show = theatre.getShows().stream().filter(s -> s.getTime().equalsIgnoreCase(showTime) && s.getMovie().getTitle().equalsIgnoreCase(movieTitle)).findFirst().orElse(null);
            if (show != null) {
                Ticket ticket = new Ticket(theatreName, movieTitle, showTime, ticketType, price);
                show.addTicket(ticket);
                System.out.println("Ticket issued successfully: " + ticket);
            } else {
                System.out.println("Show not found.");
            }
        } else {
            System.out.println("Theatre not found.");
        }
    }

    private static void listMoviesByTheatre() {
        System.out.print("Enter theatre name: ");
        String theatreName = scanner.nextLine();
        List<Show> shows = library.listShowsByTheatre(theatreName);
        if (shows.isEmpty()) {
            System.out.println("No shows found for theatre: " + theatreName);
        } else {
            shows.forEach(show -> System.out.println(show.getMovie().getTitle() + " at " + show.getTime()));
        }
    }

    private static void listAllMovies() {
        List<Movie> movies = library.listAllMovies();
        if (movies.isEmpty()) {
            System.out.println("No movies found.");
        } else {
            movies.forEach(System.out::println);
        }
    }

    private static void listTheatresByMovie() {
        System.out.print("Enter movie name: ");
        String movieName = scanner.nextLine();
        List<Theatre> theatres = library.listTheatresByMovie(movieName);
        if (theatres.isEmpty()) {
            System.out.println("No theatres found for movie: " + movieName);
        } else {
            theatres.forEach(theatre -> System.out.println(theatre.getName() + " with shows at " + theatre.getShows().stream().filter(show -> show.getMovie().getTitle().equalsIgnoreCase(movieName)).map(Show::getTime).collect(Collectors.joining(", "))));
        }
    }

    private static void generateRevenueReport() {
        System.out.print("Enter file path for report: ");
        String filePath = scanner.nextLine();
        library.generateRevenueReport(filePath);
        System.out.println("Revenue report generated at: " + filePath);
    }
}
