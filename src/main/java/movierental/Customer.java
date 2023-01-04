package movierental;

import static movierental.Movie.*;

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
      double thisAmount = amount(rental);

      frequentRenterPoints = getFrequentRenterPoints(frequentRenterPoints, rental);

      result.append(printer.printRental(rental, thisAmount));

      totalAmount += thisAmount;
    }

    result.append(printer.printFooter(totalAmount, frequentRenterPoints));

    return result.toString();
  }

  private static int getFrequentRenterPoints(int frequentRenterPoints, Rental each) {
    // add frequent renter points
    frequentRenterPoints++;

    // add bonus for a two day new release rental
    if ((each.getMovie().getPriceCode() == NEW_RELEASE) && each.getDaysRented() > 1) {
      frequentRenterPoints++;
    }

    return frequentRenterPoints;
  }

  private static double amount(Rental rental) {

    switch (rental.getMovie().getPriceCode()) {
      case REGULAR -> {
        double thisAmount = 2;
        if (rental.getDaysRented() > 2) {
          thisAmount += (rental.getDaysRented() - 2) * 1.5;
        }

        return thisAmount;
      }
      case NEW_RELEASE -> {
        return rental.getDaysRented() * 3;
      }
      case CHILDRENS -> {
        double thisAmount = 1.5;
        if (rental.getDaysRented() > 3) {
          thisAmount += (rental.getDaysRented() - 3) * 1.5;
        }

        return thisAmount;
      }
      default -> {
        return 0;
      }
    }
  }
}
