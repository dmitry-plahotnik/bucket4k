package bucket4k;

public enum Hello {
    X(1),
    Y(1);

    int a;
    Hello(int a) {
        this.a = a;
    }

    public void set(int x) {
        a = x;
    }
}
