package xyz.umeo.manHunt

import org.bukkit.Bukkit
import org.bukkit.command.CommandExecutor
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

open class Main: JavaPlugin(), Listener, CommandExecutor{
    override fun onEnable(){
        logger.info("manHunt is now enabled.")
        registerEvents(this, EventListener())

        getCommand("set")!!.setExecutor(Command())
        getCommand("heal")!!.setExecutor(Command())
        getCommand("debug")!!.setExecutor(Command())
        getCommand("start")!!.setExecutor(Command())
        getCommand("reset")!!.setExecutor(Command())
    }
    override fun onDisable() = logger.info("manHunt is now disabled.")

    open fun registerEvents(plugin: Plugin?, vararg listeners: Listener?) { for (listener in listeners) Bukkit.getServer().pluginManager.registerEvents(listener!!, plugin!!) }
}


