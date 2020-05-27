public class Field {
    Mine[][] mineField;
    int points = 0;
    public Field(int row, int column){
        mineField = new Mine[row][column];
        fieldFiller();
        lookAround();
    }

    public Mine mineGenerator(){
        double value = Math.random();
        if(value <= 0.15)
            return new Mine(/*"\u001B[31m\uD83D\uDD32\u001B[0m"*/"\u001B[31m-\u001B[0m", Mine.Type.Mine, Mine.Aspect.Cover,100);
        else return new Mine("\u001B[37m\uD83D\uDD32\u001B[0m", Mine.Type.Empty, Mine.Aspect.Cover,0);
    }

    public void fieldFiller(){
        for (int i = 0; i < mineField.length; i++) {
            for (int j = 0; j < mineField[i].length; j++) {
                mineField[i][j] = mineGenerator();
            }
        }
    }

    public boolean intoFieldController(int i, int j){
        if((i < mineField.length) && (j < mineField[0].length) && ((i >= 0) && (j >= 0)))
            return true;
        return false;
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

    public void uncheker(int i, int j){
        /*for (int k = 0; k < mineField.length; k++) {
            for (int l = 0; l < mineField[k].length; l++) {
                if (mineField[i][j].getType() != Mine.Type.Mine) {
                    if(intoFieldController(i + k, j) && mineField[i + k][j].getValue() == 0)
                        uncheck(i + k, j);
                    else break;
                    if(intoFieldController(i + k, j - l) && mineField[i + k][j - l].getValue() == 0)
                        uncheck(i + k, j - l);
                    else break;
                    if(intoFieldController(i + k, j + l) && mineField[i + k][j + l].getValue() == 0)
                        uncheck(i + k, j + l);
                    else break;
                    if(intoFieldController(i - k, j) && mineField[i - k][j].getValue() == 0)
                        uncheck(i - k, j);
                    else break;
                    if(intoFieldController(i - k, j - l) && mineField[i - k][j - l].getValue() == 0)
                        uncheck(i - k, j - l);
                    else break;
                    if(intoFieldController(i - k, j + l) && mineField[i - k][j + l].getValue() == 0)
                        uncheck(i - k, j + l);
                    else break;
                    if(intoFieldController(i, j - l) && mineField[i][j - l].getValue() == 0)
                        uncheck(i, j - l);
                    else break;
                    if(intoFieldController(i, j + l) && mineField[i][j + l].getValue() == 0)
                        uncheck(i, j + l);
                    else break;
                }
            }
        }*/
        uncheckBottom(i, j);
        uncheckBottomLeft(i, j);
        uncheckBottomRight(i, j);
        uncheckLeft(i, j);
        uncheckRight(i, j);
        uncheckUp(i, j);
        uncheckUpLeft(i, j);
        uncheckUpRight(i, j);
    }

    /*public void uncheck(int i, int j) {
        if (mineField[i][j].getAspect() == Mine.Aspect.Cover)
            mineField[i][j].setAspect(Mine.Aspect.Uncover);
    }*/

    public void uncheckBottom(int i, int j) {
        for (int k = 0; k < mineField.length; k++) {
            if (intoFieldController(i + k, j) && mineField[i][j].getType() != Mine.Type.Mine) {
                if (mineField[i + k][j].getValue() == 0) {
                    mineField[i + k][j].setAspect(Mine.Aspect.Uncover);
                }
                else break;
            }
        }
    }

    public void uncheckBottomRight(int i, int j) {
        for (int k = 0; k < mineField.length; k++) {
            for (int l = 0; l < mineField[i].length; l++) {
                if (intoFieldController(i + k, j + l) && mineField[i][j].getType() != Mine.Type.Mine) {
                    if (mineField[i + k][j + l].getValue() == 0) {
                        mineField[i + k][j + l].setAspect(Mine.Aspect.Uncover);
                    } else break;
                }
            }
        }
    }

    public void uncheckBottomLeft(int i, int j) {
        for (int k = 0; k < mineField.length; k++) {
            for (int l = 0; l < mineField[i].length; l++) {
                if (intoFieldController(i + k, j - l) && mineField[i][j].getType() != Mine.Type.Mine) {
                    if (mineField[i + k][j - l].getValue() == 0) {
                        mineField[i + k][j - l].setAspect(Mine.Aspect.Uncover);
                    } else break;
                }
            }
        }
    }

    public void uncheckUp(int i, int j) {
        for (int k = 0; k < mineField.length; k++) {
            if (intoFieldController(i - k, j) && mineField[i][j].getType() != Mine.Type.Mine) {
                if (mineField[i - k][j].getValue() == 0) {
                    mineField[i - k][j].setAspect(Mine.Aspect.Uncover);
                }
                else break;
            }
        }
    }

    public void uncheckUpRight(int i, int j) {
        for (int k = 0; k < mineField.length; k++) {
            for (int l = 0; l < mineField[i].length; l++) {
                if (intoFieldController(i - k, j + l) && mineField[i][j].getType() != Mine.Type.Mine) {
                    if (mineField[i - k][j + l].getValue() == 0) {
                        mineField[i - k][j + l].setAspect(Mine.Aspect.Uncover);
                    } else break;
                }
            }
        }
    }

    public void uncheckUpLeft(int i, int j) {
        for (int k = 0; k < mineField.length; k++) {
            for (int l = 0; l < mineField[i].length; l++) {
                if (intoFieldController(i - k, j - l) && mineField[i][j].getType() != Mine.Type.Mine) {
                    if (mineField[i - k][j - l].getValue() == 0) {
                        mineField[i - k][j - l].setAspect(Mine.Aspect.Uncover);
                    } else break;
                }
            }
        }
    }

    public void uncheckLeft(int i, int j) {
        for (int k = 0; k < mineField.length; k++) {
            for (int l = 0; l < mineField[k].length; l++) {
                if (intoFieldController(i, j - l) && mineField[i][j].getType() != Mine.Type.Mine) {
                    if (mineField[i][j - l].getValue() == 0) {
                        mineField[i][j - l].setAspect(Mine.Aspect.Uncover);
                    } else break;
                }
            }
        }
    }

    public void uncheckRight(int i, int j) {
        for (int k = 0; k < mineField.length; k++) {
            for (int l = 0; l < mineField[k].length; l++) {
                if (intoFieldController(i, j + l) && mineField[i][j].getType() != Mine.Type.Mine) {
                    if (mineField[i][j + l].getValue() == 0) {
                        mineField[i][j + l].setAspect(Mine.Aspect.Uncover);
                    } else break;
                }
            }
        }
    }

    public String toString() {
        String result = "";
        System.out.println("\n\tA\tB\tC\tD\tE\tF\tG\tH\tI\tL");
        for (int x = 0; x < this.mineField.length; x++) {
            result += x + "\t" + "[";
            for(int y = 0; y < this.mineField[x].length; y++) {
                result += "[" + mineField[x][y] + "]";
            }
            result += "]\n";
        }
        return result;
    }
}
