package movierental;

public interface IPrinter {

  String printHeader(String name);

  String printRental(Rental each, double thisAmount);

  String printFooter(double totalAmount, int frequentRenterPoints);
}
