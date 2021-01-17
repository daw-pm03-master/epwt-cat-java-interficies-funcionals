public class AppleRedPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
        return apple.color.equals("red");
    }
}
