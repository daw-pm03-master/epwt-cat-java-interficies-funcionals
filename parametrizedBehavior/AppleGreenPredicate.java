public class AppleGreenPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
        return apple.color.equals("green");
    }
}