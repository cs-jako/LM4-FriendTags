package net.crazy.friendtags.core.tags;

import net.crazy.friendtags.core.FriendTagsAddon;
import net.kyori.adventure.text.Component;
import net.labymod.api.client.entity.LivingEntity;
import net.labymod.api.client.entity.player.Player;
import net.labymod.api.client.entity.player.tag.tags.NameTag;
import net.labymod.api.client.render.font.RenderableComponent;
import net.labymod.api.labyconnect.LabyConnect;
import net.labymod.api.labyconnect.protocol.model.friend.Friend;

public class FriendTag extends NameTag {

  private FriendTagsAddon addon;

  private FriendTag(FriendTagsAddon addon) {
    this.addon = addon;
  }

  public static FriendTag create(FriendTagsAddon addon) {
    return new FriendTag(addon);
  }

  @Override
  protected RenderableComponent renderableComponent(LivingEntity livingEntity) {
    if (!addon.configuration().enabled().get())
      return null;

    if (!(livingEntity instanceof Player))
      return null;

    LabyConnect labyConnect = addon.labyAPI().labyConnect();

    if (!labyConnect.isConnected())
      return null;

    Friend friend = labyConnect.getSession().getFriend(livingEntity.getUniqueId());

    if (friend == null)
      return null;

    return RenderableComponent.of(Component.text(
        addon.configuration().getFormat().get().replace('&', '§')
    ));
  }

  @Override
  public boolean isVisible(LivingEntity livingEntity) {
    if (livingEntity.isInvisible())
      return false;

    if (livingEntity.isCrouching())
      return false;

    double distance = addon.labyAPI().minecraft().clientPlayer().getDistanceSquared(livingEntity);

    if (distance > (double) (64.0F * 64.0F))
      return false;

    return true;
  }
}
