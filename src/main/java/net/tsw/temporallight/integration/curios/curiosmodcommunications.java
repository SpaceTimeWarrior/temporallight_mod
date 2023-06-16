package net.tsw.temporallight.integration.curios;

import net.minecraftforge.fml.InterModComms;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

public class curiosmodcommunications {
    public static boolean comm(){
        return InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> SlotTypePreset.NECKLACE.getMessageBuilder().build());
    }
}
