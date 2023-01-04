package movierental;

public record Movie(String title, int priceCode) {

  public static final int CHILDRENS = 2;
  public static final int NEW_RELEASE = 1;
  public static final int REGULAR = 0;

}
