/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basementcrew.ld32;

import java.awt.image.BufferedImage;

/**
 *
 * @author Kevin
 */
public class Particle {
    
    private int timeShown, timeAliveSoFar;
    private BufferedImage image;
    
    /**
     * A simple particle to show an image for a certain amount of time.
     * @param image
     * @param timeShown 
     */
    public Particle(BufferedImage image, int timeShown) {
        this.image=image;
        this.timeShown=timeShown;
        this.timeAliveSoFar = 0;
    }
    
    public void update(long dt) {
        this.timeAliveSoFar += dt;
    }
    
    public boolean isAlive() {
        return timeAliveSoFar < timeShown;
    }
}
