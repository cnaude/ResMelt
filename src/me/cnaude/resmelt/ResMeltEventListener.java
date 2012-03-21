/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.cnaude.resmelt;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;

public class ResMeltEventListener implements Listener { 
    private final ResMelt plugin;
    
    public ResMeltEventListener(ResMelt Instance) {
        plugin = Instance;
    }
       
    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockMelt(BlockFadeEvent event) {
        if(event.isCancelled())
            return;
        ClaimedResidence res = Residence.getResidenceManager().getByLoc(event.getBlock().getLocation());
        if(res!=null) {
            if(!res.getPermissions().has("melt", true)) {
                event.setCancelled(true);
            }
        }
        else {
            if (!Residence.getWorldFlags().getPerms(event.getBlock().getWorld().getName()).has("melt", true)) {
                event.setCancelled(true);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockMelt(BlockFormEvent event) {
        if(event.isCancelled())
            return;
        ClaimedResidence res = Residence.getResidenceManager().getByLoc(event.getBlock().getLocation());
        if(res!=null) {
            if(!res.getPermissions().has("form", true)) {
                event.setCancelled(true);
            }
        }
        else {
            if (!Residence.getWorldFlags().getPerms(event.getBlock().getWorld().getName()).has("form", true)) {
                event.setCancelled(true);
            }
        }
    }
 
}
