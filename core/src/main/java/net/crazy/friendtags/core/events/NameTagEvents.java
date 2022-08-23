package net.crazy.friendtags.core.events;

import net.crazy.friendtags.core.FriendTagsAddon;
import net.labymod.api.client.entity.player.tag.event.NameTagBackgroundRenderEvent;
import net.labymod.api.event.Subscribe;
import net.labymod.api.inject.LabyGuice;

public class NameTagEvents {
  private final FriendTagsAddon addon;

  public NameTagEvents() {
    this.addon = LabyGuice.getInstance(FriendTagsAddon.class);
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
