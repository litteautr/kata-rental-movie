package movierental;

public record Rental(Movie movie, int daysRented) {

  double amount() {
    Category category = movie.category();
    if (daysRented > category.daysBeforePenalty) {
      return category.initialAmount + (daysRented - category.daysBeforePenalty) * category.penaltyCoefficient;
    }

    return category.initialAmount;
  }
}
