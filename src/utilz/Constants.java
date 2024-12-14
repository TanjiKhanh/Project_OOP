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
        public static class PauseButton {
            public static final int MUSIC_SIZE_DEFAULT = 31;
            public static final int MUSIC_SIZE = (int) (MUSIC_SIZE_DEFAULT * Game.SCALE);
        }
        public static class VolumeButtons {
			public static final int VOLUME_DEFAULT_WIDTH = 28;
			public static final int VOLUME_DEFAULT_HEIGHT = 44;
			public static final int SLIDER_DEFAULT_WIDTH = 215;

			public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
			public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
			public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);
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
        public static final int DEAD = 28;
        public static final int HIT = 5;
        public static final int ATTACK = 6;
        public static final int ATTACK_JUMP_1 = 7;
        public static final int ATTACK_JUMP_2 = 8;



        public static final int BIG_MARIO_WIDTH_DEFAULT = 18;
        public static final int BIG_MARIO_HEIGHT_DEFAULT = 34;
        public static final int SMALL_MARIO_WIDTH_DEFAULT = 16;
        public static final int SMALL_MARIO_HEIGHT_DEFAULT = 25;



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
                case DEAD:
                    return 0;
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
        public static final int MUSHROOM = 1;
        public static final int JOKER = 2;

        public static final int TURTLE_RUNNING = 13;
        public static final int TURTLE_DEAD = 17;

        public static final int MUSHROOM_RUNNING = 1;
        public static final int MUSHROOM_DEAD = 0;

        public static final int JOKER_IDLE = 0;

        public static final int TURTLE_WIDTH_DEFAULT = 20;
        public static final int TURTLE_HEIGHT_DEFAULT = 29;
        public static final int MUSHROOM_WIDTH_DEFAULT = 19;
        public static final int MUSHROOM_HEIGHT_DEFAULT = 19;
        public static final int JOKER_WIDTH_DEFAULT = 17;
        public static final int JOKER_HEIGHT_DEFAULT = 24;


        public static final int TURTLE_WIDTH = (int) (TURTLE_WIDTH_DEFAULT * Game.SCALE);
        public static final int TURTLE_HEIGHT = (int) (TURTLE_HEIGHT_DEFAULT * Game.SCALE);
        public static final int MUSHROOM_WIDTH = (int) (MUSHROOM_WIDTH_DEFAULT * Game.SCALE);
        public static final int MUSHROOM_HEIGHT = (int) (MUSHROOM_HEIGHT_DEFAULT * Game.SCALE);
        public static final int JOKER_WIDTH = (int) (JOKER_WIDTH_DEFAULT * Game.SCALE);
        public static final int JOKER_HEIGHT = (int) (JOKER_HEIGHT_DEFAULT * Game.SCALE);

        public static final int TURTLE_DRAWOFFSET_X = (int) (6 * Game.SCALE);
        public static final int TURTLE_DRAWOFFSET_Y = (int) (4 * Game.SCALE);
        public static final int MUSHROOM_DRAWOFFSET_X = (int) (6 * Game.SCALE);
        public static final int MUSHROOM_DRAWOFFSET_Y = (int) (4 * Game.SCALE);
        public static final int JOKER_DRAWOFFSET_X = (int) (6 * Game.SCALE);
        public static final int JOKER_DRAWOFFSET_Y = (int) (4 * Game.SCALE);

        public static int GetDeadAnimation(int enemy_type)
        {
            switch (enemy_type){
                case TURTLE:
                    return TURTLE_DEAD;
                case MUSHROOM:
                    return MUSHROOM_DEAD;

            }
            return 0;
        }
        public static int GetSpriteAmount(int enemy_type, int enemy_state) {
            switch (enemy_type) {
                case TURTLE:
                    switch (enemy_state) {
                        case TURTLE_RUNNING:
                            return 3;
                        case TURTLE_DEAD:
                            return 0;
                        default:
                            return 1;
                    }
                case MUSHROOM:
                    switch (enemy_state) {
                        case MUSHROOM_RUNNING:
                            return 2;
                        case MUSHROOM_DEAD:
                            return 0;
                    }
                case JOKER:
                    switch (enemy_state) {
                        case JOKER_IDLE:
                            return 2;
                    }
            }
            return 0;
        }




    }
    //Box constants
    public static class BoxConstannts
    {
        public static final int BOX = 3;
        public static final int BOX_SIDES_DEFAULT = 64;
    }

    public static class PineConstants
    {
        public static final int PINE = 4;
        public static final int PINE_SIDES_DEFAULT = 32;
        public static final int PINE_SIDES_WIDTH = ( int) (PINE_SIDES_DEFAULT * Game.SCALE * 1.5f);
        public static final int PINE_SIDES_HEIGHT =  ( int) (PINE_SIDES_DEFAULT * Game.SCALE * 2);
    }
}
