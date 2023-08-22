public class CheckIfStringContainsAlphabets {
    public static void main(String[] args) {
        String str1 = "abc23ser";
        String str2 = "abcds";
        System.out.println(isOnlyAlphabets(str1));
        System.out.println(isOnlyAlphabets(str2));
    }

    //IntPredicate
    public static boolean isOnlyAlphabets(String str){
        return str!=null && !str.equals("") && str.chars().allMatch(Character::isLetter);
    }

}
