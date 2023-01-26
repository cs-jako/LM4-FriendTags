package net.crazy.friendtags.core.events;

import net.crazy.friendtags.core.FriendTagsAddon;
import net.labymod.api.client.entity.player.tag.event.NameTagBackgroundRenderEvent;
import net.labymod.api.event.Subscribe;

public class NameTagEvents {
  private final FriendTagsAddon addon;

  public NameTagEvents(FriendTagsAddon addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onRender(NameTagBackgroundRenderEvent event) {
    if (!addon.configuration().enabled().get())
      return;

    if (addon.configuration().tag().background())
      return;

    event.setCancelled(true);
  }
}
