package com.basementcrew.ld32;

import javax.sound.midi.Sequence;

import bropals.lib.simplegame.AWTGameWindow;
import bropals.lib.simplegame.GameWindow;
import bropals.lib.simplegame.QuitHandler;
import bropals.lib.simplegame.animation.Animation;
import bropals.lib.simplegame.io.AssetManager;
import bropals.lib.simplegame.logger.ErrorLogger;
import bropals.lib.simplegame.logger.InfoLogger;

import com.basementcrew.ld32.data.Area;
import com.basementcrew.ld32.data.Enemy;
import com.basementcrew.ld32.data.PlayerData;
import com.basementcrew.ld32.data.Weapon;
import com.basementcrew.ld32.data.loaders.AnimationLoader;
import com.basementcrew.ld32.data.loaders.AreaLoader;
import com.basementcrew.ld32.data.loaders.EnemyLoader;
import com.basementcrew.ld32.data.loaders.MovieLoader;
import com.basementcrew.ld32.data.loaders.PlayerDataLoader;
import com.basementcrew.ld32.data.loaders.SequenceLoader;
import com.basementcrew.ld32.data.loaders.WeaponLoader;
import com.basementcrew.ld32.movie.Movie;
import com.basementcrew.ld32.states.MainMenuState;
import com.basementcrew.ld32.states.TimedGameStateRunner;
import com.basementcrew.ld32.states.TransitionState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class ApplicationMain {

    public static Map<String, Object> flags = new HashMap<String, Object>();

    public static void main(String[] args) {
        InfoLogger.setSilent(true);
        AssetManager assetManager = new AssetManager(ApplicationMain.class, true);

        //Splash screen
        SplashScreen splash = SplashScreen.getSplashScreen();
        if (splash == null) {
            ErrorLogger.println("No splash screen");
        }
        Graphics2D g = null;
        if (splash != null) {
            assetManager.loadImage("assets/img/splash.png", "splash");
            g = splash.createGraphics();
            if (g != null) {
                g.drawImage(assetManager.getImage("splash"), 0, 0, (int) splash.getSize().getWidth(), (int) splash.getSize().getHeight(), null);
                g.setColor(Color.BLUE);
                splash.update();
            }
        }

        //Register the loaders
        assetManager.addAssetLoader(new AnimationLoader(assetManager), Animation.class);
        assetManager.addAssetLoader(new AreaLoader(assetManager), Area.class);
        assetManager.addAssetLoader(new EnemyLoader(assetManager), Enemy.class);
        assetManager.addAssetLoader(new MovieLoader(assetManager), Movie.class);
        assetManager.addAssetLoader(new PlayerDataLoader(assetManager), PlayerData.class);
        assetManager.addAssetLoader(new WeaponLoader(assetManager), Weapon.class);
        assetManager.addAssetLoader(new SequenceLoader(), Sequence.class);

        //Load all assets
        assetManager.loadImage("assets/img/mainMenu/playDown.png", "playDown");
        assetManager.loadImage("assets/img/mainMenu/playUp.png", "playUp");
        assetManager.loadImage("assets/img/mainMenu/playHover.png", "playHover");
        assetManager.loadImage("assets/img/mainMenu/quitDown.png", "quitDown");
        assetManager.loadImage("assets/img/mainMenu/quitUp.png", "quitUp");
        assetManager.loadImage("assets/img/mainMenu/quitHover.png", "quitHover");
        assetManager.loadImage("assets/img/mainMenu/helpDown.png", "helpDown");
        assetManager.loadImage("assets/img/mainMenu/helpUp.png", "helpUp");
        assetManager.loadImage("assets/img/mainMenu/helpHover.png", "helpHover");
        assetManager.loadImage("assets/img/mainMenu/titleBanner.png", "titleBanner");
        assetManager.loadImage("assets/img/mainMenu/mainMenuBackground.png", "mainMenuBackground");
        assetManager.loadImage("assets/img/mainMenu/help.png", "help");

        if (splash != null && g != null) {
            g.fillRect(26, 189, 25, 28); //Max size is 350
            splash.update();
        }

        assetManager.loadImage("assets/img/townMenu/townBackground.png", "townBackground");
        assetManager.loadImage("assets/img/townMenu/swamp_icon.png", "swamp_icon");
        assetManager.loadImage("assets/img/townMenu/fire_icon.png", "fire_icon");
        assetManager.loadImage("assets/img/townMenu/ice_icon.png", "ice_icon");
        assetManager.loadImage("assets/img/townMenu/savanna_icon.png", "savanna_icon");
        assetManager.loadImage("assets/img/townMenu/completed_icon.png", "completed_icon");

        if (splash != null && g != null) {
            g.fillRect(26, 189, 70, 28); //Max size is 350
            splash.update();
        }

        assetManager.loadImage("assets/img/icon.png", "windowIcon");

        assetManager.loadImage("assets/img/battleSequence/healthBar.png", "healthBar");
        assetManager.loadImage("assets/img/battleSequence/healthBarBackground.png", "healthBarBackground");
        assetManager.loadImage("assets/img/battleSequence/lowerMenuBackground.png", "lowerMenuBackground");
        assetManager.loadImage("assets/img/battleSequence/swamp_background.png", "swamp_background");
        assetManager.loadImage("assets/img/battleSequence/ice_background.png", "ice_background");
        assetManager.loadImage("assets/img/battleSequence/fire_background.png", "fire_background");
        assetManager.loadImage("assets/img/battleSequence/savanna_background.png", "savanna_background");
        assetManager.loadImage("assets/img/battleSequence/abilityButtonFists.png", "fist_button");
        assetManager.loadImage("assets/img/battleSequence/abilityButtonFistsHover.png", "hover_fist_button");
        assetManager.loadImage("assets/img/battleSequence/abilityButtonGoblin.png", "goblin_shotput_button");
        assetManager.loadImage("assets/img/battleSequence/abilityButtonGoblinHover.png", "hover_goblin_shotput_button");
        assetManager.loadImage("assets/img/battleSequence/abilityButtonIce.png", "ice_button");
        assetManager.loadImage("assets/img/battleSequence/abilityButtonIceHover.png", "hover_ice_button");
        assetManager.loadImage("assets/img/battleSequence/abilityButtonHorn.png", "horn_button");
        assetManager.loadImage("assets/img/battleSequence/abilityButtonHornHover.png", "hover_horn_button");
        assetManager.loadImage("assets/img/battleSequence/abilityButtonWings.png", "wings_button");
        assetManager.loadImage("assets/img/battleSequence/abilityButtonWingsHover.png", "hover_wings_button");
        
        if (splash != null && g != null) {
            g.fillRect(26, 189, 120, 28); //Max size is 350
            splash.update();
        }

        assetManager.loadImage("assets/img/entity/goblin_idle.png", "goblin_idle");
        assetManager.loadImage("assets/img/entity/goblin_attack.png", "goblin_attack");
        assetManager.loadImage("assets/img/entity/yeti_idle.png", "yeti_idle");
        assetManager.loadImage("assets/img/entity/yeti_attack.png", "yeti_attack");
        assetManager.loadImage("assets/img/entity/warthog_idle.png", "warthog_idle");
        assetManager.loadImage("assets/img/entity/warthog_attack.png", "warthog_attack");
        assetManager.loadImage("assets/img/entity/imp_idle.png", "imp_idle");
        assetManager.loadImage("assets/img/entity/imp_attack.png", "imp_attack");
        assetManager.loadImage("assets/img/entity/iceMonster_idle.png", "iceMonster_idle");
        assetManager.loadImage("assets/img/entity/iceMonster_attack.png", "iceMonster_attack");
        assetManager.loadImage("assets/img/entity/slime_idle.png", "slime_idle");
        assetManager.loadImage("assets/img/entity/slime_attack.png", "slime_attack");
        assetManager.loadImage("assets/img/entity/player_idle.png", "player_idle");
        assetManager.loadImage("assets/img/entity/player_attack.png", "player_attack");
        assetManager.loadImage("assets/img/entity/player_attack_wing.png", "player_attack_wing");
        assetManager.loadImage("assets/img/entity/player_attack_horn.png", "player_attack_horn");
        assetManager.loadImage("assets/img/entity/goblin_shotput.png", "goblin_shotput");
        assetManager.loadImage("assets/img/particle/imp_projectile.png", "imp_projectile");
        assetManager.loadImage("assets/img/particle/spear.png", "spear_projectile");
        assetManager.loadImage("assets/img/particle/ice_projectile.png", "ice_projectile");
   
        if (splash != null && g != null) {
            g.fillRect(26, 189, 160, 28); //Max size is 350
            splash.update();
        }

        assetManager.loadImage("assets/img/movies/introMovieBackground1.png", "introMovieBackground1");
        assetManager.loadImage("assets/img/movies/introMovieBackground2.png", "introMovieBackground2");
        assetManager.loadImage("assets/img/movies/introMountains.png", "introMountains");
        assetManager.loadImage("assets/img/movies/dieSonne.png", "dieSonne");
        assetManager.loadImage("assets/img/movies/text1.png", "text1");
        assetManager.loadImage("assets/img/movies/text2.png", "text2");
        assetManager.loadImage("assets/img/movies/text3.png", "text3");
        assetManager.loadImage("assets/img/movies/burningTown.png", "burningTown");
        assetManager.loadImage("assets/img/movies/mountainsStatic.png", "mountainsStatic");
        assetManager.loadImage("assets/img/movies/darkFace.png", "darkFace");
        assetManager.loadImage("assets/img/movies/verses.png", "verses");
        assetManager.loadImage("assets/img/movies/winner.png", "winner");
        assetManager.loadImage("assets/img/movies/wonGame.png", "win_game");
        
        // parcticles
        assetManager.loadImage("assets/img/particle/dodge.png", "dodge_particle");
        assetManager.loadImage("assets/img/particle/double_damage.png", "double_damage_particle");
        assetManager.loadImage("assets/img/particle/goblin_stench.png", "goblin_stench_particle");
        assetManager.loadImage("assets/img/particle/frostbite.png", "frostbite_particle");
        
        if (splash != null && g != null) {
            g.fillRect(26, 189, 180, 28); //Max size is 350
            splash.update();
        }

        //Load animations
        assetManager.loadAsset("assets/data/animation/slime.animation", "slime", Animation.class);
        assetManager.loadAsset("assets/data/animation/iceMonster.animation", "iceMonster", Animation.class);
        assetManager.loadAsset("assets/data/animation/goblin.animation", "goblin", Animation.class);
        assetManager.loadAsset("assets/data/animation/yeti.animation", "yeti", Animation.class);
        assetManager.loadAsset("assets/data/animation/warthog.animation", "warthog", Animation.class);
        assetManager.loadAsset("assets/data/animation/imp.animation", "imp", Animation.class);
        assetManager.loadAsset("assets/data/animation/player.animation", "player", Animation.class);
        assetManager.loadAsset("assets/data/animation/introMountains.animation", "introMountains", Animation.class);
        assetManager.loadAsset("assets/data/animation/burningBuildings.animation", "burningBuildings", Animation.class);
        assetManager.loadAsset("assets/data/animation/verses.animation", "verses", Animation.class);
        assetManager.loadAsset("assets/data/animation/winner.animation", "winner", Animation.class);
        assetManager.loadAsset("assets/data/animation/win_game.animation", "win_game", Animation.class);

        if (splash != null && g != null) {
            g.fillRect(26, 189, 200, 28); //Max size is 350
            splash.update();
        }
        
        //Load sound effects
        assetManager.loadSoundEffect("assets/sound/spawn.wav", "spawn");
        assetManager.loadSoundEffect("assets/sound/imp_attack.wav", "imp_attack");
        assetManager.loadSoundEffect("assets/sound/yeti_attack.wav", "yeti_attack");
        assetManager.loadSoundEffect("assets/sound/warthog_attack.wav", "warthog_attack");
        assetManager.loadSoundEffect("assets/sound/slime_attack.wav", "slime_attack");
        assetManager.loadSoundEffect("assets/sound/battle.wav", "battle");
        assetManager.loadSoundEffect("assets/sound/the_land_of_wornia.wav", "intro1");
        assetManager.loadSoundEffect("assets/sound/overrun_by_wild_beast.wav", "intro2");
        assetManager.loadSoundEffect("assets/sound/its_up_to_you_to_go_out.wav", "intro3");
        assetManager.loadSoundEffect("assets/sound/and_save_wornia.wav", "intro4");
        assetManager.loadSoundEffect("assets/sound/winner.wav", "winner");
        assetManager.loadSoundEffect("assets/sound/verses.wav", "verses");
        assetManager.loadSoundEffect("assets/sound/help.wav", "help");
        assetManager.loadSoundEffect("assets/sound/punch.wav", "fist_attack");
        assetManager.loadSoundEffect("assets/sound/stab.wav", "horn_attack");
        assetManager.loadSoundEffect("assets/sound/wing_slap.wav", "wing_sound");
        assetManager.loadSoundEffect("assets/sound/ice_smash.wav", "ice_sound");
    
        
        if (splash != null && g != null) {
            g.fillRect(26, 189, 220, 28); //Max size is 350
            splash.update();
        }

        //Load enemies
        assetManager.loadAsset("assets/data/enemy/slime.enemy", "slime", Enemy.class);
        assetManager.loadAsset("assets/data/enemy/goblin.enemy", "goblin", Enemy.class);
        assetManager.loadAsset("assets/data/enemy/yeti.enemy", "yeti", Enemy.class);
        assetManager.loadAsset("assets/data/enemy/warthog.enemy", "warthog", Enemy.class);
        assetManager.loadAsset("assets/data/enemy/imp.enemy", "imp", Enemy.class);
        assetManager.loadAsset("assets/data/enemy/iceMonster.enemy", "iceMonster", Enemy.class);

        if (splash != null && g != null) {
            g.fillRect(26, 189, 240, 28); //Max size is 350
            splash.update();
        }
        
        //Load weapons
        assetManager.loadAsset("assets/data/weapon/fist.weapon", "fist", Weapon.class);
        assetManager.loadAsset("assets/data/weapon/goblinshotput.weapon", "weapon_goblin", Weapon.class);
        assetManager.loadAsset("assets/data/weapon/iceprojectile.weapon", "weapon_ice", Weapon.class);
        assetManager.loadAsset("assets/data/weapon/horn.weapon", "weapon_horn", Weapon.class);
        assetManager.loadAsset("assets/data/weapon/wing.weapon", "weapon_wing", Weapon.class);

        if (splash != null && g != null) {
            g.fillRect(26, 189, 300, 28); //Max size is 350
            splash.update();
        }
        
        //Load areas
        assetManager.loadAsset("assets/data/area/fire.area", "fire", Area.class);
        assetManager.loadAsset("assets/data/area/ice.area", "ice", Area.class);
        assetManager.loadAsset("assets/data/area/savanna.area", "savanna", Area.class);
        assetManager.loadAsset("assets/data/area/swamp.area", "swamp", Area.class);

        if (splash != null && g != null) {
            g.fillRect(26, 189, 280, 28); //Max size is 350
            splash.update();
        }

        //Load player data
        assetManager.loadAsset("assets/data/playerdata/default.playerdata", "default_playerdata", PlayerData.class);

        if (splash != null && g != null) {
            g.fillRect(26, 189, 310, 28); //Max size is 350
            splash.update();
        }

        //Load Movies
        assetManager.loadAsset("assets/data/movie/intro.movie", "intro", Movie.class);
        assetManager.loadAsset("assets/data/movie/enter_battle_goblin.movie", "enter_battle_goblin", Movie.class);
        assetManager.loadAsset("assets/data/movie/enter_battle_slime.movie", "enter_battle_slime", Movie.class);
        assetManager.loadAsset("assets/data/movie/enter_battle_iceMonster.movie", "enter_battle_iceMonster", Movie.class);
        assetManager.loadAsset("assets/data/movie/enter_battle_imp.movie", "enter_battle_imp", Movie.class);
        assetManager.loadAsset("assets/data/movie/enter_battle_yeti.movie", "enter_battle_yeti", Movie.class);
        assetManager.loadAsset("assets/data/movie/enter_battle_warthog.movie", "enter_battle_warthog", Movie.class);
        assetManager.loadAsset("assets/data/movie/win_battle.movie", "win_battle", Movie.class);
        assetManager.loadAsset("assets/data/movie/lose_battle.movie", "lose_battle", Movie.class);
        assetManager.loadAsset("assets/data/movie/win_game.movie", "win_game", Movie.class);

        if (splash != null && g != null) {
            g.fillRect(26, 189, 330, 28); //Max size is 350
            splash.update();
        }

        // Load Music
        assetManager.loadAsset("assets/music/song_20150419_054852_239.mid", "main_song", Sequence.class);
        
        //Process the sequence to make it quieter
        Sequence seq = assetManager.getAsset("main_song", Sequence.class);
        Track[] tracks = seq.getTracks();
        try {
            for (int t = 0; t < tracks.length; t++) {
                tracks[t].add(new MidiEvent(new ShortMessage(ShortMessage.CONTROL_CHANGE, t, 0, 30), 0));

            }
        } catch (InvalidMidiDataException ex) {
            ErrorLogger.println("Invalid midi data exception: " + ex);
        }

        if (splash != null && g != null) {
            g.fillRect(26, 189, 350, 28); //Max size is 350
            splash.update();

            //Done loading
            g.dispose();
            splash.close();
        }

        GameWindow window = new AWTGameWindow("Battle!", 800, 600);

        window.setIcon(assetManager.getImage("windowIcon"));
        window.registerQuitHandler(new QuitHandler() {
            @Override
            public void onQuit() {
                // Otherwise game doesn't quit
                InfoLogger.println("This");
                MusicPlayer.getInstance().shutdown();
            }
        });

        TimedGameStateRunner runner = new TimedGameStateRunner(window, assetManager);

        /*
         runner.setState(new MainMenuState());
         */
        // Skip constant caller arg TODO: Replace with public domain flag parsing code
        for (int i = 0; i < args.length; i++) {
            String param = args[i];
            param = param.trim();
            if (param.charAt(0) == '-') {
                String key = param.substring(1); // -key
                if (key.indexOf('=') > 0) {						// -key=value 
                    key = key.substring(0, key.indexOf('='));	// TODO: if value is a "string" that might break this type
                    String value = key.substring(key.indexOf('=') + 1);
                    flags.put(key, value);
                } else if ((i + 1 < args.length) && args[i + 1].charAt(0) != '-') {	// -key value
                    String value = args[i + 1];
                    i++; // Skip next arg since we already handle it as a value
                    flags.put(key, value);
                } else {									// -key (flag)
                    flags.put(key, true);
                }
            }
        }

        runner.setState(new TransitionState(assetManager.getAsset("intro", Movie.class), new MainMenuState()));
        try {
            runner.loop();
        } catch (Exception e) {
            System.out.println("THERE WAS AN EXCEPTION \n CLOSING WINDOW");
            e.printStackTrace();
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(new File("error.txt"));
            } catch (FileNotFoundException ex) {
                ErrorLogger.println("Could not make error.txt (what now!?)");
            }
            if (writer != null) {
                e.printStackTrace(writer);
                writer.flush();
                writer.close();
            }
        }
        window.destroy();

    }

}
