package de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.types;

public class Pair<X, Y> {
  public final X left;
  public final Y right;

  public Pair(X left, Y right) {
    this.left = left;
    this.right = right;
  }
}