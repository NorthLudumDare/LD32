/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basementcrew.ld32.data.loaders;

import bropals.lib.simplegame.animation.Track;
import bropals.lib.simplegame.io.AssetLoader;
import bropals.lib.simplegame.io.AssetManager;
import bropals.lib.simplegame.logger.ErrorLogger;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Loads animation files.
 * 
 * @author Jonathon
 */
public class AnimationLoader extends AssetLoader {

    private AssetManager assetManager;
    
    public AnimationLoader(AssetManager assetManager) {
        this.assetManager = assetManager;
    }
    
    @Override
    public void loadAsset(String key, InputStream in) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder srcBuilder = new  StringBuilder(100); //Source without whitespace
            int num;
            while ((num = reader.read()) != -1) {
                if (!Character.isWhitespace((char)num)) {
                    srcBuilder.append((char)num);
                }
            }
            reader.close();
            char[] src = srcBuilder.toString().toCharArray();
            
            String buffer = "";
            BufferedImage image = null;
            int width = -1;
            int height = -1;
            
            for (int i=0; i<src.length; i++) {
                if (src[i] == ';') {
                    if (image == null) {
                        image = assetManager.getImage(key);
                    } else if (width == -1) {
                        width = Integer.parseInt(buffer);
                    } else if (height == -1) {
                        height = Integer.parseInt(buffer);
                    }
                    buffer = "";
                } else {
                    buffer += src[i];
                }
            }
        } catch (IOException e) {
            ErrorLogger.println("Unable to load area file with key " + key + ": " + e);
        }
    }
}