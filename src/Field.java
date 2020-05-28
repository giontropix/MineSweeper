public class Field {
    Mine[][] mineField;
    public Field(int row, int column, double ratio){
        mineField = new Mine[row][column];
        fieldFiller(ratio);
        getMines();
        lookAround();
    }

    public Mine mineGenerator(double ratio){
        double value = Math.random();
        if(value <= ratio)
            return new Mine(/*"\u001B[31m\uD83D\uDD32\u001B[0m"*/"\u001B[31m\uD83D\uDCA3\u001B[0m", Mine.Type.Mine, Mine.Aspect.Cover,100);
        else return new Mine("\u001B[37m\uD83D\uDD32\u001B[0m", Mine.Type.Empty, Mine.Aspect.Cover,0);
    }

    public void fieldFiller(double ratio){
        for (int i = 0; i < mineField.length; i++) {
            for (int j = 0; j < mineField[i].length; j++) {
                mineField[i][j] = mineGenerator(ratio);
            }
        }
    }

    public boolean intoFieldController(int i, int j){
        return (i < mineField.length) && (j < mineField[0].length) && ((i >= 0) && (j >= 0));
    }

    public void lookAround(){
        for (int i = 0; i < mineField.length; i++) {
            for (int j = 0; j < mineField[i].length; j++) {
                if(mineField[i][j].getType() == Mine.Type.Mine){
                    if(intoFieldController(i + 1, j))
                        mineField[i + 1][j].setValue(mineField[i + 1][j].getValue() + 1);
                    if(intoFieldController(i + 1, j + 1))
                        mineField[i + 1][j + 1].setValue(mineField[i + 1][j + 1].getValue() + 1);
                    if(intoFieldController(i + 1, j - 1))
                        mineField[i + 1][j - 1].setValue(mineField[i + 1][j - 1].getValue() + 1);
                    if(intoFieldController(i - 1, j))
                        mineField[i - 1][j].setValue(mineField[i - 1][j].getValue() + 1);
                    if(intoFieldController(i - 1, j + 1))
                        mineField[i - 1][j + 1].setValue(mineField[i - 1][j + 1].getValue() + 1);
                    if(intoFieldController(i - 1, j - 1))
                        mineField[i - 1][j - 1].setValue(mineField[i - 1][j - 1].getValue() + 1);
                    if(intoFieldController(i, j - 1))
                        mineField[i][j - 1].setValue(mineField[i][j - 1].getValue() + 1);
                    if(intoFieldController(i, j + 1))
                        mineField[i][j + 1].setValue(mineField[i][j + 1].getValue() + 1);
                }
            }
        }
    }

    public boolean isMine(int i, int j) {
        if(mineField[i][j].getType() == Mine.Type.Mine) {
            for (Mine[] mines : mineField) {
                for (Mine mine : mines) {
                    if (mine.getType() == Mine.Type.Mine)
                        mine.setAspect(Mine.Aspect.Uncover);
                }
            }
            return true;
        }
        else return false;
    }

    public void isFlag(){
        for (int i = 0; i < mineField.length; i++) {
            for (int j = 0; j < mineField[i].length; j++) {
                if (mineField[i][j].getType() == Mine.Type.Mine) {
                    if ((intoFieldController(i + 1, j) && mineField[i + 1][j].getAspect() == Mine.Aspect.Cover)
                            || (intoFieldController(i + 1, j + 1) && mineField[i + 1][j + 1].getAspect() == Mine.Aspect.Cover)
                            || (intoFieldController(i + 1, j - 1) && mineField[i + 1][j - 1].getAspect() == Mine.Aspect.Cover)
                            || (intoFieldController(i - 1, j) && mineField[i - 1][j].getAspect() == Mine.Aspect.Cover)
                            || (intoFieldController(i - 1, j + 1) && mineField[i - 1][j + 1].getAspect() == Mine.Aspect.Cover)
                            || (intoFieldController(i - 1, j - 1) && mineField[i - 1][j - 1].getAspect() == Mine.Aspect.Cover)
                            || (intoFieldController(i, j - 1) && mineField[i][j - 1].getAspect() == Mine.Aspect.Cover)
                            || (intoFieldController(i, j + 1) && mineField[i][j + 1].getAspect() == Mine.Aspect.Cover))
                        return;
                    else {
                        mineField[i][j].setType(Mine.Type.Flag);
                    }
                }
            }
        }
    }

    public void uncover(int i, int j){
        if (mineField[i][j].getType() != Mine.Type.Mine) {
            uncoverCurrent(i, j);
            if (mineField[i][j].getValue() == 0) {
                uncoverBottom(i, j);
                uncoverBottomLeft(i, j);
                uncoverBottomRight(i, j);
                uncoverLeft(i, j);
                uncoverRight(i, j);
                uncoverUp(i, j);
                uncoverUpLeft(i, j);
                uncoverUpRight(i, j);
            }
        }
        isFlag();
    }

    public void uncoverCurrent(int i, int j) {
        if (intoFieldController(i, j) && mineField[i][j].getAspect() == Mine.Aspect.Cover){
            mineField[i][j].setAspect(Mine.Aspect.Uncover);
        }
    }

    public void uncoverBottom(int i, int j) {
        if (intoFieldController(i + 1, j) && mineField[i + 1][j].getAspect() == Mine.Aspect.Cover) {
            if (mineField[i + 1][j].getValue() == 0) {
                mineField[i + 1][j].setAspect(Mine.Aspect.Uncover);
                uncover(i + 1, j);
            }
            else {
                mineField[i + 1][j].setAspect(Mine.Aspect.Uncover);
            }
        }
    }

    public void uncoverBottomRight(int i, int j) {
        if (intoFieldController(i + 1, j + 1) && mineField[i + 1][j + 1].getAspect() == Mine.Aspect.Cover) {
            if (mineField[i + 1][j + 1].getValue() == 0) {
                mineField[i + 1][j + 1].setAspect(Mine.Aspect.Uncover);
                uncover(i + 1, j + 1);
            }
            else {
                mineField[i + 1][j + 1].setAspect(Mine.Aspect.Uncover);
            }
        }
    }

    public void uncoverBottomLeft(int i, int j) {
        if (intoFieldController(i + 1, j - 1) && mineField[i + 1][j - 1].getAspect() == Mine.Aspect.Cover) {
            if (mineField[i + 1][j - 1].getValue() == 0) {
                mineField[i + 1][j - 1].setAspect(Mine.Aspect.Uncover);
                uncover(i + 1, j - 1);
            }
            else {
                mineField[i + 1][j - 1].setAspect(Mine.Aspect.Uncover);
            }
        }
    }

    public void uncoverUp(int i, int j) {
        if (intoFieldController(i - 1, j) && mineField[i - 1][j].getAspect() == Mine.Aspect.Cover) {
            if (mineField[i - 1][j].getValue() == 0) {
                mineField[i - 1][j].setAspect(Mine.Aspect.Uncover);
                uncover(i - 1, j);
            }
            else {
                mineField[i - 1][j].setAspect(Mine.Aspect.Uncover);
            }
        }
    }

    public void uncoverUpRight(int i, int j) {
        if (intoFieldController(i - 1, j + 1) && mineField[i - 1][j + 1].getAspect() == Mine.Aspect.Cover) {
            if (mineField[i - 1][j + 1].getValue() == 0) {
                mineField[i - 1][j + 1].setAspect(Mine.Aspect.Uncover);
                uncover(i - 1, j + 1);
            }
            else {
                mineField[i - 1][j + 1].setAspect(Mine.Aspect.Uncover);
            }
        }
    }

    public void uncoverUpLeft(int i, int j) {
        if (intoFieldController(i - 1, j - 1) && mineField[i - 1][j - 1].getAspect() == Mine.Aspect.Cover) {
            if (mineField[i - 1][j - 1].getValue() == 0) {
                mineField[i - 1][j - 1].setAspect(Mine.Aspect.Uncover);
                uncover(i - 1, j - 1);
            }
            else {
                mineField[i - 1][j - 1].setAspect(Mine.Aspect.Uncover);
            }
        }
    }

    public void uncoverLeft(int i, int j) {
        if (intoFieldController(i, j - 1) && mineField[i][j - 1].getAspect() == Mine.Aspect.Cover) {
            if (mineField[i][j - 1].getValue() == 0) {
                mineField[i][j - 1].setAspect(Mine.Aspect.Uncover);
                uncover(i, j - 1);
            }
            else {
                mineField[i][j - 1].setAspect(Mine.Aspect.Uncover);
            }
        }
    }

    public void uncoverRight(int i, int j) {
        if (intoFieldController(i, j + 1) && mineField[i][j + 1].getAspect() == Mine.Aspect.Cover) {
            if (mineField[i][j + 1].getValue() == 0) {
                mineField[i][j + 1].setAspect(Mine.Aspect.Uncover);
                uncover(i, j + 1);
            }
            else {
                mineField[i][j + 1].setAspect(Mine.Aspect.Uncover);
            }
        }
    }

    public int getMines() {
        int mines = 0;
        for (Mine[] value : mineField) {
            for (Mine mine : value) {
                if (mine.getType() == Mine.Type.Mine)
                    mines++;
            }
        }
        return mines;
    }

    public int getPoints() {
        int points = 0;
        for (Mine[] mines : mineField) {
            for (Mine mine : mines) {
                if (mine.getAspect() == Mine.Aspect.Uncover)
                    points++;
            }
        }
        return points;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < mineField.length ; i++) {
            if(i == 0)
                result.append("\t");
            if (i > 9)
                result.append(" ").append(i);
            else
            result.append("  ").append(i);
            if(i == mineField.length - 1)
                result.append("\n");
        }
        for (int x = 0; x < this.mineField.length; x++) {
            result.append(x).append("\t").append("[");
            for(int y = 0; y < this.mineField[x].length; y++) {
                result.append("[").append(mineField[x][y]).append("]");
            }
            result.append("]\n");
        }
        return result.toString();
    }
}
