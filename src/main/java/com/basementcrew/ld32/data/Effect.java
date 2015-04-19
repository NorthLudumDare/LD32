/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basementcrew.ld32.data;

/**
 *
 * @author Jonathon
 */
public enum Effect {
    DOUBLE_DAMAGE, NONE;
    
    public void doEffect(Weapon with, Enemy target, PlayerData playerData) {
        switch (this) {
            case DOUBLE_DAMAGE:
                System.out.println("Double damage!");
                target.damage(with.getAttackDamage());
                break;
        }
    }
}
