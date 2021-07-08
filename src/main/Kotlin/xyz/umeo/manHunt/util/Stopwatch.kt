package xyz.umeo.manHunt.util

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.entity.Player
import java.util.*
import kotlin.concurrent.timer
import kotlin.math.pow

open class Stopwatch {
    private val list: List<Player> = ArrayList(Bukkit.getOnlinePlayers())
    private fun insertZero(num: Int): String {
        return if (num < 10) "0$num"
        else num.toString()
    }

    fun mcStopwatchStart() {
        xyz.umeo.manHunt.data.mcTimer = timer(period = 1000) {
            if(xyz.umeo.manHunt.data.runner?.world?.environment == World.Environment.NORMAL) xyz.umeo.manHunt.data.overWorldLocation = xyz.umeo.manHunt.data.runner!!.location
            if(xyz.umeo.manHunt.data.runner?.world?.environment == World.Environment.NETHER) xyz.umeo.manHunt.data.netherLocation = xyz.umeo.manHunt.data.runner!!.location

            if (xyz.umeo.manHunt.data.second >= 60) {
                xyz.umeo.manHunt.data.minute += 1
                xyz.umeo.manHunt.data.second -= 60
            }
            if (xyz.umeo.manHunt.data.minute >= 60) {
                xyz.umeo.manHunt.data.hour += 1
                xyz.umeo.manHunt.data.minute -= 60
            }
            for (players in list) {
                players.sendActionBar(Component.text(xyz.umeo.manHunt.data.hour.toString() + ":" + insertZero(xyz.umeo.manHunt.data.minute) + ":" + insertZero(xyz.umeo.manHunt.data.second)))
                players.hideBossBar(xyz.umeo.manHunt.data.bossBarFar)
                players.hideBossBar(xyz.umeo.manHunt.data.bossBarClose)
                if (distance()) players.showBossBar(xyz.umeo.manHunt.data.bossBarClose)
                else players.showBossBar(xyz.umeo.manHunt.data.bossBarFar)
            }
            xyz.umeo.manHunt.data.second++
        }
    }

    fun mcStopwatchStop() {
        xyz.umeo.manHunt.data.second = 0
        xyz.umeo.manHunt.data.minute = 0
        xyz.umeo.manHunt.data.hour = 0
        xyz.umeo.manHunt.data.mcTimer?.cancel()
    }

    private fun distance(): Boolean {
        var enoughDistance = false
        val distance = (list[0].location.x- list[1].location.x).pow(2.0) + (list[0].location.y- list[1].location.y).pow(2.0) + (list[0].location.z- list[1].location.z).pow(2.0)
        if (distance < xyz.umeo.manHunt.data.checkDistance) enoughDistance = true
        return enoughDistance
    }
}