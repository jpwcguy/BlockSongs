package net.ethannetwork.blocksongs.util;

import net.minecraft.util.math.random.Random;
import net.minecraft.util.math.random.RandomSplitter;

public class AlwaysZeroRandom implements Random {
    @Override
    public float nextFloat() {
        return 0.5f; // i use this to manipulate minecrafts random checker for eyes of ender, the thing is (rnd-rnd)*0.2
        // thats why i make rnd-rnd always 0.5 so it comes out as 0 so i can make sure its the same pitch every time
    }

    @Override
    public int nextInt() {
        return 0;
    }

    @Override
    public int nextInt(int bound) {
        return 0;
    }

    @Override
    public long nextLong() {
        return 0L;
    }

    @Override
    public double nextDouble() {
        return 0.5;
    }

    @Override
    public double nextGaussian() {
        return 0;
    }

    @Override
    public boolean nextBoolean() {
        return false;
    }

    @Override
    public Random split() {
        return this;
    }

    @Override
    public RandomSplitter nextSplitter() {
        return null;
    }

    @Override
    public void setSeed(long seed) {

    }
}
