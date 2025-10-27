package net.tsw.Temporal_Light.entity.phoenixF;

import net.tsw.Temporal_Light.entity.kitsune.KitsuneVarients;

import java.util.Arrays;
import java.util.Comparator;

public enum phoenix_fVarients {
    nitwit(0),
    armorer(1),
    butcher(2),
    cartographer(3),
    cleric(4),
    farmer(5),
    fisherman(6),
    fletcher(7),
    leatherworker(8),
    librarian(9),
    mason(10),
    sheperd(11),
    toolsmith(12),
    weaponsmith(13),
    end_trader(14),

    other_trader(25);

    private static final phoenix_fVarients[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(phoenix_fVarients::getId)).toArray(phoenix_fVarients[]::new);
    private final int id;

    phoenix_fVarients(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public static phoenix_fVarients byID(int id){
        return BY_ID[id% BY_ID.length];
    }
}
