package net.crazy.friendtags.core.tags;

import net.crazy.friendtags.core.FriendTagsAddon;
import net.crazy.friendtags.core.enums.NameTagLocation;
import net.labymod.api.client.entity.LivingEntity;
import net.labymod.api.client.entity.player.Player;
import net.labymod.api.client.entity.player.tag.tags.IconTag;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.render.matrix.Stack;

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
  public void render(Stack stack, LivingEntity livingEntity) {
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

    super.render(stack, livingEntity);
  }

  @Override
  public boolean isVisible(LivingEntity livingEntity) {
    if (!addon.configuration().star().getLocation().equals(location))
      return false;

    return addon.isVisible(livingEntity);
  }

  @Override
  public int getColor(LivingEntity livingEntity) {
    return addon.configuration().star().getColor();
  }
}
