package com.example.a2048;

public class Utils {

    public static int getScore(int[][] map){
        int sum = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                sum += map[i][j];
            }
        }
        return sum;
    }

    public static String customText(int degree){
        if(degree == 0){
            return "";
        } else {
            return "" + degree;
        }
    }

    public static String setColor(int degree){
        switch (degree){
            case 0: return "#FFFFFF";
            case 2: return "#fffde7";
            case 4: return "#fff9c4";
            case 8: return "#fff59d";
            case 16: return "#fff176";
            case 32: return "#ffee58";
            case 64: return "#ffeb3b";
            case 128: return "#fdd835";
            case 256: return "#fbc02d";
            case 512: return "#f9a825";
            case 1024: return "#f57f17";
            case 2048: return "#ff6f00";
            default: return "#e65100";
        }
    }

    public static boolean goingUp(int[][] map){
        boolean moved = false;
        for(int c = 0; c < 4; c++){
            // stuff 0s
            int count = 3;
            while(map[0][c] == 0 && count > 0){
                if(map[1][c] > 0 || map[2][c] > 0 || map[3][c] > 0){
                    moved = true;
                }
                map[0][c] = map[1][c];
                map[1][c] = map[2][c];
                map[2][c] = map[3][c];
                map[3][c] = 0;
                count--;
            }
            count = 2;
            while(map[1][c] == 0 && count > 0){
                if(map[2][c] > 0 || map[3][c] > 0){
                    moved = true;
                }
                map[1][c] = map[2][c];
                map[2][c] = map[3][c];
                map[3][c] = 0;
                count--;
            }
            count = 1;
            while(map[2][c] == 0 && count > 0){
                if(map[3][c] > 0){
                    moved = true;
                }
                map[2][c] = map[3][c];
                map[3][c] = 0;
                count--;
            }

            if(map[0][c] == map[1][c] && map[0][c] != 0){
                moved = true;
                map[0][c] *= 2;
                map[1][c] = map[2][c];
                map[2][c] = map[3][c];
                map[3][c] = 0;
            } else if(map[1][c] == map[2][c] && map[1][c] != 0){
                moved = true;
                map[1][c] *= 2;
                map[2][c] = map[3][c];
                map[3][c] = 0;
            } else if(map[2][c] == map[3][c] && map[2][c] != 0){
                moved = true;
                map[2][c] *= 2;
                map[3][c] = 0;
            }
        }
        return moved;

    }

    public static boolean goingDown(int[][] map){
        boolean moved = false;
        for(int c = 0; c < 4; c++){
            int count = 3;
            while(map[3][c] == 0 && count > 0){
                if(map[2][c] > 0 || map[1][c] > 0 || map[0][c] > 0){
                    moved = true;
                }
                map[3][c] = map[2][c];
                map[2][c] = map[1][c];
                map[1][c] = map[0][c];
                map[0][c] = 0;
                count--;
            }
            count = 2;
            while(map[2][c] == 0 && count > 0){
                if(map[1][c] > 0 || map[0][c] > 0){
                    moved = true;
                }
                map[2][c] = map[1][c];
                map[1][c] = map[0][c];
                map[0][c] = 0;
                count--;
            }
            count = 1;
            while(map[1][c] == 0 && count > 0){
                if(map[0][c] > 0){
                    moved = true;
                }
                map[1][c] = map[0][c];
                map[0][c] = 0;
                count--;
            }

            if(map[3][c] == map[2][c] && map[3][c] != 0){
                moved = true;
                map[3][c] *= 2;
                map[2][c] = map[1][c];
                map[1][c] = map[0][c];
                map[0][c] = 0;
            } else if(map[2][c] == map[1][c] && map[2][c] != 0){
                moved = true;
                map[2][c] *= 2;
                map[1][c] = map[0][c];
                map[0][c] = 0;
            } else if(map[1][c] == map[0][c] && map[1][c] != 0){
                moved = true;
                map[1][c] *= 2;
                map[0][c] = 0;
            }
        }
        return moved;
    }

    public static boolean goingLeft(int[][] map){
        boolean moved = false;
        for(int r = 0; r < 4; r++){
            int count = 3;
            while(map[r][0] == 0 && count > 0){
                if(map[r][1] > 0 || map[r][2] > 0 || map[r][3] > 0){
                    moved = true;
                }
                map[r][0] = map[r][1];
                map[r][1] = map[r][2];
                map[r][2] = map[r][3];
                map[r][3] = 0;
                count--;
            }
            count = 2;
            while(map[r][1] == 0 && count > 0){
                if(map[r][2] > 0 || map[r][3] > 0){
                    moved = true;
                }
                map[r][1] = map[r][2];
                map[r][2] = map[r][3];
                map[r][3] = 0;
                count--;
            }
            count = 1;
            while(map[r][2] == 0 && count > 0){
                if(map[r][3] > 0){
                    moved = true;
                }
                map[r][2] = map[r][3];
                map[r][3] = 0;
                count--;
            }

            if(map[r][0] == map[r][1] && map[r][0] != 0){
                moved = true;
                map[r][0] *= 2;
                map[r][1] = map[r][2];
                map[r][2] = map[r][3];
                map[r][3] = 0;
            } else if(map[r][1] == map[r][2] && map[r][1] != 0){
                moved = true;
                map[r][1] *= 2;
                map[r][2] = map[r][3];
                map[r][3] = 0;
            } else if(map[r][2] == map[r][3] && map[r][2] != 0){
                moved = true;
                map[r][2] *= 2;
                map[r][3] = 0;
            }
        }
        return moved;
    }

    public static boolean goingRight(int[][] map){
        boolean moved = false;
        for(int r = 0; r < 4; r++){
            int count = 3;
            while(map[r][3] == 0 && count > 0){
                if(map[r][2] > 0 || map[r][1] > 0 || map[r][0] > 0){
                    moved = true;
                }
                map[r][3] = map[r][2];
                map[r][2] = map[r][1];
                map[r][1] = map[r][0];
                map[r][0] = 0;
                count--;
            }
            count = 2;
            while(map[r][2] == 0 && count > 0){
                if(map[r][1] > 0 || map[r][0] > 0){
                    moved = true;
                }
                map[r][2] = map[r][1];
                map[r][1] = map[r][0];
                map[r][0] = 0;
                count--;
            }
            count = 1;
            while(map[r][1] == 0 && count > 0){
                if(map[r][0] > 0){
                    moved = true;
                }
                map[r][1] = map[r][0];
                map[r][0] = 0;
                count--;
            }

            if(map[r][3] == map[r][2] && map[r][3] != 0){
                moved = true;
                map[r][3] *= 2;
                map[r][2] = map[r][1];
                map[r][1] = map[r][0];
                map[r][0] = 0;
            } else if(map[r][2] == map[r][1] && map[r][2] != 0){
                moved = true;
                map[r][2] *= 2;
                map[r][1] = map[r][0];
                map[r][0] = 0;
            } else if(map[r][1] == map[r][0] && map[r][1] != 0){
                moved = true;
                map[r][1] *= 2;
                map[r][0] = 0;
            }
        }
        return moved;
    }
}
