public class Main {
public static void main(String[] arg){
   System.out.print(test(Integer.valueOf(arg[0])));
}
public static void test(int N){
    if(N==1||N==2){
        return 1;
    }
   return test(N-1)+test(N-2);
}
}