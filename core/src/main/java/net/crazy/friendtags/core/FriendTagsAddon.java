package net.crazy.friendtags.core;

import net.crazy.friendtags.core.enums.NameTagLocation;
import net.crazy.friendtags.core.tags.FriendTag;
import net.crazy.friendtags.core.tags.StarTag;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.entity.Entity;
import net.labymod.api.client.entity.player.Player;
import net.labymod.api.client.entity.player.tag.PositionType;
import net.labymod.api.client.entity.player.tag.TagRegistry;
import net.labymod.api.labyconnect.LabyConnect;
import net.labymod.api.labyconnect.protocol.model.friend.Friend;
import net.labymod.api.models.addon.annotation.AddonMain;
import java.util.UUID;

@AddonMain
public class FriendTagsAddon extends LabyAddon<AddonConfiguration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();

    TagRegistry tagRegistry = labyAPI().tagRegistry();
    for (PositionType positionType : PositionType.values()) {
      tagRegistry.registerBefore("badge", "friendtags_tag", positionType,
          FriendTag.create(this, NameTagLocation.getNameTagLocation(positionType)));
      tagRegistry.registerBefore("badge", "friendtags_star", positionType,
          StarTag.create(this, NameTagLocation.getNameTagLocation(positionType)));
    }
  }

  @Override
  protected Class<AddonConfiguration> configurationClass() {
    return AddonConfiguration.class;
  }

  public boolean isVisible(Entity entity) {
    if (entity.isInvisible()) {
      return false;
    }

    if (entity.isCrouching()) {
      return false;
    }

    if (!(entity instanceof Player))
      return false;

    if (!isFriend(entity.getUniqueId()))
      return false;

    if (entity.getUniqueId().equals(labyAPI().minecraft().clientPlayer().getUniqueId()))
      return false;

    double distance = labyAPI().minecraft().clientPlayer().getDistanceSquared(entity);

    if (distance > (double) (64.0F * 64.0F)) {
      return false;
    }

    return true;
  }

  public boolean isFriend(UUID uuid) {
    LabyConnect labyConnect = labyAPI().labyConnect();

    if (!labyConnect.isConnected()) {
      return false;
    }

    Friend friend = labyConnect.getSession().getFriend(uuid);

    if (friend == null) {
      return false;
    }

    return true;
  }
}
