package com.jesperancinha.imagecontour.objects

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

/**
  * Created by joaofilipesabinoesperancinha on 28-08-16.
  *
  * Please check the exaple:
  * {
  * "commands": [
  * {
  * "filter": "imageKuwahara",
  * "settings": [
  * {
  * "name": "square-size",
  * "value": 2
  * },
  * {
  * "name": "iterations",
  * "value": 2
  * }
  * ]
  * },
  * {
  * "filter": "imageContour",
  * "settings": [
  * {
  * "name": "bgColor",
  * "value": "0xFFFFFF"
  * },
  * {
  * "name": "lnColor",
  * "value": "0x000000"
  * },
  * {
  * "name": "diffThreshold",
  * "value": "800000"
  * },
  * {
  * "name": "radius",
  * "value": 2
  * }
  * ]
  * }
  * ]
  * }
  */

// domain model
final case class SettingItem(name: String, value: String)

final case class CommandContainer(filter: String, settings: List[SettingItem])

final case class Commands(commands: List[CommandContainer])

final case class Item(name: String, content: Array[String])

// collect your json format instances into a support trait:
trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol with Directives {

  implicit val settingItemFormat: RootJsonFormat[SettingItem] = jsonFormat2(SettingItem)
  implicit val commandContainerFormat: RootJsonFormat[CommandContainer] = jsonFormat2(CommandContainer)
  implicit val commandsFormat: RootJsonFormat[Commands] = jsonFormat1(Commands)
  implicit val itemFormat = jsonFormat2(Item)

  implicit class StringConversions(s: String) {
    def toIntFromHex: Int = Integer.decode(s)
  }

}

