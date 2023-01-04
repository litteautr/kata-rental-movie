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

    switch (getMovie().getPriceCode()) {
      case REGULAR -> {
        double thisAmount = 2;
        if (getDaysRented() > 2) {
          thisAmount += (getDaysRented() - 2) * 1.5;
        }

        return thisAmount;
      }
      case NEW_RELEASE -> {
        return (double) getDaysRented() * 3;
      }
      case CHILDRENS -> {
        double thisAmount = 1.5;
        if (getDaysRented() > 3) {
          thisAmount += (getDaysRented() - 3) * 1.5;
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
