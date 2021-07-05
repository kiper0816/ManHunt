package xyz.umeo.manHunt.data

import org.bukkit.Location
import org.bukkit.entity.Player

//player role
var hunter:Player? = null
var runner:Player? = null

//runner heal command count
var isRunnerGetHealed = false

//runner location
var overWorldLocation:Location? = null
var netherLocation:Location? = null

//heal of distance info runner?
var checkDistance = 400.0

//Check some.
var NORMALNETHER = 0
