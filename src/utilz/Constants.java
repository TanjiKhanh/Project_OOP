package utilz;

import main.Game;

public class Constants {
    public static class UI {
		public static class Buttons {
			public static final int B_WIDTH_DEFAULT = 152;
			public static final int B_HEIGHT_DEFAULT = 67;
			public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
			public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
		}
	}
    public static class Direction
    {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    public static class PlayerConstants{
        public static final int IDLE = 14;
        public static final int RUNNING = 16;
        public static final int JUMP = 20;
        public static final int FALLING = 21;
        public static final int GROUND = 4;
        public static final int HIT = 5;
        public static final int ATTACK = 6;
        public static final int ATTACK_JUMP_1 = 7;
        public static final int ATTACK_JUMP_2 = 8;

        public static int GetSpriteAmount( int player_action)
        {
            switch (player_action)
            {
                case IDLE:
                    return 0;
                case RUNNING:
                    return 2;
                case JUMP:
                    return 0;
                case FALLING:
                    return 0;
                case GROUND:
                    return 4;
                case HIT:
                    return 4;
                case ATTACK:
                case ATTACK_JUMP_1:
                case ATTACK_JUMP_2:
                    return 3;
                default:
                    return 1;
            }
        }
    }
    // enemyConstants
    public static class EnemyConstants {
        public static final int TURTLE = 0;

        public static final int IDLE = 14;
        public static final int RUNNING  = 16;
        public static final int ATTACK = 18;
        public static final int HIT = 17;
        public static final int DEAD = 19;

        public static final int TURTLE_WIDTH_DEFAULT = 72;
        public static final int TURTLE_HEIGHT_DEFAULT = 32;

        public static final int TURTLE_WIDTH = (int) (TURTLE_WIDTH_DEFAULT * Game.SCALE);
        public static final int TURTLE_HEIGHT = (int) (TURTLE_HEIGHT_DEFAULT * Game.SCALE);

        public static final int TURTLE_DRAWOFFSET_X = (int) (6 * Game.SCALE);
        public static final int TURTLE_DRAWOFFSET_Y = (int) (4 * Game.SCALE);

        public static int GetSpriteAmount(int enemy_type, int enemy_state) {
            switch (enemy_type) {
                case TURTLE:
                    switch (enemy_state) {
                        case IDLE:
                            return 9;
                        case RUNNING:
                            return 6;
                        case ATTACK:
                            return 7;
                        case HIT:
                            return 4;
                        case DEAD:
                            return 5;
                        default:
                            return 1;
                    }
            }
            return 0;
        }
    }
}
