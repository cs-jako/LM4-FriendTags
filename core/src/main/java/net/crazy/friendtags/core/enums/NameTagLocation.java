package net.crazy.friendtags.core.enums;

public enum NameTagLocation {
  ABOVE_NAME("Above name"),
  BELOW_NAME("Below name"),
  LEFT_OF_NAME("Left side"),
  RIGHT_OF_NAME("Right side");

  private final String display;
  NameTagLocation(String display) {
    this.display = display;
  }


  @Override
  public String toString() {
    return this.display;
  }
}
