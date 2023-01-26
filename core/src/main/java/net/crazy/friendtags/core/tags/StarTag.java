package net.crazy.friendtags.core.tags;

import net.crazy.friendtags.core.FriendTagsAddon;
import net.crazy.friendtags.core.enums.NameTagLocation;
import net.labymod.api.client.entity.Entity;
import net.labymod.api.client.entity.player.Player;
import net.labymod.api.client.entity.player.tag.tags.IconTag;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.render.matrix.Stack;
import java.awt.*;

public class StarTag extends IconTag {

  private FriendTagsAddon addon;
  private NameTagLocation location;

  private StarTag(FriendTagsAddon addon, NameTagLocation location) {
    super(Icon.url("https://cs-jako.github.io/images/addons/assets/white_star.png"),
        8);
    this.addon = addon;
    this.location = location;
  }

  public static StarTag create(FriendTagsAddon addon, NameTagLocation location) {
    return new StarTag(addon, location);
  }

  @Override
  public void render(Stack stack, Entity livingEntity) {
    if (!addon.configuration().enabled().get())
      return;

    if (!addon.configuration().star().isEnabled())
      return;

    if (!(livingEntity instanceof Player))
      return;

    if (!addon.isFriend(livingEntity.getUniqueId()))
      return;

    if (!addon.configuration().star().getLocation().equals(location))
      return;

    this.labyAPI.renderPipeline().renderSeeThrough(entity, () -> {
      getIcon().render(stack, 16, 0, 8, 8, false, getColor());
    });
  }

  @Override
  public boolean isVisible() {
    if (!addon.configuration().star().isEnabled())
      return false;

    if (!addon.configuration().star().getLocation().equals(location))
      return false;

    if (this.entity == null)
      return false;

    return addon.isVisible(this.entity);
  }

  private int r = 255, g = 0, b = 0;

  @Override
  public int getColor() {
    if (addon.configuration().star().rgb()) {
      return rgbEffect().getRGB();
    }

    return addon.configuration().star().getColor();
  }

  private Color rgbEffect() {
    if (r > 0 && b == 0) {
      r--;
      g++;
    }

    if (g > 0 && r == 0) {
      g--;
      b++;
    }

    if (b > 0 && g == 0) {
      r++;
      b--;
    }

    return new Color(r, g, b);
  }
}
