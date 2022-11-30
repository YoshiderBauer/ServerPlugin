package de.yoshi.serverplugin.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class rightClickListener implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEntityEvent event){
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();

        if(player.isSneaking() && entity.getType() == EntityType.ITEM_FRAME){
            event.setCancelled(true);
            ItemFrame itemFrame = (ItemFrame) entity;
            if(itemFrame.isVisible()){
                itemFrame.setVisible(false);
            } else {
                itemFrame.setVisible(true);
            }
        }
    }
}
