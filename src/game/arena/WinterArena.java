package game.arena;

import game.entities.IMobileEntity;
import game.enums.WeatherCondition;
import game.enums.SnowSurface;
import utilities.ValidationUtils;

public class WinterArena implements IArena {
    private final double length;
    private final SnowSurface surface;
    private final WeatherCondition condition;

    public WinterArena(double length, SnowSurface surface, WeatherCondition condition) {
        this.length = length;
        this.surface = surface;
        this.condition = condition;
    }

    @Override
    public double getFriction(){
        return surface.getFriction();
    }

    @Override
    public boolean isFinished(IMobileEntity mobileEntity) {
        ValidationUtils.assertNotNull(mobileEntity);
        return mobileEntity.getLocation().getX() >= length;
    }
}
