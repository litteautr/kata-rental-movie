package movierental;

import java.util.ArrayList;
import java.util.List;

public class Customer {

  private final String name;
  private final List<Rental> rentals = new ArrayList<>();

  public Customer(String name) {
    this.name = name;
  }

  public void addRental(Rental rental) {
    rentals.add(rental);
  }

  public String getName() {
    return name;
  }

  public String statement() {
    double totalAmount = 0;
    int frequentRenterPoints = 0;
    StringBuilder result = new StringBuilder();
    printHeader(result);

    for (Rental rental : rentals) {
      double amount = 0;

      //determine amounts for each line
      switch (rental.getMovie().getPriceCode()) {
        case Movie.REGULAR:
          amount += 2;
          if (rental.getDaysRented() > 2) {
            amount += (rental.getDaysRented() - 2) * 1.5;
          }
          break;
        case Movie.NEW_RELEASE:
          amount += rental.getDaysRented() * 3;
          break;
        case Movie.CHILDRENS:
          amount += 1.5;
          if (rental.getDaysRented() > 3) {
            amount += (rental.getDaysRented() - 3) * 1.5;
          }
          break;
      }

      // add frequent renter points
      frequentRenterPoints++;
      // add bonus for a two day new release rental
      if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && rental.getDaysRented() > 1) {
        frequentRenterPoints++;
      }

      totalAmount += amount;

      printRental(result, rental, amount);
    }

    printFooter(result, totalAmount, frequentRenterPoints);

    return result.toString();
  }

  private void printHeader(StringBuilder result) {
    result.append("Rental Record for ")
        .append(getName())
        .append("\n");
  }

  private static void printRental(StringBuilder result, Rental rental, double amount) {
    result.append("\t")
        .append(rental.getMovie().getTitle())
        .append("\t")
        .append(amount).append("\n");
  }

  private static void printFooter(StringBuilder result, double totalAmount, int frequentRenterPoints) {
    result.append("Amount owed is ")
        .append(totalAmount)
        .append("\n")
        .append("You earned ")
        .append(frequentRenterPoints)
        .append(" frequent renter points");
  }
}
