package nl.enjarai.simplepipes.mixin;

import net.minecraft.block.HopperBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(HopperBlock.class)
public class TestMixin {
    @ModifyArg(
            method = "getPlacementState",
            at = @At(
                    value = "INVOKE",
                    ordinal = 1,
                    target = "Lnet/minecraft/block/BlockState;with(Lnet/minecraft/state/property/Property;Ljava/lang/Comparable;)Ljava/lang/Object;"
            ),
            index = 1
    )
    private Comparable lockHopper(Comparable par2) {

        return false;
    }
}
