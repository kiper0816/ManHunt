package xyz.umeo.manHunt

import io.papermc.paper.event.block.DragonEggFormEvent
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.inventory.ItemStack
import xyz.umeo.manHunt.util.Compass
import xyz.umeo.manHunt.util.StartEnd
import xyz.umeo.manHunt.util.Stopwatch

class EventListener: Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        event.player.sendMessage("➜ Plugin made by U.meo!")
        event.player.sendMessage("➜ Check my work in my github!")
        Bukkit.broadcast(Component.text("➜ Welcome to my Server! ${event.player.name}"))
        event.joinMessage(null) //default joinMessage disable.
    }

    @EventHandler
    fun onPlayerDie(event: PlayerDeathEvent) {
        //Runner dies game end.
        if (event.entity.type == EntityType.PLAYER && event.entity.name == xyz.umeo.manHunt.data.runner?.name) {
            event.deathMessage(null)
            event.entity.gameMode = GameMode.SPECTATOR
            Stopwatch().mcStopwatchStop()
            StartEnd().hunterWin()
        }
        //Hunter dies game continue.
        /*
        else if (event.entity.type == EntityType.PLAYER && event.entity.name == Player().returnHunter()?.name){
            event.deathMessage(null)
        }
         */
    }

    @EventHandler
    fun isDragonDefeated(event: DragonEggFormEvent) {
        StartEnd().runnerWin()
    }

    @EventHandler
    fun onHunterRespawn(event: PlayerRespawnEvent) {
        if (event.player.type == EntityType.PLAYER && event.player.name == xyz.umeo.manHunt.data.hunter?.name) {
            event.player.inventory.addItem(ItemStack(Material.COMPASS))
        }
    }

    @EventHandler //Compass points runner
    fun onRightClick(event: PlayerInteractEvent) {
        try {
            if (event.player.type == EntityType.PLAYER && event.player.name == xyz.umeo.manHunt.data.hunter?.name) {
                Compass().setCompassLocation()
            }
        }
        catch (e:Exception) { return }
    }

    @EventHandler
    fun onDisconnect(event: PlayerQuitEvent) {
        if (event.player.type == EntityType.PLAYER && event.player.name == xyz.umeo.manHunt.data.runner?.name) {
            event.player.gameMode = GameMode.SPECTATOR
            event.player.banPlayer("You are a Coward.")
            Stopwatch().mcStopwatchStop()
            StartEnd().hunterWin()
        }
    }
}