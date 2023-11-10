package phase2;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBase db = new DBase();
//		System.out.println(db.test());
		
//		String[] test = db.tableStatistics("course");
//		for(int i=0;i<test.length;++i) {
//			System.out.println(test[i]);
//		}
		
		System.out.println(db.attributeStatistics("course").toString());
	}

}
