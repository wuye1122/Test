package jdk18.learn_local;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class listDemo {
    private static boolean status() {
        List<String> startlist = new ArrayList<>();
        startlist.add("*");
        startlist.add("to d2");
        startlist.add("in progress");
        startlist.add("done");
        startlist.add("to do");

        List<String> endlist = new ArrayList<>();
        endlist.add("*");
        endlist.add("to do");
        endlist.add("in progress");
        endlist.add("*");
        endlist.add("to d2");

        //to do --> to do
        boolean containStatus=false;//* todo ,in progress ,done,todo
        // * , in progress ,done ,todo ,todo
        System.out.println(startlist.toString());
        System.out.println(endlist.toString());

        System.out.println(startlist.indexOf("to do"));
        System.out.println(endlist.indexOf("to do"));

        if(startlist.contains("*")&&endlist.contains("*")){
            containStatus= startlist.indexOf("*")==endlist.indexOf("*");
            return containStatus;
        }
        if(startlist.contains("to do")&&endlist.contains("to do")){
            containStatus= startlist.indexOf("to do")==endlist.indexOf("to do");
            return containStatus;

        }

        if(startlist.contains("to do")&&endlist.contains("*")){
           return endlist.get(startlist.indexOf("to do")).equals("*");

        }

        if(endlist.contains("to do")&&startlist.contains("*")){
            return startlist.get(endlist.indexOf("to do")).equals("*");

        }
        return false;
    }
    public static void main(String[] args) {


     /*   boolean isStatus =status();
        System.out.println(isStatus);*/

        //ofNullable
        TestPo po = new TestPo();
        po.setId("22");
        po.setName("333");
        po.setList(new ArrayList<>());
        po.getList().add("");
        po.getList().add("11");
        po.getList().add("22");
        po.getList().add("");
        po.getList().add("333");

        po.getList().stream().forEach(x->{
            System.out.println("for each:"+x);
        });
        System.out.println("for each:1======================");

        System.out.println("toSTRING"+po.getList().stream().filter(x -> x.equals("")).findFirst().toString());
        po.getList().stream().filter(x -> x.equals("")).findFirst().ifPresent(x->{
            System.out.println("ifPresent"+x);
        });
        //过滤所有的可能都筛选处理
        po.getList().stream().filter(x -> x.equals("")).forEach(x->{
            System.out.println("for each "+x);
        });
        System.out.println("for each:2======================");

  /*      po.getList().add("");
        po.getList().add("11");
        po.getList().add("22");
        po.getList().add("");
        po.getList().add("333");*/
        List<String> listadd =  new ArrayList<>();

        if(po.getList().stream().filter(x -> x.equals("22")).count()==0){
            po.getList().stream().filter(x->x.equals("")).forEach(x-> Optional.ofNullable(x).ifPresent(listadd::add));
        }else{
            po.getList().stream().filter(x->x.equals("22")).forEach(x-> Optional.ofNullable(x).ifPresent(listadd::add));

        }
        String xx="";

      /*  调用orElse时，将评估apicall（）。值并将其传递给方法。 然而，在orElseGet的情况下，仅当oldValue为空时才进行评估。 orElseGet允许延迟评估。也就是说:orElse(T)无论前面Optional容器是null还是non-null，都会执行orElse里的方法，orElseGet(Supplier)并不会

         当然，在这个例子中，差异可能是不可测量的，但是如果你必须从远程Web服务或数据库中获取默认值，它突然变得非常重要。

*/

        //按照优先级过滤 如果有22 则不过来null
        System.out.println("for each:3======================");
        System.out.println(listadd.toString());
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);

        String parttern = "[0-9]*";
        System.out.println(Pattern.matches(parttern, "1a23"));
        System.out.println(Pattern.matches(parttern, "-123"));
        System.out.println(Pattern.matches(parttern, "0"));

        System.out.println(Collections.max(list)+1);
        Integer inde=null;
        System.out.println(null!=inde);
        System.out.println(null!=inde&&1==inde);

    }


}
