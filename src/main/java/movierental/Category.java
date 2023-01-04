package movierental;

public enum Category {
  REGULAR(2, 2, 1.5),
  NEW_RELEASE(0, 0, 3),
  CHILDREN(1.5, 3, 1.5);

  final double initialAmount;
  final int daysBeforePenalty;
  final double penaltyCoefficient;

  Category(double initialAmount, int daysBeforePenalty, double penaltyCoefficient) {
    this.initialAmount = initialAmount;
    this.daysBeforePenalty = daysBeforePenalty;
    this.penaltyCoefficient = penaltyCoefficient;
  }
}
