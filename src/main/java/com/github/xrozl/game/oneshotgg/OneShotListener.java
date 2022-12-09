package com.github.xrozl.game.oneshotgg;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class OneShotListener implements Listener {

    OneShotGG plugin;
    public OneShotListener(OneShotGG oneShotGG) {
        plugin = oneShotGG;
    }

    @EventHandler
    public void on(EntityShootBowEvent ev) {
        if (!(ev.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) ev.getEntity();
        List<LivingEntity> target = getInsightEntity(player, 100d, 0.25d);
        if (target.size() == 0) {
            return;
        }

        LivingEntity entity = target.get(0);
        entity.damage(1000d, player);

        player.sendMessage("Killed " + entity.getName());
    }

    List<LivingEntity> getInsightEntity(Player player, double range, double by) {
        List<LivingEntity> entities = new ArrayList<>();
        List<Location> locations = new ArrayList<>();
        Location eye = player.getEyeLocation();
        Vector direction = eye.getDirection();
        for (double i = 0d; i < range; i+=by) {
            direction.multiply(i);
            eye.add(direction);
            eye.subtract(direction);
            direction.normalize();

            locations.add(eye);
        }

        for (Location location : locations) {
            for (LivingEntity entity : player.getWorld().getLivingEntities()) {
                if (entity.getLocation().distance(location) < 1) {
                    entities.add(entity);
                }
            }
        }

        return entities;
    }
}
