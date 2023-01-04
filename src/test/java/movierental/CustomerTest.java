package movierental;

import static movierental.Movie.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {

  private static final Movie REGULAR_MOVIE = new Movie("Gone with the Wind", REGULAR);
  private static final Movie NEW_RELEASE_MOVIE = new Movie("Star Wars", NEW_RELEASE);
  private static final Movie CHILDRENS_MOVIE = new Movie("Madagascar", CHILDRENS);
  private static final String CUSTOMER_NAME = "Sallie";

  private static CustomerBuilder customerBuilder;

  @BeforeEach
  void init() {
    customerBuilder = new CustomerBuilder().withName(CUSTOMER_NAME);
  }

  @Test
  void testCustomer() {
    Customer c = new CustomerBuilder().build();
    assertNotNull(c);
  }

  @Test
  void addRental() {
    Customer customer = customerBuilder.build();
    Rental rental = new Rental(REGULAR_MOVIE, 3);
    customer.addRental(rental);
  }

  @Test
  void getName() {
    Customer c = new Customer(CUSTOMER_NAME);
    assertEquals(CUSTOMER_NAME, c.getName());
  }

  @Test
  void statement_regularMovie() {
    //Given
    Rental rental = new Rental(REGULAR_MOVIE, 3);
    Customer customer = customerBuilder.withRentals(rental).build();

    // When
    String statement = customer.statement(new TextePrinter());

    // Then
    String expected = """
        Rental Record for Sallie
        \tGone with the Wind\t3.5
        Amount owed is 3.5
        You earned 1 frequent renter points""";
    assertEquals(expected, statement);
  }

  @Test
  void statement_newReleaseMovie() {
    //Given
    Rental rental = new Rental(NEW_RELEASE_MOVIE, 3);
    Customer customer = customerBuilder.withRentals(rental).build();

    // When
    String statement = customer.statement(new TextePrinter());

    // Then
    String expected = """
        Rental Record for Sallie
        \tStar Wars\t9.0
        Amount owed is 9.0
        You earned 2 frequent renter points""";
    assertEquals(expected, statement);
  }

  @Test
  void statement_childrensMovie() {
    //Given
    Rental rental = new Rental(CHILDRENS_MOVIE, 3);
    Customer customer = customerBuilder.withRentals(rental).build();

    // When
    String statement = customer.statement(new TextePrinter());

    // Then
    String expected = """
        Rental Record for Sallie
        \tMadagascar\t1.5
        Amount owed is 1.5
        You earned 1 frequent renter points""";
    assertEquals(expected, statement);
  }

  @Test
  void statement_manyMovies() {
    //Given
    Rental rentalChidren = new Rental(CHILDRENS_MOVIE, 6);
    Rental rentalNewRelease = new Rental(NEW_RELEASE_MOVIE, 2);
    Rental rentalRegular = new Rental(REGULAR_MOVIE, 8);
    Customer customer = customerBuilder.withRentals(rentalChidren, rentalNewRelease, rentalRegular).build();

    // When
    String statement = customer.statement(new TextePrinter());

    // Then
    String expected = """
        Rental Record for Sallie
        \tMadagascar\t6.0
        \tStar Wars\t6.0
        \tGone with the Wind\t11.0
        Amount owed is 23.0
        You earned 4 frequent renter points""";
    assertEquals(expected, statement);
  }
}
