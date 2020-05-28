public class Mine {
    public enum Type {Mine, Flag, Empty}
    private Type type;
    public enum Aspect {Cover, Uncover, CoveredMine}
    private Aspect aspect;
    private final String emoji;
    private int value;

    public Mine(String emoji, Type type, Aspect aspect, int value) {
        this.emoji = emoji;
        this.type = type;
        this.value = value;
        this.aspect = aspect;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Aspect getAspect() {
        return aspect;
    }

    public void setAspect(Aspect aspect) {
        this.aspect = aspect;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString() {
        if(getType() == Type.Flag)
            return "\u001B[31m\uD83C\uDFC1\u001B[0m";
        if (getType() == Type.Mine) {
            if (getAspect() == Aspect.CoveredMine)
                //return "\u001B[37m\uD83D\uDD32\u001B[0m";
                return "\u001B[31m\uD83D\uDD32\u001B[0m";
            else return this.emoji;
        }
        if (getType() == Type.Empty) {
            if(getAspect() == Aspect.Uncover) { //DA SWITCHARE PER COPRIRE LE CASELLE PRIMA DEL GIOCO
                if (getValue() != 0) {
                    if (getValue() == 1)
                        return "\u001B[34m1\u001B[0m";
                    if (getValue() == 2)
                        return "\u001B[32m2\u001B[0m";
                    if (getValue() == 3)
                        return "\u001B[31m3\u001B[0m";
                    if (getValue() == 4)
                        return "\u001B[33m4\u001B[0m";
                    if (getValue() == 5)
                        return "\u001B[35m5\u001B[0m";
                }
                else return " ";
            }
            else return "\u001B[37m\uD83D\uDD32\u001B[0m";
        }
        return " ";
    }
}
