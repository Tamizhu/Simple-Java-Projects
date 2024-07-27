public class Ticket {
    private static int counter = 0;
    private int ticketNumber;
    private String theatreName;
    private String movieTitle;
    private String showTime;
    private String ticketType;
    private double price;

    public Ticket(String theatreName, String movieTitle, String showTime, String ticketType, double price) {
        this.ticketNumber = ++counter;
        this.theatreName = theatreName;
        this.movieTitle = movieTitle;
        this.showTime = showTime;
        this.ticketType = ticketType;
        this.price = price;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getShowTime() {
        return showTime;
    }

    public String getTicketType() {
        return ticketType;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketNumber=" + ticketNumber +
                ", theatreName='" + theatreName + '\'' +
                ", movieTitle='" + movieTitle + '\'' +
                ", showTime='" + showTime + '\'' +
                ", ticketType='" + ticketType + '\'' +
                ", price=" + price +
                '}';
    }
}

