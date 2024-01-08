public class Main {
public void main(String[] arg){
   test(arg[0]);
}
public void test(int N){
    if(N==1||N==2){
        return 1;
    }
   return test(N-1)+test(N-2);
}
}