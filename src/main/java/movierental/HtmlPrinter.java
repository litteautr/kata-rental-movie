package movierental;

public class HtmlPrinter implements IPrinter {

  @Override
  public String printHeader(String name) {
    return "<h1>Rental Record for <em>" + name + "</em></h1>\n<table>\n";
  }

  @Override
  public String printRental(Rental rental, double thisAmount) {
    return "\t<tr><td>" + rental.movie().title() + "</td><td>" + thisAmount + "</td></tr>\n";
  }

  @Override
  public String printFooter(double totalAmount, int frequentRenterPoints) {
    return "</table>\n<p>Amount owed is <em>"
        + totalAmount
        + "</em></p>\n<p>You earned <em>"
        + frequentRenterPoints
        + "</em> frequent renter points</p>";
  }
}
