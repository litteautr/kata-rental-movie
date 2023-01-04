package movierental;

import static movierental.Movie.NEW_RELEASE;

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
    StringBuilder result = new StringBuilder(printer.printHeader(getName()));

    for (Rental rental : rentals) {
      double thisAmount = rental.amount();

      frequentRenterPoints = getFrequentRenterPoints(frequentRenterPoints, rental);

      result.append(printer.printRental(rental, thisAmount));

      totalAmount += thisAmount;
    }

    result.append(printer.printFooter(totalAmount, frequentRenterPoints));

    return result.toString();
  }

  private static int getFrequentRenterPoints(int frequentRenterPoints, Rental rental) {
    // add frequent renter points
    frequentRenterPoints++;

    // add bonus for a two day new release rental
    if ((rental.getMovie().getPriceCode() == NEW_RELEASE) && rental.getDaysRented() > 1) {
      frequentRenterPoints++;
    }

    return frequentRenterPoints;
  }
}
