package movierental;

import static movierental.Category.NEW_RELEASE;

import java.util.ArrayList;
import java.util.List;

public class Customer {

  private final String name;
  private final List<Rental> rentals = new ArrayList<>();

  public Customer(String name) {
    this.name = name;
  }

  public void addRental(Rental arg) {
    rentals.add(arg);
  }

  public String getName() {
    return name;
  }

  public String statement(IPrinter printer) {
    double totalAmount = 0;
    int frequentRenterPoints = 0;
    StringBuilder result = new StringBuilder(printer.printHeader(name));

    for (Rental rental : rentals) {
      double thisAmount = rental.amount();

      frequentRenterPoints = getFrequentRenterPoints(frequentRenterPoints, rental);

      result.append(printer.printRental(rental, thisAmount));

      totalAmount += thisAmount;
    }

    return result
        .append(printer.printFooter(totalAmount, frequentRenterPoints))
        .toString();
  }

  private static int getFrequentRenterPoints(int frequentRenterPoints, Rental rental) {
    frequentRenterPoints++;

    // add bonus for a two day new release rental
    if ((rental.movie().category() == NEW_RELEASE) && rental.daysRented() > 1) {
      frequentRenterPoints++;
    }

    return frequentRenterPoints;
  }
}
