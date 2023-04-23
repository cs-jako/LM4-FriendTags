package net.crazy.friendtags.core.tags;

import net.crazy.friendtags.core.FriendTagsAddon;
import net.crazy.friendtags.core.enums.NameTagLocation;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.entity.Entity;
import net.labymod.api.client.entity.player.Player;
import net.labymod.api.client.entity.player.tag.tags.NameTag;
import net.labymod.api.client.entity.player.tag.tags.NameTagBackground;
import net.labymod.api.client.render.font.RenderableComponent;
import java.awt.*;

public class FriendTag extends NameTag {

  private FriendTagsAddon addon;
  private NameTagLocation location;

  private FriendTag(FriendTagsAddon addon, NameTagLocation location) {
    this.addon = addon;
    this.location = location;
  }

  public static FriendTag create(FriendTagsAddon addon, NameTagLocation location) {
    return new FriendTag(addon, location);
  }

  @Override
  protected RenderableComponent getRenderableComponent() {
    if (!addon.configuration().enabled().get())
      return null;

    if (!addon.configuration().tag().isEnabled())
      return null;

    Entity livingEntity = this.entity;

    if (livingEntity == null)
      return null;

    if (!(livingEntity instanceof Player))
      return null;

    if (!addon.isFriend(livingEntity.getUniqueId()))
      return null;

    if (!addon.configuration().tag().getLocation().equals(location))
      return null;

    return RenderableComponent.of(Component.text(
        addon.configuration().tag().getFormat().replace('&', '§')
    ));
  }

  @Override
  public boolean isVisible() {
    return super.isVisible() && addon.isVisible(this.entity);
  }

  @Override
  protected NameTagBackground getCustomBackground() {
    boolean enabled = addon.configuration().tag().background();
    NameTagBackground nameTagBackground = super.getCustomBackground();

    if (nameTagBackground == null)
      nameTagBackground = NameTagBackground.custom(enabled, Color.BLACK.getRGB());

    nameTagBackground.setEnabled(enabled);
    return nameTagBackground;
  }
}
