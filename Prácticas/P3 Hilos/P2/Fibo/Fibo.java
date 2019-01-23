public class Fibo{

	public Fibo(){}
	
	public int sFibonacci(int num){
		int x=0;
        
        if (num == 0 || num == 1)
            x=num;
        else
        	x = (sFibonacci(num-2) )+( sFibonacci(num-1) );
        return x;
	}
	public void printFibo(){
		for(int i=0;i<=40;i++){
			int x=i;
        	System.out.println("Fibonacci "+i + "\t: "+sFibonacci(x) );
		}
	}
}