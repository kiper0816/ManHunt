package xyz.umeo.manHunt.data

import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component
import org.bukkit.Location
import org.bukkit.entity.Player
import java.util.*

//player role
var hunter:Player? = null
var runner:Player? = null

//runner heal command count
var isRunnerGetHealed = false

//runner location
var overWorldLocation:Location? = null
var netherLocation:Location? = null

//heal of distance info runner?
var checkDistance = 3600.0

//Check some.
var NORMALNETHER = 0

//Timer
var mcTimer: Timer? = null
var second = 0
var minute = 0
var hour = 0

//Bossbar
val bossBarFar = BossBar.bossBar(Component.text("You are in mc.umeo.xyz!"), 1f, BossBar.Color.BLUE, BossBar.Overlay.PROGRESS )
val bossBarClose = BossBar.bossBar(Component.text("You are in mc.umeo.xyz!"), 1f, BossBar.Color.RED, BossBar.Overlay.PROGRESS )