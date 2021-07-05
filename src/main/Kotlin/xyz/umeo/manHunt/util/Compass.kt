package xyz.umeo.manHunt.util

import org.bukkit.Material
import org.bukkit.World
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.CompassMeta

class Compass {
    private val runner = xyz.umeo.manHunt.data.runner
    private val hunter = xyz.umeo.manHunt.data.hunter
    /*
    fun setCompassLocation(){
        val meta: CompassMeta?
        if(runner?.world?.environment == World.Environment.NORMAL && hunter?.world?.environment == World.Environment.NORMAL) { //Clear
            hunter.compassTarget = runner.location
            hunter.sendMessage("Runner's Location is refreshed.")
        }
        else if (runner?.world?.environment == World.Environment.NORMAL && hunter?.world?.environment == World.Environment.NETHER) { //Clear
            hunter.sendMessage("Runner's Location is refreshed.")
            //Just Return resent runner's nether location. I can't save the portal's location. 군대가는데 너무 귀찮다...
        }
        else if (runner?.world?.environment == World.Environment.NORMAL && hunter?.world?.environment == World.Environment.THE_END) { //Clear
            hunter.sendMessage("Runner's Location is refreshed.")
        }
        else if (runner?.world?.environment == World.Environment.NETHER && hunter?.world?.environment == World.Environment.NORMAL) { //Clear
            if (portalLocation != null) hunter.compassTarget = portalLocation!!
            hunter.sendMessage("Runner's Location is refreshed.")
        }
        else if (runner?.world?.environment == World.Environment.NETHER && hunter?.world?.environment == World.Environment.NETHER) { //Clear
            meta = hunter.itemInHand.itemMeta as CompassMeta?
            runner.location.subtract(0.0,runner.location.y,0.0).block.type = Material.LODESTONE
            if (meta != null) {
                meta.lodestone = runner.location.subtract(0.0,runner.location.y,0.0)
                meta.isLodestoneTracked = true
                hunter.itemInHand.itemMeta = meta
            }
            hunter.sendMessage("Runner's Location is refreshed.")
        }
        else if (runner?.world?.environment == World.Environment.NETHER && hunter?.world?.environment == World.Environment.THE_END) { //Clear
            hunter.sendMessage("Runner's Location is refreshed.")
        }
        else if (runner?.world?.environment == World.Environment.THE_END && hunter?.world?.environment == World.Environment.NORMAL) { //Clear
            if (portalEnterLocation != null) hunter.compassTarget = portalEnterLocation!!
            hunter.sendMessage("Runner's Location is refreshed.")
        }
        else if (runner?.world?.environment == World.Environment.THE_END && hunter?.world?.environment == World.Environment.THE_END) { //Clear
            hunter.sendMessage("You can't refresh runner's location in the end.")
        }
        else if (runner?.world?.environment == World.Environment.THE_END && hunter?.world?.environment == World.Environment.NETHER) { //Clear
            hunter.sendMessage("You can't refresh runner's location in the nether while runner is in the end.")
            //I'm dumb at this case.
        }
     */

    fun setCompassLocation() {
        if(hunter?.player!!.itemInHand == ItemStack(Material.COMPASS) || (hunter.player!!.itemInHand.itemMeta as CompassMeta).isLodestoneTracked){
            if (runner!!.world.environment == World.Environment.NORMAL && hunter.world.environment == World.Environment.NORMAL) {
                removeMeta()
                hunter.compassTarget = xyz.umeo.manHunt.data.overWorldLocation!!
                xyz.umeo.manHunt.data.NORMALNETHER = 0

            } else if (runner.world.environment == World.Environment.NORMAL && hunter.world.environment == World.Environment.NETHER) {
                if (xyz.umeo.manHunt.data.netherLocation != null && xyz.umeo.manHunt.data.NORMALNETHER == 0) {
                    removeMeta()
                    netherCompass()
                    xyz.umeo.manHunt.data.NORMALNETHER++
                }
                hunter.sendMessage("Runner's Location is refreshed.")

            } else if (runner.world.environment == World.Environment.NORMAL && hunter.world.environment == World.Environment.THE_END) {
                hunter.sendMessage("Runner's Location is refreshed.")

            } else if (runner.world.environment == World.Environment.NETHER && hunter.world.environment == World.Environment.NORMAL) {
                removeMeta()
                hunter.compassTarget = xyz.umeo.manHunt.data.overWorldLocation!!
                hunter.sendMessage("Runner's Location is refreshed.")

            } else if (runner.world.environment == World.Environment.NETHER && hunter.world.environment == World.Environment.NETHER) { //Clear
                removeMeta()
                netherCompass()
                hunter.sendMessage("Runner's Location is refreshed.")

            } else if (runner.world.environment == World.Environment.NETHER && hunter.world.environment == World.Environment.THE_END) { //Clear
                hunter.sendMessage("Runner's Location is refreshed.")

            } else if (runner.world.environment == World.Environment.THE_END && hunter.world.environment == World.Environment.NORMAL) { //Clear
                removeMeta()
                hunter.compassTarget = xyz.umeo.manHunt.data.overWorldLocation!!
                hunter.sendMessage("Runner's Location is refreshed.")

            } else if (runner.world.environment == World.Environment.THE_END && hunter.world.environment == World.Environment.THE_END) { //Clear
                hunter.sendMessage("You can't refresh runner's location in the end.")

            } else if (runner.world.environment == World.Environment.THE_END && hunter.world.environment == World.Environment.NETHER) { //Clear
                hunter.sendMessage("IDK") //I'm dumb at this case
            }
        }
    }
    private fun removeMeta() {
        hunter?.inventory?.removeItem(ItemStack(xyz.umeo.manHunt.data.hunter!!.itemInHand))
        hunter?.inventory?.addItem(ItemStack(Material.COMPASS))
    }

    private fun netherCompass() {
        val meta: CompassMeta? = hunter?.itemInHand!!.itemMeta as CompassMeta?
        xyz.umeo.manHunt.data.netherLocation!!.subtract(0.0, xyz.umeo.manHunt.data.netherLocation!!.y, 0.0).block.type = Material.LODESTONE
        if (meta != null) {
            meta.lodestone = xyz.umeo.manHunt.data.netherLocation!!.subtract(
                0.0,
                xyz.umeo.manHunt.data.netherLocation!!.y, 0.0
            )
            meta.isLodestoneTracked = true
            hunter.itemInHand.itemMeta = meta
        }
    }
}