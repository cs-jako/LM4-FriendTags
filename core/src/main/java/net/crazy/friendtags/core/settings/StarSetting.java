package net.crazy.friendtags.core.settings;

import net.crazy.friendtags.core.enums.NameTagLocation;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.color.ColorPickerWidget.ColorPickerSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.dropdown.DropdownWidget.DropdownSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.ParentSwitch;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.util.MethodOrder;
import java.awt.*;

public class StarSetting extends Config {

  @ParentSwitch
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @MethodOrder(after = "enabled")
  @ColorPickerSetting
  private final ConfigProperty<Integer> starColor = new ConfigProperty<>(Color.YELLOW.getRGB());

  @MethodOrder(after = "starColor")
  @DropdownSetting
  private final ConfigProperty<NameTagLocation> location = new ConfigProperty<>(NameTagLocation.RIGHT_OF_NAME);

  public boolean isEnabled() {
    return enabled.get();
  }

  public int getColor() {
    return starColor.get();
  }

  public NameTagLocation getLocation() {
    return location.get();
  }
}
