package net.crazy.friendtags.core.enums;

import net.labymod.api.client.entity.player.tag.PositionType;

public enum NameTagLocation {
  ABOVE_NAME("Above name", PositionType.ABOVE_NAME),
  BELOW_NAME("Below name", PositionType.BELOW_NAME),
  LEFT_OF_NAME("Left side", PositionType.LEFT_TO_NAME),
  RIGHT_OF_NAME("Right side", PositionType.RIGHT_TO_NAME);

  private final String display;
  private final PositionType positionType;

  NameTagLocation(String display, PositionType positionType) {
    this.display = display;
    this.positionType = positionType;
  }


  @Override
  public String toString() {
    return this.display;
  }

  public static NameTagLocation getNameTagLocation(PositionType type) {
    for (NameTagLocation nameTagLocation : values()) {
      if (nameTagLocation.positionType.equals(type)) {
        return nameTagLocation;
      }
    }
    return null;
  }
}
