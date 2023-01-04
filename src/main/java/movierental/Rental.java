package movierental;

public record Rental(Movie movie, int daysRented) {

  double amount() {
    switch (movie.category()) {
      case REGULAR -> {
        return calcul(2, 2, 1.5);
      }
      case NEW_RELEASE -> {
        return calcul(0, 0, 3);
      }
      case CHILDREN -> {
        return calcul(1.5, 3, 1.5);
      }
      default -> {
        return 0;
      }
    }
  }

  private double calcul(double initialAmount, double daysBeforePenalty, double penaltyCoefficient) {
    if (daysRented > daysBeforePenalty) {
      return initialAmount + (daysRented - daysBeforePenalty) * penaltyCoefficient;
    }

    return initialAmount;
  }
}
