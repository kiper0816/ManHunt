package xyz.umeo.manHunt.util

import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.entity.Player
import java.util.*
import kotlin.concurrent.timer
import kotlin.math.pow

open class Stopwatch {
    private var second = 0
    private var minute = 0
    private var hour = 0
    private val list: List<Player> = ArrayList(Bukkit.getOnlinePlayers())
    private var mcTimer: Timer? = null
    private val bossBarFar = BossBar.bossBar(Component.text("You are in mc.umeo.xyz!"), 1f, BossBar.Color.BLUE, BossBar.Overlay.PROGRESS )
    private val bossBarClose = BossBar.bossBar(Component.text("You are in mc.umeo.xyz!"), 1f, BossBar.Color.RED, BossBar.Overlay.PROGRESS )

    private fun insertZero(num: Int): String {
        return if (num < 10) "0$num"
        else num.toString()
    }

    fun mcStopwatchStart() {
        mcTimer = timer(period = 1000) {
            if(xyz.umeo.manHunt.data.runner?.world?.environment == World.Environment.NORMAL) xyz.umeo.manHunt.data.overWorldLocation = xyz.umeo.manHunt.data.runner!!.location
            if(xyz.umeo.manHunt.data.runner?.world?.environment == World.Environment.NETHER) xyz.umeo.manHunt.data.netherLocation = xyz.umeo.manHunt.data.runner!!.location

            if (second >= 60) {
                minute += 1
                second -= 60
            }
            if (minute >= 60) {
                hour += 1
                minute -= 60
            }
            for (players in list) {
                players.sendActionBar(Component.text(hour.toString() + ":" + insertZero(minute) + ":" + insertZero(second)))
                players.hideBossBar(bossBarFar)
                players.hideBossBar(bossBarClose)
                if (distance()) players.showBossBar(bossBarClose)
                else players.showBossBar(bossBarFar)
            }
            second++
        }
    }

    fun mcStopwatchStop() {
        second = 0
        minute = 0
        hour = 0
        mcTimer?.cancel()
    }

    private fun distance(): Boolean {
        var enoughDistance = false
        val distance = (list[0].location.x- list[1].location.x).pow(2.0) + (list[0].location.y- list[1].location.y).pow(2.0) + (list[0].location.z- list[1].location.z).pow(2.0)
        if (distance < xyz.umeo.manHunt.data.checkDistance) enoughDistance = true
        return enoughDistance
    }

}