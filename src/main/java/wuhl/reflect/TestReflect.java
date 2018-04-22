package wuhl.reflect;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestReflect {

		/**
		 * @author wuhl
		 * void
		 */
		public int id;
		
		public String name;
		
		
		
		public int getId() {
			return id;
		}



		public void setId(int id) {
			this.id = id;
		}



		public String getName() {
			return name;
		}



		public void setName(String name) {
			this.name = name;
		}

		public void show(){
			System.out.println("������÷���");
		}

	    public TestReflect(){
	    	
	    }
	    
	    
		public TestReflect(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}



		@Override
		public String toString() {
			return "reflectTest [id=" + id + ", name=" + name + "]";
		}



		public static void main(String[] args) {
			// TODO Auto-generated method stub

			try {
				//ȫ·��
				Class c1 = Class.forName("wuhl.reflect.TestReflect");
				
				Class c2=TestReflect.class;
				
				TestReflect re=new TestReflect();
				Class c3= re.getClass();

				//new
				Object o=c1.newInstance();
				
                 //��ȡ���������� 
			     Field[] fs = c1.getDeclaredFields();  
	             for(Field field:fs){
	            	 System.out.println("���ԣ�"+field);
	             }
	             
	             //��ȡָ������
	             Field idF = c1.getDeclaredField("id");  
	             System.out.println("ָ�����ԣ�"+idF);
	             //�������Ա����ö���  ����ֵ
	             //˽��������Ҫprivate 
	             idF.setAccessible(true); //ʹ�÷�����ƿ��Դ��Ʒ�װ�ԣ�������java��������Բ���ȫ�� 
	             idF.set(o, 1);
	             Field idf = c1.getDeclaredField("name");  
	             idf.set(o, "�����");
	             //���
	             System.out.println(idF.get(o));
	             System.out.println(idf.get(o));

	             //��ȡ���з���
	             Method[] meth=  c1.getDeclaredMethods();
	             for(Method method:meth){
	            	 System.out.println("������"+method);
	             }           
	             //��ȡָ������
	             Method   m1 =  c1.getMethod("toString");
	             Method   m2 =  c1.getMethod("getId");
	             Method   m3 =  c1.getMethod("getName");
	             Method   m4 =  c1.getMethod("show");
	           //  m1.invoke(o);
	           System.out.println( m2.invoke(o));
	           System.out.println( m3.invoke(o));
	           System.out.println(m1.invoke(o));
	           m4.invoke(o);

	           /*  c1.getMethod("setId");
	             c1.getMethod("setName");*/

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}

	}


