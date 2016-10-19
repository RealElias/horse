package com.opener;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by ilya on 17.10.16.
 */
public class HorseWalker {

    private static char CUBE_SIZE;
    private static char field[][][];
    private Boolean end = false;
    private List<Offset> offsets = new ArrayList<>();
    private final int startX;
    private final int startY;
    private final int startZ;
    private Stack<Offset> stepHistory = new Stack<>();

    public HorseWalker(int cubeSize, int startX, int startY, int startZ) {
        this.startX = startX;
        this.startY = startY;
        this.startZ = startZ;
        CUBE_SIZE = (char) cubeSize;
        field = new char[CUBE_SIZE][CUBE_SIZE][CUBE_SIZE];
        initOffsets();
    }

    private String go(int x, int y, int z, int step) {
        while (!end) {
            if (step == CUBE_SIZE * CUBE_SIZE * CUBE_SIZE - 1) {
                end = true;
            }

            Boolean deadEnd = true;
            for (Offset offset : offsets) {
                int newX = x + offset.x;
                int newY = y + offset.y;
                int newZ = z + offset.z;

                if(newX >= 0 && newX < CUBE_SIZE &&
                        newY >= 0 && newY < CUBE_SIZE &&
                        newZ >= 0 && newZ < CUBE_SIZE &&
                        field[newX][newY][newZ] == 0) {
                    x = newX;
                    y = newY;
                    z = newZ;
                    field[x][y][z] = offset.stepName;
                    step++;
                    stepHistory.push(offset);
                    deadEnd = false;
                    break;
                }
            }

            if (deadEnd) {
                if (stepHistory.empty()) {
                    break;
                }
                Offset backStep = stepHistory.pop();
                x = backStep.x;
                y = backStep.y;
                z = backStep.z;
                step--;
            }
        }

//        printField();
        String path = "";
        while (!stepHistory.empty()) {
            path += stepHistory.pop().stepName;
        }

        return path;
    }

    private void printField() {
        for (int i = 0; i < CUBE_SIZE; i++) {
            System.out.println(i);
            for (int j = 0; j < CUBE_SIZE; j++) {
                String line = "";
                for (int k = 0; k < CUBE_SIZE; k++) {
                    line += field[i][j][k] == 0 ? '0' : field[i][j][k];
                }
                System.out.println(line);
            }
            System.out.println();
        }
    }

    public String runHorseRun() {

        String path = go(startX, startY, startZ, 0);

        String reversePath = "";
        for (int i = path.length() - 2; i > 0; i--) {
            reversePath += path.charAt(i);
        }

        return reversePath;
    }

    private void initOffsets() {
        offsets.add(new Offset(1, 2, 2, 'A'));
        offsets.add(new Offset(1, 2, -2, 'B'));
        offsets.add(new Offset(1, -2, 2, 'C'));
        offsets.add(new Offset(1, -2, -2, 'D'));
        offsets.add(new Offset(-1, 2, 2, 'E'));
        offsets.add(new Offset(-1, 2, -2, 'F'));
        offsets.add(new Offset(-1, -2, 2, 'G'));
        offsets.add(new Offset(-1, -2, -2, 'H'));
        offsets.add(new Offset(2, 1, 2, 'I'));
        offsets.add(new Offset(2, 1, -2, 'J'));
        offsets.add(new Offset(2, -1, 2, 'K'));
        offsets.add(new Offset(2, -1, -2, 'L'));
        offsets.add(new Offset(-2, 1, 2, 'M'));
        offsets.add(new Offset(-2, 1, -2, 'N'));
        offsets.add(new Offset(-2, -1, 2, 'O'));
        offsets.add(new Offset(-2, -1, -2, 'P'));
        offsets.add(new Offset(2, 2, 1, 'Q'));
        offsets.add(new Offset(2, 2, -1, 'R'));
        offsets.add(new Offset(2, -2, 1, 'S'));
        offsets.add(new Offset(2, -2, -1, 'T'));
        offsets.add(new Offset(-2, 2, 1, 'U'));
        offsets.add(new Offset(-2, 2, -1, 'V'));
        offsets.add(new Offset(-2, -2, 1, 'W'));
        offsets.add(new Offset(-2, -2, -1, 'X'));
    }



}
