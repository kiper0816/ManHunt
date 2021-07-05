package xyz.umeo.manHunt.util

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Heal {
    fun heal() {
        if(xyz.umeo.manHunt.data.isRunnerGetHealed) {
            xyz.umeo.manHunt.data.runner?.sendMessage("You need more chance? Oh....!")
            xyz.umeo.manHunt.data.runner?.addPotionEffect(PotionEffect(PotionEffectType.HUNGER, 240, 60, true))
        }
        else {
            xyz.umeo.manHunt.data.runner?.sendMessage("Cooked Beef is yours. But you'll pay for this.")
            for(i in 1..8) xyz.umeo.manHunt.data.runner?.inventory?.addItem(ItemStack(Material.COOKED_BEEF))
            xyz.umeo.manHunt.data.isRunnerGetHealed = true
            xyz.umeo.manHunt.data.checkDistance = 3.0
        }
    }
}