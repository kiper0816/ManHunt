package xyz.umeo.manHunt.util

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class StartEnd {
    private val list: List<Player> = ArrayList(Bukkit.getOnlinePlayers())
    fun start() {
        for(players in list){
            players.sendTitle("ManHunt Started", "${ChatColor.YELLOW}" + "Runner: ${xyz.umeo.manHunt.data.runner?.name} / Hunter: ${xyz.umeo.manHunt.data.hunter?.name}", 10, 70, 10)
            players.playSound(players.location, org.bukkit.Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f)
        }
    }
    fun runnerWin() {
        for(players in list){
            players.sendTitle("WIN","${ChatColor.YELLOW}" + "#${xyz.umeo.manHunt.data.runner?.name} #Dragon #Slayer", 10, 70, 10)
            players.playSound(players.location, org.bukkit.Sound.UI_TOAST_CHALLENGE_COMPLETE, 1f, 1f)
        }
    }
    fun hunterWin() {
        for(players in list){
            players.sendTitle("WIN", "${ChatColor.YELLOW}" + "#${xyz.umeo.manHunt.data.hunter?.name} #Better #Minecrafter", 10, 70, 10)
            players.playSound(players.location, org.bukkit.Sound.UI_TOAST_CHALLENGE_COMPLETE, 1f, 1f)
        }
    }
}