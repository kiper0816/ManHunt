package xyz.umeo.manHunt.util

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class Player {
    private val list: List<Player> = ArrayList(Bukkit.getOnlinePlayers())
    fun setPlayer(name: String, role:String): Boolean {
        for (player in list) {
            if (name == player.name) {
                when (role) {
                    "hunter" -> {
                        xyz.umeo.manHunt.data.hunter = player
                        xyz.umeo.manHunt.data.hunter?.inventory?.addItem(ItemStack(Material.COMPASS))
                    }
                    "runner" -> xyz.umeo.manHunt.data.runner = player
                }
                Bukkit.broadcast(Component.text(player.name+" is now "+role+"!"))
            }
        }
        return true
    }
}