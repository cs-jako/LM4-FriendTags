package net.crazy.friendtags.core;

import net.crazy.friendtags.core.settings.StarSetting;
import net.crazy.friendtags.core.settings.TagSetting;
import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.annotation.SpriteSlot;
import net.labymod.api.configuration.loader.annotation.SpriteTexture;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingSection;
import net.labymod.api.util.MethodOrder;

@SuppressWarnings("FieldMayBeFinal")
@ConfigName("settings")
@SpriteTexture("settings.png")
public class AddonConfiguration extends AddonConfig {

  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SettingSection("settings")
  @MethodOrder(after = "enabled")
  @SpriteSlot()
  private TagSetting tagSetting = new TagSetting();

  @MethodOrder(after = "tagSetting")
  @SpriteSlot(y = 1)
  private StarSetting starSetting = new StarSetting();

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public TagSetting tag() {
    return tagSetting;
  }

  public StarSetting star() {
    return starSetting;
  }
}
