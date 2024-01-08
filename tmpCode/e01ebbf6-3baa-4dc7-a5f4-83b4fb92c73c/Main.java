public class Main {
public static void main(String[] arg){
   test(Integer.valueOf(arg[0]));
}
public static void test(int N){
   return test(N-1)+test(N-2);
}
}