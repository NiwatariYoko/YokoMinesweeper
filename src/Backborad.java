import java.util.*;
import java.lang.*;

public class Backborad {
    public static int borad_Figure;
    private static int height;
    private static int weight;
    private static int input_height;
    private static int input_weight;
    private static int[][] back_board;
    private static HashMap<Integer,Integer> booms_heights;
    private static HashMap<Integer,Integer> booms_weights;
    private static boolean set_backboard;

    public Backborad(int height, int weight) {
        Backborad.height = height;
        Backborad.weight = weight;
        Backborad.borad_Figure = 0;
        Backborad.back_board = new int [height][weight];
        Backborad.booms_heights = new HashMap<>();
        Backborad.booms_weights = new HashMap<>();
        Backborad.set_backboard = true;
    }

    public void setBackborad(int boom) {
        Random random = new Random();
        for (int i = 0;i < boom;i ++) {
            while (true) {
                int rand_height = random.nextInt(height);
                int rand_weight = random.nextInt(weight);
                if (rand_height == input_height&&rand_weight == input_weight){
                    continue;
                }
                if (existed(rand_height, rand_weight)||(rand_height == 0&&rand_weight == 0)) {
                    back_board[rand_height][rand_weight] = 9;
                    booms_heights.put(i, rand_height);
                    booms_weights.put(i, rand_weight);
                    break;
                }
            }
        }
        for (int i = 0;i < Surfaceboard.boom;i ++) {
            for (int j = booms_heights.get(i) - 1; j <= booms_heights.get(i) + 1;j ++) {
                for (int k = booms_weights.get(i) - 1; k <= booms_weights.get(i) + 1; k++) {
                    try {
                        if (back_board[j][k] != 9) {
                            back_board[j][k] ++;
                        }
                    } catch (Exception e) {

                    }
                }
            }
        }
    }

    private static boolean existed(int rand_height, int rand_weight) {
        for (int acc = 0;acc < booms_heights.size();acc ++) {
            if (booms_heights.get(acc) == rand_height&&booms_weights.get(acc) == rand_weight) {
                return false;
            }
        }
        return true;
    }

    public static void getFigure(int height, int weight, String[][] array) {
        input_height = height;
        input_weight = weight;
        if (set_backboard) {
            Surfaceboard.backborad.setBackborad(Surfaceboard.boom);
            set_backboard = false;
        }
        int sum = back_board[height][weight];
        if (sum == 9) {
            borad_Figure = 9;
            System.out.println("踩到雷了！输入play重新开始游戏");
            booms_heights.clear();
            booms_weights.clear();
        } else {
            array[height][weight] = String.valueOf(sum);
            if (sum == 0) {
                setSurfaceBoardFigure(height, weight, array);
            }
        }
    }

    private static void setSurfaceBoardFigure(int height, int weight, String[][] array) {
        for (int i = height - 1; i <= height + 1;i ++) {
            for (int j = weight - 1; j <= weight + 1;j ++) {
                try {
                    if (!array[i][j].equals(String.valueOf(back_board[i][j]))) {
                        array[i][j] = String.valueOf(back_board[i][j]);
                        if (back_board[i][j] == 0) {
                            setSurfaceBoardFigure(i, j, array);
                        }
                    }
                } catch(Exception e){

                }
            }
        }
    }
}
