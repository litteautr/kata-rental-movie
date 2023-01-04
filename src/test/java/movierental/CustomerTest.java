package movierental;

import static movierental.Category.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {

  private static final Movie REGULAR_MOVIE = new Movie("Gone with the Wind", REGULAR);
  private static final Movie NEW_RELEASE_MOVIE = new Movie("Star Wars", NEW_RELEASE);
  private static final Movie CHILDRENS_MOVIE = new Movie("Madagascar", CHILDREN);
  private static final String CUSTOMER_NAME = "Sallie";

  private static CustomerBuilder customerBuilder;

  @BeforeEach
  void init() {
    customerBuilder = new CustomerBuilder().withName(CUSTOMER_NAME);
  }

  @Test
  void testCustomer() {
    Customer customer = new CustomerBuilder().build();
    assertNotNull(customer);
  }

  @Test
  void addRental() {
    Customer customer = customerBuilder.build();
    Rental rental = new Rental(REGULAR_MOVIE, 3);
    customer.addRental(rental);
  }

  @Test
  void getName() {
    Customer customer = new Customer(CUSTOMER_NAME);
    assertThat(customer.getName()).isEqualTo(CUSTOMER_NAME);
  }

  @Test
  void statement_withTextePrinter_regularMovie() {
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
    assertThat(statement).isEqualTo(expected);
  }

  @Test
  void statement_withTextePrinter_newReleaseMovie() {
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
    assertThat(statement).isEqualTo(expected);
  }

  @Test
  void statement_withTextePrinter_childrensMovie() {
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
    assertThat(statement).isEqualTo(expected);
  }

  @Test
  void statement_withTextePrinter_manyMovies() {
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
    assertThat(statement).isEqualTo(expected);
  }

  @Test
  void statement_withHtmlPrinter_regularMovie() {
    //Given
    Rental rental = new Rental(REGULAR_MOVIE, 3);
    Customer customer = customerBuilder.withRentals(rental).build();

    // When
    String statement = customer.statement(new HtmlPrinter());

    // Then
    String expected = """
        <h1>Rental Record for <em>Sallie</em></h1>
        <table>
        \t<tr><td>Gone with the Wind</td><td>3.5</td></tr>
        </table>
        <p>Amount owed is <em>3.5</em></p>
        <p>You earned <em>1</em> frequent renter points</p>""";
    assertThat(statement).isEqualTo(expected);
  }
}
