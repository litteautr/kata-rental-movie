package movierental;

public class TextePrinter implements IPrinter {

  @Override
  public String printHeader(String name) {
    return "Rental Record for " + name + "\n";
  }

  @Override
  public String printRental(Rental each, double thisAmount) {
    return "\t" + each.movie().title() + "\t" + thisAmount + "\n";
  }

  @Override
  public String printFooter(double totalAmount, int frequentRenterPoints) {
    return "Amount owed is " + totalAmount + "\n" + "You earned " + frequentRenterPoints + " frequent renter points";
  }
}
