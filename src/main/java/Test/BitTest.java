package Test;

public class BitTest {

	/**
	 * @author wuhl
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
     
		//A*2^n
		System.out.println(9<<4);
		//�Ƿ�����ż��  ������ �������λһ����1
		System.out.println(1&1);
		System.out.println(2313124&1);
	    //����������  ��� ==(��������ֻ����0����1,��ͬȡ0��ͬȡ1,��ȫ��ԭʼ����һ��)
		int a=4;
		int b=3;
		System.out.println(a^0);
		System.out.println(a^1);
		System.out.println(a^b);
		System.out.println(b^a);
        a=a^b;
        b=a^b;
        a=a^b;
		System.out.println(a);
		System.out.println(b);
		System.out.println(a^-1);
		System.out.println(b^-1);
		System.out.println(Integer.toBinaryString(3));
		System.out.println(Integer.toBinaryString(-3));
		System.out.println(Integer.toBinaryString(-2));
		System.out.println(Integer.toBinaryString(-1));
		System.out.println(Integer.toBinaryString(-0));
		System.out.println(Integer.toBinaryString(1));
        //ȡ����ֵ (a^(a>>31))-(a>>31)
        int c=2;
        int d=-4;
        System.out.println(c>>31);//����
        System.out.println(d>>31);//����
        
        System.out.println(c^(c>>31));//0����
        System.out.println(d^(d>>31));//-1�������

        System.out.println(c^(c>>31)-(c>>31));//0����
        System.out.println((d^(d>>31))-(d>>31));//-1�������


	}

}
