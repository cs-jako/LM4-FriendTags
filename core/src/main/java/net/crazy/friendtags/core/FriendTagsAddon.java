package net.crazy.friendtags.core;

import com.google.inject.Singleton;
import net.crazy.friendtags.core.enums.NameTagLocation;
import net.crazy.friendtags.core.tags.FriendTag;
import net.crazy.friendtags.core.tags.StarTag;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.entity.LivingEntity;
import net.labymod.api.client.entity.player.tag.PositionType;
import net.labymod.api.client.entity.player.tag.TagRegistry;
import net.labymod.api.labyconnect.LabyConnect;
import net.labymod.api.labyconnect.protocol.model.friend.Friend;
import net.labymod.api.models.addon.annotation.AddonListener;
import java.util.UUID;

@Singleton
@AddonListener
public class FriendTagsAddon extends LabyAddon<AddonConfiguration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();

    TagRegistry tagRegistry = labyAPI().tagRegistry();

    tagRegistry.register("friendtags_tag", PositionType.ABOVE_NAME,
        FriendTag.create(this, NameTagLocation.ABOVE_NAME));
    tagRegistry.register("friendtags_tag", PositionType.BELOW_NAME,
        FriendTag.create(this, NameTagLocation.BELOW_NAME));
    tagRegistry.register("friendtags_tag", PositionType.LEFT_TO_NAME,
        FriendTag.create(this, NameTagLocation.LEFT_OF_NAME));
    tagRegistry.register("friendtags_tag", PositionType.RIGHT_TO_NAME,
        FriendTag.create(this, NameTagLocation.RIGHT_OF_NAME));

    tagRegistry.register("friendtags_star", PositionType.ABOVE_NAME,
        StarTag.create(this, NameTagLocation.ABOVE_NAME));
    tagRegistry.register("friendtags_star", PositionType.BELOW_NAME,
        StarTag.create(this, NameTagLocation.BELOW_NAME));
    tagRegistry.register("friendtags_star", PositionType.LEFT_TO_NAME,
        StarTag.create(this, NameTagLocation.LEFT_OF_NAME));
    tagRegistry.register("friendtags_star", PositionType.RIGHT_TO_NAME,
        StarTag.create(this, NameTagLocation.RIGHT_OF_NAME));
  }

  @Override
  protected Class<AddonConfiguration> configurationClass() {
    return AddonConfiguration.class;
  }

  public boolean isVisible(LivingEntity entity) {
    if (entity.isInvisible()) {
      return false;
    }

    if (entity.isCrouching()) {
      return false;
    }

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
