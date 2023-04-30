package net.tsw.temporallight.util;

import net.minecraftforge.common.ForgeConfigSpec;

public class configRegistry {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> harvestlv_floor;
   // public static final ForgeConfigSpec.ConfigValue<String> example_string;

    static {
        BUILDER.push("Config for Temporal Light Mod!");

        harvestlv_floor = BUILDER.comment("This is the minimum value for harvest level of the mod. Default value is 5.").define("harvestlv floor", 5);
       // example_string = BUILDER.comment("This is a string. Default value is \"Cy4\".").define("Example String", "Cy4");

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
