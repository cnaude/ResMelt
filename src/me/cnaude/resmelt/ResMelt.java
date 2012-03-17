package me.cnaude.resmelt;

import com.bekvon.bukkit.residence.protection.FlagPermissions;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
/**
 *
 * @author Chris Naude
 */
public class ResMelt extends JavaPlugin {
    public static final String PLUGIN_NAME = "ResMelt";
    public static final String LOG_HEADER = "[" + PLUGIN_NAME + "] ";
    private static ResMelt instance = null;
    
    private Logger log;    
    private final ResMeltEventListener eventListener = new ResMeltEventListener(this);
    
    public static ResMelt get() {
        return instance;
    }

    @Override
    public void onEnable() {
        //this.loadConfig();
        log = Logger.getLogger("Minecraft");

        Plugin plugin = getServer().getPluginManager().getPlugin(PLUGIN_NAME);            
        
        PluginManager pm = getServer().getPluginManager();
        Plugin p = pm.getPlugin("Residence");
        if(p!=null) {
            if(!p.isEnabled()) {
                logInfo("Manually Enabling Residence!");
                pm.enablePlugin(p);
                FlagPermissions.addResidenceOnlyFlag("melt");
                FlagPermissions.addFlag("melt");
                getServer().getPluginManager().registerEvents(eventListener, this);
            }
        }
        else {
            logInfo("Residence NOT Installed, DISABLED!");
            this.setEnabled(false);
        }
    }

    public void logInfo(String _message) {
        log.log(Level.INFO,String.format("%s %s",LOG_HEADER,_message));
    }

    public void logError(String _message) {
        log.log(Level.SEVERE,String.format("%s %s",LOG_HEADER,_message));
    }
    /*
    public ResMelt getSConfig() {
        return config;
    }
    
    void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        config = new ResMeltConfig(this);
    }
   */
}
