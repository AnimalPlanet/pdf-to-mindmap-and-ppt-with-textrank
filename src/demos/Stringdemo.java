package demos;

public class Stringdemo {
	public static void main(String [] args) throws Exception
	{
		FirstSteps fs=new FirstSteps();
		String a="tushar.txt";
		String o;
		int l = a.length();
		o=a.substring(0,l-4);
		o=o+"kale";
		//o=o -"kale";
		System.out.println(o);
		fs.createSlide(o);
		fs.createSlide(a);
		fs.addBullet();
		
		
	}

}
