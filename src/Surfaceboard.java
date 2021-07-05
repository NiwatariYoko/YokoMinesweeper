import java.util.*;

public class Surfaceboard {
    public static int boom;
    public static Backborad backborad;

    public static void main(String[] args) {
        System.out.println("YZMinesweeper v0.1alpha");
        System.out.println("Copyright(C)2021 By Niwatari Yoko.保留所有权利");
        Scanner in = new Scanner(System.in);
        try {
            while (true) {
                System.out.print("请输入雷区的高度：");
                int height = in.nextInt();
                System.out.print("请输入雷区的宽度：");
                int weight = in.nextInt();
                String[][] surface_board = chekerBoard(height, weight);
                backborad = new Backborad(height, weight);
                while (true) {
                    System.out.print("请输入地雷的数量：");
                    boom = in.nextInt();
                    in.nextLine();
                    printBoard(surface_board);
                    while (Backborad.borad_Figure != 9 && boom < height * weight || boom == 0) {
                        command(in.nextLine(), surface_board);
                    }
                    if (Backborad.borad_Figure == 9) {
                        break;
                    }
                    System.out.println("您输入的地雷数量貌似有点不对劲呢~");
                }
            }
        } catch (Exception e) {
            System.out.println("您输入的数据有点不对劲呢，让我们再来一次");
        }

    }

    private static String[][] chekerBoard(int height, int weight) {
        String[][] cheker_board = new String[height][weight];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < weight; j++) {
                cheker_board[i][j] = "*";
            }
        }
        return cheker_board;
    }

    private static void command(String command,String[][] array) {
        String[] words = command.split(" ");
        try {
            int input_height = -1;
            int input_weight = -1;
            if (words.length == 3) {
                input_height = Integer.parseInt(words[1]) - 1;
                input_weight = Integer.parseInt(words[2]) - 1;
            }
            switch (words[0]) {
                case "dt":
                    Backborad.getFigure(input_height, input_weight, array);
                    printBoard(array);
                    break;
                case "mk":
                    array[input_height][input_weight] = "#";
                    printBoard(array);
                    break;
                case "play" :
                    Backborad.borad_Figure = 9;
                    break;
                case "help" :
                    System.out.println("dt [横坐标] [纵坐标] 获取指定坐标上的数字");
                    System.out.println("mk [横坐标] [纵坐标] 标记指定坐标上的地雷");
                    System.out.println("play 开始/重新开始游戏");
                    System.out.println("help 获取指令帮助");
                    break;
                default:
                    System.out.println("未知指令，请输入“help”查看指令列表");
            }
        }catch (Exception e) {
            System.out.println("未知指令，请输入“help”查看指令列表");
        }
    }

    private static void printBoard(String[][] array){
        for (String[] sarry : array) {
            for (String s : sarry) {
                System.out.print(s+" ");
            }
            System.out.println();
        }
    }
}
