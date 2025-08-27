package net.ethannetwork.blocksongs.mixin;

import net.ethannetwork.blocksongs.util.AlwaysZeroRandom;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {

    @Unique
    private static final int[] AMONG_US_TUNE = {-4, -4, -3, -2, -1, -2, -3, -4, -5, -3, -4}; // i was up till 3 am perfecting this
    @Unique
    private static int tuneStep = 0;
    @Final
    @Shadow
    private MinecraftClient client = MinecraftClient.getInstance();

    @Inject(method = "playSound(DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FFZJ)V",
            at = @At("HEAD"), cancellable = true)
    private void onSoundPlayed(
            double x, double y, double z,
            SoundEvent sound,
            SoundCategory category,
            float volume, float pitch,
            boolean useDistance,
            long seed,
            CallbackInfo ci
    ) {
        if (sound == SoundEvents.BLOCK_END_PORTAL_FRAME_FILL) {
            ci.cancel();
            int offset = AMONG_US_TUNE[tuneStep];
            float exactPitch = 1.0f + (offset * 0.1f);
            client.getSoundManager().play(
                    new PositionedSoundInstance(sound, category, volume, exactPitch,
                            new AlwaysZeroRandom(), x, y, z)
            );
            tuneStep = (tuneStep + 1) % AMONG_US_TUNE.length;
        }
    }
}
