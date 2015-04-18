package com.basementcrew.ld32;

import bropals.lib.simplegame.AWTGameWindow;
import bropals.lib.simplegame.GameStateRunner;
import bropals.lib.simplegame.GameWindow;
import bropals.lib.simplegame.animation.Animation;
import bropals.lib.simplegame.io.AssetManager;
import com.basementcrew.ld32.data.Enemy;
import com.basementcrew.ld32.data.Weapon;
import com.basementcrew.ld32.states.MainMenuState;
import com.basementcrew.ld32.states.TimedGameStateRunner;
import java.io.File;

public class ApplicationMain {

	public static void main(String[] args) {
            AssetManager assetManager = new AssetManager(ApplicationMain.class, true);
            
            //Load all assets
            assetManager.loadImage("assets/img/mainMenu/playDown.png", "playDown");
            assetManager.loadImage("assets/img/mainMenu/playUp.png", "playUp");
            assetManager.loadImage("assets/img/mainMenu/playHover.png", "playHover");
            assetManager.loadImage("assets/img/mainMenu/quitDown.png", "quitDown");
            assetManager.loadImage("assets/img/mainMenu/quitUp.png", "quitUp");
            assetManager.loadImage("assets/img/mainMenu/quitHover.png", "quitHover");
            assetManager.loadImage("assets/img/mainMenu/titleBanner.png", "titleBanner");
            
            assetManager.loadImage("assets/img/townMenu/shopDown.png", "shopDown");
            assetManager.loadImage("assets/img/townMenu/shopHover.png", "shopHover");
            assetManager.loadImage("assets/img/townMenu/shopUp.png", "shopUp");
            assetManager.loadImage("assets/img/townMenu/toTownDown.png", "toTownDown");
            assetManager.loadImage("assets/img/townMenu/toTownHover.png", "toTownHover");
            assetManager.loadImage("assets/img/townMenu/toTownUp.png", "toTownUp");
            assetManager.loadImage("assets/img/townMenu/townBackground.png", "townBackground");
            assetManager.loadImage("assets/img/townMenu/townIcon.png", "townIcon");
            
            assetManager.loadImage("assets/img/battleSequence/cooldownBar.png", "cooldownBar");
            assetManager.loadImage("assets/img/battleSequence/cooldownBarBackground.png", "cooldownBarBackground");
            assetManager.loadImage("assets/img/battleSequence/healthBar.png", "healthBar");
            assetManager.loadImage("assets/img/battleSequence/healthBarBackground.png", "healthBarBackground");
            assetManager.loadImage("assets/img/battleSequence/selector.png", "selector");
            assetManager.loadImage("assets/img/battleSequence/lowerMenuBackground.png", "lowerMenuBackground");
            
            // enemy1 files
            assetManager.loadImage("assets/img/enemy1_idle", "img_enemy1_idle");
            assetManager.loadAsset("assets/data/enemy1/anim_idle.txt", "anim_enemy1_idle", Animation.class);
            assetManager.loadAsset("assets/data/enemy1/enemy.txt", "enemy_enemy1", Enemy.class);
            
            // player file
            assetManager.loadAsset("assets/data/weapon_fists.txt", "weapon_fists", Weapon.class);

            GameWindow window = new AWTGameWindow("Ludum Dare 32", 800, 600);
            TimedGameStateRunner runner = new TimedGameStateRunner(window, assetManager);
            runner.setState(new MainMenuState());
            runner.loop();
	}

}
