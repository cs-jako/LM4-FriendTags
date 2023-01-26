package net.crazy.friendtags.core;

import net.crazy.friendtags.core.enums.NameTagLocation;
import net.crazy.friendtags.core.events.NameTagEvents;
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

    tagRegistry.registerBefore("badge", "friendtags_tag", PositionType.ABOVE_NAME,
        FriendTag.create(this, NameTagLocation.ABOVE_NAME));
    tagRegistry.registerBefore("badge", "friendtags_tag", PositionType.BELOW_NAME,
        FriendTag.create(this, NameTagLocation.BELOW_NAME));
    tagRegistry.registerBefore("badge", "friendtags_tag", PositionType.LEFT_TO_NAME,
        FriendTag.create(this, NameTagLocation.LEFT_OF_NAME));
    tagRegistry.registerBefore("badge", "friendtags_tag", PositionType.RIGHT_TO_NAME,
        FriendTag.create(this, NameTagLocation.RIGHT_OF_NAME));

    tagRegistry.registerBefore("badge", "friendtags_star", PositionType.ABOVE_NAME,
        StarTag.create(this, NameTagLocation.ABOVE_NAME));
    tagRegistry.registerBefore("badge", "friendtags_star", PositionType.BELOW_NAME,
        StarTag.create(this, NameTagLocation.BELOW_NAME));
    tagRegistry.registerBefore("badge", "friendtags_star", PositionType.LEFT_TO_NAME,
        StarTag.create(this, NameTagLocation.LEFT_OF_NAME));
    tagRegistry.registerBefore("badge", "friendtags_star", PositionType.RIGHT_TO_NAME,
        StarTag.create(this, NameTagLocation.RIGHT_OF_NAME));

    this.registerListener(new NameTagEvents(this));
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
