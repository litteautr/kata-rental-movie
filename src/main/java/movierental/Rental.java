package movierental;

import static movierental.Movie.*;

public class Rental {

  private final Movie movie;
  private final int daysRented;

  public Rental(Movie movie, int daysRented) {
    this.movie = movie;
    this.daysRented = daysRented;
  }

  double amount() {
    switch (movie.priceCode()) {
      case REGULAR -> {
        double thisAmount = 2;
        if (daysRented > 2) {
          thisAmount += (daysRented - 2) * 1.5;
        }

        return thisAmount;
      }
      case NEW_RELEASE -> {
        return (double) daysRented * 3;
      }
      case CHILDRENS -> {
        double thisAmount = 1.5;
        if (daysRented > 3) {
          thisAmount += (daysRented - 3) * 1.5;
        }

        return thisAmount;
      }
      default -> {
        return 0;
      }
    }
  }

  public int getDaysRented() {
    return daysRented;
  }

  public Movie getMovie() {
    return movie;
  }
}
