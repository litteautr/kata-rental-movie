package movierental;

public class TextePrinter implements IPrinter{

  @Override
  public String printHeader(String name) {
    return "Rental Record for " + name + "\n";
  }

  @Override
  public String printRental(Rental each, double thisAmount) {
    return "\t" + each.getMovie().getTitle() + "\t" + thisAmount + "\n";
  }

  @Override
  public String printFooter(double totalAmount, int frequentRenterPoints, String result) {
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentRenterPoints + " frequent renter points";
    return result;  }
}
