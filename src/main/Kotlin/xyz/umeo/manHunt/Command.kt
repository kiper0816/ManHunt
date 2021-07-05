package xyz.umeo.manHunt

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.umeo.manHunt.util.Heal
import xyz.umeo.manHunt.util.StartEnd
import xyz.umeo.manHunt.util.Stopwatch

class Command: CommandExecutor{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player){
            when (command.name){
                "set" -> {
                    if (args[0] == "hunter") {
                        xyz.umeo.manHunt.util.Player().setPlayer(args[1], "hunter")
                        Bukkit.broadcast(Component.text(xyz.umeo.manHunt.data.hunter!!.name))
                    } else if (args[0] == "runner") {
                        xyz.umeo.manHunt.util.Player().setPlayer(args[1], "runner")
                    }
                }
                "heal" -> {
                    if (sender.name == xyz.umeo.manHunt.data.runner?.name) {
                        sender.sendMessage("Testing.")
                        Heal().heal()
                    }
                }
                "start" -> {
                    Stopwatch().mcStopwatchStart()
                    StartEnd().start()
                }
                "debug" -> {
                    xyz.umeo.manHunt.util.Player().setPlayer("U_meo", "hunter")
                    xyz.umeo.manHunt.util.Player().setPlayer("AIGamer", "runner")
                    Stopwatch().mcStopwatchStart()
                    StartEnd().start()
                }
            }
        }
        else {
            sender.sendMessage("You can't run this command in console.")
        }
        return true
    }
}