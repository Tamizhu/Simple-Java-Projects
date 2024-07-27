import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private List<Theatre> theatres;

    public Library() {
        theatres = new ArrayList<>();
    }

    public void addTheatre(Theatre theatre) {
        theatres.add(theatre);
    }

    public List<Theatre> getTheatres() {
        return theatres;
    }

    public List<Movie> listAllMovies() {
        return theatres.stream()
                .flatMap(theatre -> theatre.getShows().stream())
                .map(Show::getMovie)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Show> listShowsByTheatre(String theatreName) {
        return theatres.stream()
                .filter(theatre -> theatre.getName().equalsIgnoreCase(theatreName))
                .flatMap(theatre -> theatre.getShows().stream())
                .collect(Collectors.toList());
    }

    public List<Theatre> listTheatresByMovie(String movieTitle) {
        return theatres.stream()
                .filter(theatre -> theatre.getShows().stream().anyMatch(show -> show.getMovie().getTitle().equalsIgnoreCase(movieTitle)))
                .collect(Collectors.toList());
    }

    public void generateRevenueReport(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Theatre, Show Timing, Total Amount Sold\n");
            for (Theatre theatre : theatres) {
                for (Show show : theatre.getShows()) {
                    writer.write(String.format("%s, %s, %.2f\n", theatre.getName(), show.getTime(), show.calculateRevenue()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
