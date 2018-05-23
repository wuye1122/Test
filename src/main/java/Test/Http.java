package Test;

import IO.HttpRequest;

public class Http {

	/**
	 * @author wuhl
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   String dcms="http://localhost:8085/dcmsStatics/report/DialerSessionDetail/gotoDialerSessionDetail";
	       String param="";
	       System.out.println(HttpRequest.sendGet(dcms,param));
	         
	}

}
