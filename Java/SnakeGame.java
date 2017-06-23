/*
Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.

The snake is initially positioned at the top left corner (0,0) with length = 1 unit.

You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.

Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.

When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

Example:
Given width = 3, height = 2, and food = [[1,2],[0,1]].

Snake snake = new Snake(width, height, food);

Initially the snake appears at position (0,0) and the food at (1,2).

|S| | |
| | |F|

snake.move("R"); -> Returns 0

| |S| |
| | |F|

snake.move("D"); -> Returns 0

| | | |
| |S|F|

snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

| |F| |
| |S|S|

snake.move("U"); -> Returns 1

| |F|S|
| | |S|

snake.move("L"); -> Returns 2 (Snake eats the second food)

| |S|S|
| | |S|

snake.move("U"); -> Returns -1 (Game over because snake collides with border)
*/

//My solution and thoughts:
//https://discuss.leetcode.com/topic/93572/o-1-move-solution-using-linkedhashset-and-follow-ups
public class SnakeGame {
    private static class Pos {
        final Integer row;
        final Integer col;
        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o instanceof Pos) {
                Pos other = (Pos) o;
                return this.row == other.row && this.col == other.col;
            }
            return false;
        }
        
        @Override
        public int hashCode() {
            return row.hashCode() * 31 + col.hashCode();
        }
    }
    private final Map<String, Pos> directionMap = new HashMap<>();
    private final int rows;
    private final int cols;
    
    private final Queue<Pos> foodList = new ArrayDeque<>();
    private final Set<Pos> snake = new LinkedHashSet<>();
    private Pos snakeHead;
    private int score = 0;
    
    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        directionMap.put("U", new Pos(-1, 0));
        directionMap.put("D", new Pos(1, 0));
        directionMap.put("L", new Pos(0, -1));
        directionMap.put("R", new Pos(0, 1));
        rows = height;
        cols = width;
        for (int[] foodPos : food) {
            foodList.offer(new Pos(foodPos[0], foodPos[1]));
        }
        snakeHead = new Pos(0, 0);
        snake.add(snakeHead);
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    //O(1) time
    public int move(String direction) {
        Pos shift = directionMap.get(direction);
        if (shift == null) {
            return -1;
        }
        Pos nextHead = new Pos(snakeHead.row + shift.row, snakeHead.col + shift.col);
        if (nextHead.row < 0 || nextHead.row >= rows 
         || nextHead.col < 0 || nextHead.col >= cols) {
             return -1;
        }
        Iterator<Pos> iter = snake.iterator();
        Pos snakeTail = iter.next();
        if (!nextHead.equals(snakeTail) && snake.contains(nextHead)) {
            return -1;
        }
        if (!foodList.isEmpty() && foodList.peek().equals(nextHead)) {
            score++;
            foodList.poll();
        } else {
            iter.remove();
        }
        snake.add(nextHead);
        snakeHead = nextHead;
        return score;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */