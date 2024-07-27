public class Movie {
    private String title;
    private String language;

    public Movie(String title, String language) {
        this.title = title;
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return language;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", Language='" + language + '\'' +
                '}';
    }
}
