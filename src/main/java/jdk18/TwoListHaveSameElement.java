package jdk18;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TwoListHaveSameElement {


    public static void main(String[] args) {

        List<String> list1 = new ArrayList<>();
        list1.add("22");
        List<String> list2 = new ArrayList<>();
        list2.add("11");
        Collections.disjoint(list1,list2);
    }

    public static boolean TwoListHaveSameElement(List<?> l1, List<?> l2) {

        for (Object e : l1) {
            if (l2.contains(e)) {
                return true;
            }
        }
        return false;
    }

    // if (exist) {
    //                return ResponseWrapper.error("Project already exist", "Rule already exist for project:" + existPro);
    //            }
}
