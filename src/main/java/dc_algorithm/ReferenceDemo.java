package dc_algorithm;

public class ReferenceDemo {

	public static void main(String[] args) {

		Account a1=new Account(100);
		//引用是一个对象的别名，主要用于函数参数和返回值类型,对引用的操作直接反应到所指向的对象
		Account b1=a1;
		a1.withdraw(10.2);
		a1.display();
		
		b1.withdraw(20);
		
		a1.display();
		
		a1.change(a1);
		a1.display();
		
	}
}

class Account {
	double balance;

	public Account(double b) {
		this.balance = b;
	}

	public void deposit(double d) {
		this.balance = this.balance + d;
	}

	public void withdraw(double w) {
		this.balance -= w;
	}

	public void display() {
		System.out.println("balance=" + this.balance);
	}
	
	public void change(Account acct){
		acct=new Account(10);
	}

}
