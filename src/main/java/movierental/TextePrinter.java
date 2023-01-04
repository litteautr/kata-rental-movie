package movierental;

public class TextePrinter implements IPrinter {

  @Override
  public String printHeader(String name) {
    return "Rental Record for " + name + "\n";
  }

  @Override
  public String printRental(Rental rental, double thisAmount) {
    return "\t" + rental.movie().title() + "\t" + thisAmount + "\n";
  }

  @Override
  public String printFooter(double totalAmount, int frequentRenterPoints) {
    return "Amount owed is " + totalAmount + "\n" + "You earned " + frequentRenterPoints + " frequent renter points";
  }
}
