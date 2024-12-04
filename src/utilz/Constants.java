package utilz;

public class Constants {
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


}
