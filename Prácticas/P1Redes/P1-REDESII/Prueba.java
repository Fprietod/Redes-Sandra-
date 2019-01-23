public class Prueba{
	public static void main(String[] args) {
		String n=args[0];
		String s="";
		System.out.println(n.length());
		for(int i=n.length()-1;i>=0;i--){
            if(n.charAt(i)=='.'){
                s=n.substring(i,n.length());
                break;
            }
        }
        System.out.println(s);
	}
}