import java.util.ArrayList;
import java.util.List;

public class Show {
    private String time;
    private Movie movie;
    private List<Ticket> tickets;

    public Show(String time, Movie movie) {
        this.time = time;
        this.movie = movie;
        this.tickets = new ArrayList<>();
    }

    public String getTime() {
        return time;
    }

    public Movie getMovie() {
        return movie;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public double calculateRevenue() {
        return tickets.stream().mapToDouble(Ticket::getPrice).sum();
    }

    @Override
    public String toString() {
        return "Show{" +
                "time='" + time + '\'' +
                ", movie=" + movie +
                ", tickets=" + tickets +
                '}';
    }
}
