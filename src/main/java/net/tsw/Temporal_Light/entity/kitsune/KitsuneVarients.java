package net.tsw.Temporal_Light.entity.kitsune;

import java.util.Arrays;
import java.util.Comparator;

public enum KitsuneVarients {
    BROWN(0),
    ORANGE(1),
    DK_GREY(2),
    YELLOW(3),
    WHITE(4),
    BROWN2(5),
    ORANGE2(6),
    DK_GREY2(7);

    private static final KitsuneVarients[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(KitsuneVarients::getId)).toArray(KitsuneVarients[]::new);
    private final int id;

    KitsuneVarients(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public static KitsuneVarients byID(int id){
        return BY_ID[id% BY_ID.length];
    }
}
