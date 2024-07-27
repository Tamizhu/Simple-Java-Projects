import java.util.ArrayList;
import java.util.List;

public class Theatre {
    private String name;
    private int capacity;
    private List<Show> shows;

    public Theatre(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.shows = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void addShow(Show show) {
        shows.add(show);
    }

    @Override
    public String toString() {
        return "Theatre{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", shows=" + shows +
                '}';
    }
}
