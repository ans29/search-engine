public class Page implements Runnable
{
	StringBuilder title;
	
	Page(StringBuilder title)
	{
		this.title = title;
		run();
	}
	
	@Override
	public void run()
	{
		System.out.println ("\n" + this.title);
	}
}