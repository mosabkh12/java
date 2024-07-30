package game.competition;

import game.arena.IArena;

public class SkiCompetition extends Competition {
    public SkiCompetition(IArena arena, int maxCompetitors) {
        super(arena, maxCompetitors);
    }

    @Override
    protected boolean isValidCompetitor(Competitor competitor) {
        // Add logic to validate if a competitor is valid for a ski competition
        return true; // For simplicity, we assume all competitors are valid
    }
}
