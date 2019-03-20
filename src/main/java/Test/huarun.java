package Test;

import Demo.SessionDetail;

import java.util.*;

public class huarun {

    public static void main(String[] args) {
        Map<String,Integer> map = new LinkedHashMap<>();
        map.put("aa",null);
        map.put("bb",0);
        System.out.println(map.toString());
        System.out.println(map.get("bb"));
        System.out.println(map.get("cc"));
        System.out.println(map.get("aa"));


        SessionDetail session1 = new SessionDetail("2");
        SessionDetail session2 = new SessionDetail("33");
        SessionDetail session3 = new SessionDetail("555");
        SessionDetail session4 = new SessionDetail("666");
        SessionDetail session5 = new SessionDetail("77");
        SessionDetail session6 = new SessionDetail("8");
        List<SessionDetail> sessionDetailList = new ArrayList();
        sessionDetailList.add(session1);
        sessionDetailList.add(session2);
        sessionDetailList.add(session3);
        sessionDetailList.add(session4);
        sessionDetailList.add(session6);
        sessionDetailList.add(session5);
        System.out.println(sessionDetailList.toString());
        System.out.println(sessionDetailList.size());
//        [SessionDetail{entId='2'}, SessionDetail{entId='33'}, SessionDetail{entId='555'}, SessionDetail{entId='666'}, SessionDetail{entId='8'}, SessionDetail{entId='77'}]

        // start=0, length=3,
       // start=3, length=3,
       // start=6, length=3,
        Paging paging = Paging.pagination(sessionDetailList.size(),3,1);
        int fromIndex = paging.getQueryIndex();
        int toIndex = 0;
        if (fromIndex + paging.getPageSize() >= sessionDetailList.size()){
            toIndex = sessionDetailList.size();
        }else {
            toIndex = fromIndex +  paging.getPageSize();
        }
        if (fromIndex > toIndex){
            System.out.println( Collections.EMPTY_LIST);;
        }else{
            System.out.println(sessionDetailList.subList(fromIndex,toIndex).toString());

        }

    }
}
