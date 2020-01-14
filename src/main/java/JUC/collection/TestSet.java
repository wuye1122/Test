package JUC.collection;

import JUC.kafka.po.AgentStateDetailPo;

import java.util.HashSet;
import java.util.Set;

public class TestSet {

    public static void main(String[] args) {
        Set<AgentStateDetailPo> set = new HashSet<AgentStateDetailPo>();

        AgentStateDetailPo po =new AgentStateDetailPo();
        System.out.println("po:"+po.hashCode());
        set.add(po);

        AgentStateDetailPo po1 =new AgentStateDetailPo();
        po1.setAgentId("1");
        System.out.println("po1:"+po1.hashCode());
        set.add(po1);

       AgentStateDetailPo po2 =new AgentStateDetailPo();
        po2.setAgentId("2");
        System.out.println("po2:"+po2.hashCode());
        set.add(po2);
        System.out.println(set.toString());


    }


}
