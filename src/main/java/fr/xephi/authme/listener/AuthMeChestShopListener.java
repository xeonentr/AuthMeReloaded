package fr.xephi.authme.listener;

import com.Acrobot.ChestShop.Events.PreTransactionEvent;
import com.Acrobot.ChestShop.Events.PreTransactionEvent.TransactionOutcome;
import fr.xephi.authme.AuthMe;
import fr.xephi.authme.Utils;
import fr.xephi.authme.cache.auth.PlayerCache;
import fr.xephi.authme.settings.Settings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class AuthMeChestShopListener implements Listener {

    public AuthMe plugin;

    public AuthMeChestShopListener(AuthMe plugin) {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPreTransaction(PreTransactionEvent event) {
        if (event.getClient() == null) {
            return;
        }

        Player player = event.getClient();
        String name = player.getName().toLowerCase();

        if (Utils.isUnrestricted(player)) {
            return;
        }

        if (PlayerCache.getInstance().isAuthenticated(name)) {
            return;
        }

        if (!plugin.database.isAuthAvailable(name)) {
            if (!Settings.isForcedRegistrationEnabled) {
                return;
            }
        }
        event.setCancelled(TransactionOutcome.OTHER);
    }
}
