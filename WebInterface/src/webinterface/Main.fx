/*
 * Main.fx
 *
 * Created on 04/05/2010, 19:23:53
 */

package webinterface;

import javafx.stage.Stage;
import javafx.scene.Scene;
import webinterface.ui.MainInterface;
import javafx.scene.image.Image;

/**
 * The width of the scene.
 */
var width: Float = 800;

/**
 * The height of the scene.
 */
var height: Float = 600;

/**
 * Main interface.
 */
 def main: MainInterface =
    MainInterface {
        x: 50
        y: 20
    }

/**
 * Stage of WebInterface.
 */
Stage {
    title: "DBMI - Dado Bongo Meteorological Information"
    resizable: false
    iconified: true
    icons: [
        Image {
            url: "{__DIR__}ui/resources/umbrella.png"
        }
    ]
    scene: Scene {
        width: width
        height: height
        content: [
            main
        ]
    }
}