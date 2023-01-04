package movierental;

import static movierental.Movie.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class CustomerTest {

  public static final Movie REGULAR_MOVIE = new Movie("Gone with the Wind", REGULAR);
  public static final Movie NEW_RELEASE_MOVIE = new Movie("Star Wars", NEW_RELEASE);
  public static final Movie CHILDRENS_MOVIE = new Movie("Madagascar", CHILDRENS);

  @Test
  void testCustomer() {
    Customer c = new CustomerBuilder().build();
    assertNotNull(c);
  }

  @Test
  void addRental() {
    Customer customer2 = new CustomerBuilder().withName("Julia").build();
    Rental rental1 = new Rental(REGULAR_MOVIE, 3);
    customer2.addRental(rental1);
  }

  @Test
  void getName() {
    Customer c = new Customer("David");
    assertEquals("David", c.getName());
  }

  @Test
  void statement_regularMovie() {
    Rental rental1 = new Rental(REGULAR_MOVIE, 3);
    Customer customer2 =
        new CustomerBuilder()
            .withName("Sallie")
            .withRentals(rental1)
            .build();
    String expected = """
        Rental Record for Sallie
        \tGone with the Wind\t3.5
        Amount owed is 3.5
        You earned 1 frequent renter points""";
    String statement = customer2.statement(new TextePrinter());
    assertEquals(expected, statement);
  }

  @Test
  void statement_newReleaseMovie() {
    Rental rental1 = new Rental(NEW_RELEASE_MOVIE, 3);
    Customer customer2 =
        new CustomerBuilder()
            .withName("Sallie")
            .withRentals(rental1)
            .build();
    String expected = """
        Rental Record for Sallie
        \tStar Wars\t9.0
        Amount owed is 9.0
        You earned 2 frequent renter points""";
    String statement = customer2.statement(new TextePrinter());
    assertEquals(expected, statement);
  }

  @Test
  void statement_childrensMovie() {
    Rental rental1 = new Rental(CHILDRENS_MOVIE, 3);
    Customer customer2
        = new CustomerBuilder()
        .withName("Sallie")
        .withRentals(rental1)
        .build();
    String expected = """
        Rental Record for Sallie
        \tMadagascar\t1.5
        Amount owed is 1.5
        You earned 1 frequent renter points""";
    String statement = customer2.statement(new TextePrinter());
    assertEquals(expected, statement);
  }

  @Test
  void statement_manyMovies() {
    Rental rental1 = new Rental(CHILDRENS_MOVIE, 6);
    Rental rental2 = new Rental(NEW_RELEASE_MOVIE, 2);
    Rental rental3 = new Rental(REGULAR_MOVIE, 8);
    Customer customer1
        = new CustomerBuilder()
        .withName("David")
        .withRentals(rental1, rental2, rental3)
        .build();
    String expected = """
        Rental Record for David
        \tMadagascar\t6.0
        \tStar Wars\t6.0
        \tGone with the Wind\t11.0
        Amount owed is 23.0
        You earned 4 frequent renter points""";
    String statement = customer1.statement(new TextePrinter());
    assertEquals(expected, statement);
  }
}
