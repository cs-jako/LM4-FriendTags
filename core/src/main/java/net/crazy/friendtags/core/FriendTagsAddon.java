package net.crazy.friendtags.core;

import com.google.inject.Singleton;
import net.crazy.friendtags.core.tags.FriendTag;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.entity.player.tag.PositionType;
import net.labymod.api.client.entity.player.tag.TagRegistry;
import net.labymod.api.models.addon.annotation.AddonListener;

@Singleton
@AddonListener
public class FriendTagsAddon extends LabyAddon<AddonConfiguration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();

    TagRegistry tagRegistry = labyAPI().tagRegistry();
    tagRegistry.register("friendtag", PositionType.ABOVE_NAME, FriendTag.create(this));
  }

  @Override
  protected Class<AddonConfiguration> configurationClass() {
    return AddonConfiguration.class;
  }
}
