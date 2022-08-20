package net.crazy.friendtags.core;

import net.crazy.friendtags.core.settings.StarSetting;
import net.crazy.friendtags.core.settings.TagSetting;
import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.annotation.SpriteTexture;
import net.labymod.api.configuration.loader.property.ConfigProperty;

@SuppressWarnings("FieldMayBeFinal")
@ConfigName("settings")
@SpriteTexture("tagformat.png")
public class AddonConfiguration extends AddonConfig {

  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  private TagSetting tagSetting = new TagSetting();

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
