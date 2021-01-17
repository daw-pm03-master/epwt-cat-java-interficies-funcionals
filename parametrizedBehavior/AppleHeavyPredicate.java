public class AppleHeavyPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
        return apple.weight >= 150;
    }
}