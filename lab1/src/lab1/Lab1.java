package lab1;

public class Lab1 {

	static int[] money={50,20,5,5,1,1,1};

	public static boolean fun(int num){
		if(num<0){
			return false;
		}
		for(int i=0;i<money.length;i++){
			if(num>=money[i]){
				num-=money[i];
			}
			if(num==0){
				return true;
			}
		}
		return false;
	}
}
