package nl.enjarai.skinthing.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraft.entity.EquipmentSlot;
import nl.enjarai.skinthing.SkinThing;

import java.util.HashMap;

@Config(name = SkinThing.MODID)
public class ModConfig implements ConfigData {
    @ConfigEntry.Gui.Excluded
    public static ModConfig INSTANCE;

    public static void init() {
        AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
        INSTANCE = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }

    @ConfigEntry.Gui.Tooltip
    public HashMap<EquipmentSlot, Boolean> shownArmor = new HashMap<>();

    @ConfigEntry.Gui.Tooltip
    public boolean showHelmet = true;

    @ConfigEntry.Gui.Tooltip
    public boolean showChestplate = true;

    @ConfigEntry.Gui.Tooltip
    public boolean showLeggings = true;

    @ConfigEntry.Gui.Tooltip
    public boolean showBoots = true;
}
