package movierental;

public record Rental(Movie movie, int daysRented) {

  double amount() {
    switch (movie.category()) {
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
      case CHILDREN -> {
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
}
