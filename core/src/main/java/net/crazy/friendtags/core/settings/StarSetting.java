package net.crazy.friendtags.core.settings;

import net.crazy.friendtags.core.enums.NameTagLocation;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.color.ColorPickerWidget.ColorPickerSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.dropdown.DropdownWidget.DropdownSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.ParentSwitch;
import net.labymod.api.configuration.loader.annotation.SpriteSlot;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingSection;
import net.labymod.api.util.MethodOrder;
import java.awt.*;

public class StarSetting extends Config {

  @ParentSwitch
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @MethodOrder(after = "enabled")
  @SpriteSlot(x = 1, y = 1)
  @ColorPickerSetting
  private final ConfigProperty<Integer> starColor = new ConfigProperty<>(Color.YELLOW.getRGB());

  @SpriteSlot(x = 2)
  @DropdownSetting
  private final ConfigProperty<NameTagLocation> location = new ConfigProperty<>(NameTagLocation.RIGHT_OF_NAME);

  @SettingSection("experimental")
  @SwitchSetting
  private final ConfigProperty<Boolean> rgb = new ConfigProperty<>(false);

  public boolean isEnabled() {
    return enabled.get();
  }

  public int getColor() {
    return starColor.get();
  }

  public NameTagLocation getLocation() {
    return location.get();
  }

  public boolean rgb() {
    return rgb.get();
  }
}
