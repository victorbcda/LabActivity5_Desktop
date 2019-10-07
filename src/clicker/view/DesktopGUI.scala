package clicker.view

import io.socket.client.{IO, Socket}
import io.socket.emitter.Emitter
import javafx.application.Platform
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene

class HandleMessagesFromServer() extends Emitter.Listener {
  override def call(objects: Object*): Unit = {

    // Use runLater when interacting with the GUI
    Platform.runLater(() => {
      // This method is called whenever a new game state is received from the server
      val jsonGameState = objects.apply(0).toString
      println(jsonGameState)

      // TODO: Display the game state on your GUI
      // You must display: current gold, and the name, number owned, and cost for each type of equipment

      // You can access any variables/methods in the DesktopGUI object from this class
      // ex. DesktopGUI.goldTextField.text = goldFromGameState

    })

  }
}


object DesktopGUI extends JFXApp {

  var socket: Socket = IO.socket("https://tictactoe.info/")
  socket.on("gameState", new HandleMessagesFromServer)
  socket.connect()

  // Change "test" to any username you'd like to start a new game
  socket.emit("register", "test")

  // Call this method whenever the user clicks your gold button
  def clickGold(): Unit = {
    socket.emit("clickGold")
  }

  // Call this method whenever the user clicks to purchase equipment
  // The parameter is the id of the equipment type to purchase
  def buyEquipment(equipmentId: String): Unit = {
    socket.emit("buy", equipmentId)
  }

  // TODO: Setup your GUI
  // You may create and place all GUI elements (TextFields, Buttons, etc) then only change the text on
  // each element when a new game state is received
  // You may assume that there will be exactly 3 types of equipment with ids of "shovel", "excavator", and "mine"

  this.stage = new PrimaryStage {
    title = "CSE Clicker"
    scene = new Scene() {
      content = List(
        // TODO: Add elements to your GUI
      )
    }

  }

}
