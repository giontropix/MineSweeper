public class Mine {
    public enum Type {Mine, Flag, Empty}
    private Type type;
    public enum Aspect {Cover, Uncover}
    private Aspect aspect;
    private String emoji;
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

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString() {
        if (getAspect() == Aspect.Uncover) {
            if (getValue() == 0)
                return "\u001B[37m\uD83D\uDD32\u001B[0m";
        }
        if (getAspect() == Aspect.Cover) { //DA SWITCHARE PER COPRIRE LE CASELLE PRIMA DEL GIOCO
            if(getType() == Type.Mine)
                return this.emoji;
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
            else
                return "0";
        }
        else return "\u001B[37m\uD83D\uDD32\u001B[0m";
    }
}
